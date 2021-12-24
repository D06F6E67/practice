package com.lee.mqtt.main;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * 发送消息到MQTT
 */
public class PubMsg {
    private static int qos = 2; //只有一次
    private static String broker = "tcp://192.168.31.21:1883";
    private static String userName = "admin";
    private static String passWord = "admin";


    private static MqttClient connect(String clientId, String userName,
                                      String password) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName(userName);
        connOpts.setPassword(password.toCharArray());
        connOpts.setConnectionTimeout(10);// 设置超时时间
        connOpts.setKeepAliveInterval(20); // 设置会话心跳时间
        //      String[] uris = {"tcp://10.100.124.206:1883","tcp://10.100.124.207:1883"};
        //      connOpts.setServerURIs(uris);  //起到负载均衡和高可用的作用

        // broker，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
        MqttClient mqttClient = new MqttClient(broker, clientId, persistence);


/*        // MQTT的连接设置
        MqttConnectOptions options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(true);
        // 设置连接的用户名
        options.setUserName(userName);
        // 设置连接的密码
        options.setPassword(passWord.toCharArray());
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(10);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(20);
        MqttTopic topic = mqttClient.getTopic("test-topic");
        // setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
        options.setWill(topic, "close".getBytes(), 2, true);*/

        mqttClient.setCallback(new PushCallback("test"));
        mqttClient.connect(connOpts);
        return mqttClient;
    }

    private static void pub(MqttClient sampleClient, String msg, String topic)
            throws MqttException {
        MqttMessage message = new MqttMessage(msg.getBytes());
        message.setQos(qos);
        message.setRetained(false);
        sampleClient.publish(topic, message);
    }

    private static void publish(String str, String clientId, String topic) throws MqttException {
        MqttClient mqttClient = connect(clientId, userName, passWord);

        if (mqttClient != null) {
            pub(mqttClient, str, topic);
            System.out.println("pub-->" + str);
        }

        if (mqttClient != null) {
            mqttClient.disconnect();
        }
    }

    public static void main(String[] args) throws MqttException {
        publish("message content", "client-id-0", "test-topic");
    }
}
