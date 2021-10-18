package com.lee.rabbitmq;

import com.lee.rabbitmq.Utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 订阅模式
 * 1生成者 多消费者 多队列 1交换机
 * 交换机向每一个队列都发送消息
 */
@RestController
@RequestMapping("/three")
public class Three {
    // 交换机名称
    private final static String EXCHANGE_NAME = "test_exchange_fanout";
    // 队列名称
    private final static String QUEUE_NAME1 = "test_queue_fanout1";
    private final static String QUEUE_NAME2 = "test_queue_fanout2";

    /**
     * 向交换机中发送数据
     * 交换机只能转发数据不能存储数据
     * 如果交换机没有绑定队列，数据将丢失
     */
    @RequestMapping("/{message}")
    public void send(@PathVariable("message") String message) throws Exception {
        // 获取连接、通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机 类型：fanout
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println("[three] send:" + message);

        channel.close();
        connection.close();
    }

    /**
     * 接受消息
     */
    public static void recv1() throws Exception {
        // 获取连接、通道
        Channel channel = ConnectionUtil.getConnection().createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME1, false, false, false, null);
        // 绑定交换机
        channel.queueBind(QUEUE_NAME1, EXCHANGE_NAME, "");
        // 同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);
        // 定义消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME1, false, queueingConsumer);
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            System.out.println("[three] recv1:" + new String(delivery.getBody()));
            Thread.sleep(10);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    /**
     * 接受消息
     */
    public static void recv2() throws Exception {
        // 获取连接、通道
        Channel channel = ConnectionUtil.getConnection().createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME2, false, false, false, null);
        // 绑定交换机
        channel.queueBind(QUEUE_NAME2, EXCHANGE_NAME, "");
        // 同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);
        // 定义消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 监听队列
        channel.basicConsume(QUEUE_NAME2, false, queueingConsumer);
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            System.out.println("[three] recv2:" + new String(delivery.getBody()));
            Thread.sleep(10);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(() -> {
            try {
                recv1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        service.execute(() -> {
            try {
                recv2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        service.shutdown();
    }

}
