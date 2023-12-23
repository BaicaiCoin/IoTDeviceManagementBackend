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
    private BigDecimal longitude;
    private BigDecimal latitude;

    public Message(int messageId, int deviceId, int alarm, String information, Timestamp sendTime, BigDecimal longitude, BigDecimal latitude) {
        this.messageId = messageId;
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

    public BigDecimal getLongitude() {
        return longitude;
    }

    public BigDecimal getLatitude() {
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

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Long sendTimeToLong() {
        return this.sendTime.getTime();
    }
}
