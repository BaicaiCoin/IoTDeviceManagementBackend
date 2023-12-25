package com.example.iotdevicemanagementbackend.pojo;

public class ReceivedMessage {
    private int alert;
    private String clientId;
    private String info;
    private double lat;
    private double lng;
    private long timestamp;
    private int value;

    // 添加对应属性的 getter 方法
    public int getAlert() {
        return alert;
    }

    public String getClientId() {
        return clientId;
    }

    public String getInfo() {
        return info;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getValue() {
        return value;
    }
}
