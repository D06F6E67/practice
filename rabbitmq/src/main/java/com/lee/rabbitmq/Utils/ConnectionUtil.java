package com.lee.rabbitmq.Utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Rabbitma连接工具类
 */
public class ConnectionUtil {

    public static Connection getConnection() throws IOException {
        // 定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置地址、端口
        factory.setHost("192.168.31.21");
        factory.setPort(5672);
        // 设置账号信息
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/");
        // 获取连接
        return factory.newConnection();
    }
}
