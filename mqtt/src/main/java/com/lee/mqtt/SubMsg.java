package com.lee.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * 订阅
 */
public class SubMsg {


    private static int qos = 2;
    private static String broker = "tcp://192.168.31.21:1883";


    private static MqttClient connect(String clientId) throws MqttException{
        MemoryPersistence persistence = new MemoryPersistence();
        MqttConnectOptions connOpts = new MqttConnectOptions();

        connOpts.setCleanSession(false);

        connOpts.setConnectionTimeout(10);
        connOpts.setKeepAliveInterval(20);
        connOpts.setUserName("admin");
        connOpts.setPassword("admin".toCharArray());

        MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
        mqttClient.connect(connOpts);
        return mqttClient;
    }

    public static void sub(MqttClient mqttClient,String topic) throws MqttException{
        int[] Qos  = {qos};
        String[] topics = {topic};
        mqttClient.subscribe(topics, Qos);
    }


    private static void runsub(String clientId, String topic) throws MqttException{
        MqttClient mqttClient = connect(clientId);
        if(mqttClient != null){
            sub(mqttClient,topic);
        }
        mqttClient.subscribe(topic,2, new IMqttMessageListener() {

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("接收消息主题 : " + topic);
                System.out.println("接收消息Qos : " + message.getQos());
                System.out.println("接收消息内容 : " + new String(message.getPayload()));
            }
        });
    }
    public static void main(String[] args) throws MqttException{

        runsub("testSub", "test-topic");
    }
}