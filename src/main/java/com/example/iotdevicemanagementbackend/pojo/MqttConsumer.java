package com.example.iotdevicemanagementbackend.pojo;

import org.eclipse.paho.client.mqttv3.*;

import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MqttConsumer implements ApplicationRunner {

    private static MqttClient client;

    @Override
    public void run(ApplicationArguments applicationArguments) {
        System.out.println("mqtt服务器启动");
        this.connect();
    }

    private void connect() {
        try {
            getClient();
            MqttConnectOptions options = getOptions();
            String[] topic = PropertiesUtil.MQTT_TOPIC.split(",");
            int[] qos = getQos(topic.length);
            create(options, topic, qos);
        } catch (Exception e) {
            System.out.println("mqtt连接异常：" + e);
        }
    }

    private void getClient() {
        try {
            if(client == null) {
                client = new MqttClient(PropertiesUtil.MQTT_HOST, PropertiesUtil.MQTT_CLIENT_ID, new MemoryPersistence());
            }
            System.out.println("创建mqtt客户端：");
        } catch (Exception e) {
            System.out.println("创建mqtt客户端异常：" + e);
        }
    }

    private MqttConnectOptions getOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(PropertiesUtil.MQTT_USER_NAME);
        options.setPassword(PropertiesUtil.MQTT_PASSWORD.toCharArray());
        options.setCleanSession(false);
        System.out.println("生成mqtt配置对象");
        return options;
    }

    private int[] getQos(int length) {
        int[] qos = new int[length];
        Arrays.fill(qos, 1);
        System.out.println("设置消息发布质量");
        return qos;
    }

    private void create(MqttConnectOptions options, String[] topic, int[] qos) {
        try {
            client.setCallback(new MqttConsumerCallback(client, options, topic, qos));
            System.out.println("添加回调处理");
            client.connect(options);
        } catch (Exception e) {
            System.out.println("装载实例异常：" + e);
        }
    }

    public static void subscribe(String topic, int qos) {
        try {
            System.out.println("topic: " + topic);
            client.subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
