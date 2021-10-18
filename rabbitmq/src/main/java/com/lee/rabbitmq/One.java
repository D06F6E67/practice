package com.lee.rabbitmq;

import com.lee.rabbitmq.Utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 简单队列
 * 1生产者 1消费者 1队列
 */
@RestController
@RequestMapping("/one")
public class One {
    private final static String QUEUE_NAME = "q_test_01";

    /**
     * 发送消息
     * @param message 消息内容
     */
    @GetMapping("/{message}")
    public void Send(@PathVariable("message")String message) throws IOException {
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
     * 接受消息
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 定义消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            System.out.println("[one] 接收:" + new String(delivery.getBody()));
        }

    }
}
