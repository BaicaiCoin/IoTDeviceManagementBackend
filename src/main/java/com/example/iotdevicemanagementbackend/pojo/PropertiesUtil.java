package com.example.iotdevicemanagementbackend.pojo;

public class PropertiesUtil {
    public static String MQTT_HOST;
    public static String MQTT_CLIENT_ID;
    public static String MQTT_USER_NAME;
    public static String MQTT_PASSWORD;
    public static String MQTT_TOPIC;

    static {
        MQTT_HOST = "tcp://121.43.152.248:1884";
        MQTT_CLIENT_ID = "client_id_001";
        MQTT_USER_NAME = "cabbageCoin";
        MQTT_PASSWORD = "cyy021027";
        MQTT_TOPIC = "BSIoTManagement";
    }
}
