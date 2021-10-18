package com.lee.rabbitmq;

import com.lee.rabbitmq.Utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主题模式（通配符模式）
 * 1生成者 多消费者 多队列 1交换机
 * 交换机向符合条件的队列发送消息
 */
@RestController
@RequestMapping("/five")
public class Five {
    // 交换机名称
    private final static String EXCHANGE_NAME = "test_exchange_topic";
    // 队列名称
    private final static String QUEUE_NAME1 = "test_queue_topic1";
    private final static String QUEUE_NAME2 = "test_queue_topic2";

    /**
     * 发送消息
     * @param key 消息key
     * @param message 消息值
     */
    @GetMapping("/{key}/{message}")
    public void send(@PathVariable("key")String key, @PathVariable("message")String message) throws Exception {
        // 获取连接、通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机 类型：topic
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        // 发送消息
        channel.basicPublish(EXCHANGE_NAME, key, null, message.getBytes());

        channel.close();
        connection.close();
    }

    /**
     * 接受 key为 in.XX up.XX/up.XX.XX/.... 的消息
     */
    public static void recv1() throws Exception {
        // 获取连接、通道
        Channel channel = ConnectionUtil.getConnection().createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME1, false, false, false, null);
        // 绑定队列到交换机 *匹配一个词，#匹配一个或多个如a.# 能匹配a.b.c 但是a.* 只能匹配a.b
        channel.queueBind(QUEUE_NAME1, EXCHANGE_NAME, "in.*");
        channel.queueBind(QUEUE_NAME1, EXCHANGE_NAME, "up.#");
        // 同时只接受一条消息
        channel.basicQos(1);
        // 定义消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME1, true, queueingConsumer);
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            System.out.println("[four] recv1:" + new String(delivery.getBody()));
        }
    }

    /**
     * 接受 key为 XX.XX 的消息
     */
    public static void recv2() throws Exception {
        // 获取连接、通道
        Channel channel = ConnectionUtil.getConnection().createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME2, false, false, false, null);
        // 绑定交换机
        channel.queueBind(QUEUE_NAME2, EXCHANGE_NAME, "*.*");
        channel.basicQos(1);
        // 定义消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME2, true, queueingConsumer);
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            System.out.println("[four] recv2:" + new String(delivery.getBody()));
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
