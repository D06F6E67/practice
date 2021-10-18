package com.lee.rabbitmq;

import com.lee.rabbitmq.Utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Work模式
 * 1生产者 多消费者 1队列
 */
@RestController
@RequestMapping("/two")
public class Two {
    private final static String QUEUE_NAME = "test_queue_work";

    /**
     * 发送消息
     *
     * @param message 消息内容
     */
//    @GetMapping("/{message}")
    public static void Send(@PathVariable("message") String message) throws IOException {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 发送内容
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("[one] send:" + message);
        // 关闭连接
        channel.close();
        connection.close();
    }

    /**
     * 接受消息 休眠10毫秒
     */
    public static void Recv1() throws Exception {
        // 获取连接、通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);
        // 定义消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 监听队列 false表示手动返回完成状态，true表示自动
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [two] Received1:" + message + "'");
            //休眠
            Thread.sleep(10);
            // 返回确认状态，注释掉表示使用自动确认模式
            //channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    /**
     * 接受消息 休眠1秒
     */
    public static void Recv2() throws Exception {
        // 获取连接、通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);
        // 定义消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 监听队列 false表示手动返回完成状态，true表示自动
        channel.basicConsume(QUEUE_NAME, false, queueingConsumer);
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [two] Received2:" + message + "'");
            //休眠
            Thread.sleep(1000);
            // 返回确认状态，注释掉表示使用自动确认模式
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

        fixedThreadPool.execute(() -> {
            try {
                Recv1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        fixedThreadPool.execute(() -> {
            try {
                Recv2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        for (int i = 0; i < 100; i++) {
            Send(String.valueOf(i));
        }
        fixedThreadPool.shutdown();
    }

}
