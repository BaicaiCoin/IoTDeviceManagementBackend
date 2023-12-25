package com.example.iotdevicemanagementbackend.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

public class Message {


    private int messageId;
    private int deviceId;
    private int alarm;
    private String information;
    private Timestamp sendTime;
    private double longitude;
    private double latitude;

    public Message(int messageId, int deviceId, int alarm, String information, Timestamp sendTime, double longitude, double latitude) {
        this.messageId = messageId;
        this.deviceId = deviceId;
        this.alarm = alarm;
        this.information = information;
        this.sendTime = sendTime;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Message(int deviceId, int alarm, String information, Timestamp sendTime, double longitude, double latitude) {
        this.deviceId = deviceId;
        this.alarm = alarm;
        this.information = information;
        this.sendTime = sendTime;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Message(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getMessageId() {
        return messageId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public int getAlarm() {
        return alarm;
    }

    public String getInformation() {
        return information;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Long sendTimeToLong() {
        return this.sendTime.getTime();
    }
}
