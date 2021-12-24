package com.lee.mqtt.mqtt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttConfig {

    /**
     * RabbitMQ的MQTT连接地址
     */
    private String host;
    /**
     * RabbitMQ连接用户名
     */
    private String username;
    /**
     * RabbitMQ连接密码
     */
    private String password;
    /**
     * 链接mqtt时的ID
     */
    private String clientId;
    /**
     * 链接mqtt时的ID
     */
    private String serverId;
    /**
     * 队列名
     */
    private String topic;

}
