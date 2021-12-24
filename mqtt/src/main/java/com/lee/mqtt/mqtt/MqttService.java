package com.lee.mqtt.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * mqtt生产者
 */
@Configuration
public class MqttService {

    @Autowired
    private MqttConfig mqttConfig;

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    /*****
     * 创建MqttPahoClientFactory，设置MQTT Broker连接属性，如果使用SSL验证，也在这里设置。
     * @return
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{mqttConfig.getHost()});
//        options.setUserName(mqttConfig.getUsername());
        factory.setConnectionOptions(options);
        return factory;
    }

    /*****
     * 发送消息和消费消息Channel可以使用相同MqttPahoClientFactory
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler outbound() {
        // 在这里进行mqttOutboundChannel的相关设置
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler("publishClient", mqttClientFactory());
        messageHandler.setAsync(true); //如果设置成true，发送消息时将不会阻塞。
        messageHandler.setDefaultTopic("testTopic");
        messageHandler.setDefaultQos(2);
        return messageHandler;
    }
}
