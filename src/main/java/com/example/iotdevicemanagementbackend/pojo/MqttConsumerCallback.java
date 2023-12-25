package com.example.iotdevicemanagementbackend.pojo;

import com.example.iotdevicemanagementbackend.service.DeviceService;
import com.example.iotdevicemanagementbackend.service.MessageService;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.sql.*;
import java.util.Arrays;
import com.google.gson.Gson;

public class MqttConsumerCallback implements org.eclipse.paho.client.mqttv3.MqttCallbackExtended {
    private MqttClient client;
    private MqttConnectOptions options;
    private String[] topic;
    private int[] qos;

    DeviceService deviceService;
    MessageService messageService;

    public MqttConsumerCallback(MqttClient client, MqttConnectOptions options, String[] topic, int[] qos) {
        this.client = client;
        this.options = options;
        this.topic = topic;
        this.qos = qos;
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("mqtt服务器断连");
        try {
            if(client != null && !client.isConnected()) {
                client.reconnect();
                System.out.println("尝试重连");
            } else {
                client.connect(options);
                System.out.println("尝试建立新连接");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + Arrays.toString(topic));
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        try {
            String msg = new String(message.getPayload());
            System.out.println("收到topic:" + topic + " 消息：" + msg);
            Gson gson = new Gson();
            ReceivedMessage receivedMessage = gson.fromJson(msg, ReceivedMessage.class);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iot_device_management", "root", "cyy021027");
            String sqlMessage = "select device_id from device where client_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlMessage);
            preparedStatement.setString(1, receivedMessage.getClientId());
            ResultSet queryResult = preparedStatement.executeQuery();
            int deviceId = 0;
            while (queryResult.next()) {
                deviceId = queryResult.getInt(1);
            }
            if(deviceId != 0) {
                String sqlMessage1 = "insert into message (device_id, alarm, information, send_time, longitude, latitude) values (?,?,?,?,?,?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sqlMessage1);
                preparedStatement1.setInt(1, deviceId);
                preparedStatement1.setInt(2, receivedMessage.getAlert());
                preparedStatement1.setString(3, receivedMessage.getInfo());
                preparedStatement1.setTimestamp(4, new Timestamp(receivedMessage.getTimestamp()));
                preparedStatement1.setDouble(5, receivedMessage.getLng());
                preparedStatement1.setDouble(6, receivedMessage.getLat());
                int addResult = preparedStatement1.executeUpdate();
                preparedStatement1.close();
            }
            System.out.println("将消息存储进数据库");
        } catch (Exception e) {
            System.out.println("处理mqtt消息异常:" + e);
        }
    }

    @Override
    public void connectComplete(boolean b, String s) {
        try {
            if (null != topic && null != qos) {
                if (client.isConnected()) {
                    client.subscribe(topic, qos);
                    System.out.println("mqtt连接成功，客户端ID：" + PropertiesUtil.MQTT_CLIENT_ID);
                    System.out.println("订阅主题: " + Arrays.toString(topic));
                } else {
                    System.out.println("mqtt连接失败，客户端ID：" + PropertiesUtil.MQTT_CLIENT_ID);
                }
            }
        } catch (Exception e) {
            System.out.println("mqtt订阅主题异常:" + e);
        }
    }
}
