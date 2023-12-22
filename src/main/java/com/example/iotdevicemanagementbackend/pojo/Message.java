package com.example.iotdevicemanagementbackend.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Message {


    private int messageId;
    private int deviceId;
    private int alarm;
    private String information;
    private Timestamp sendTime;
    private BigDecimal longitude;
    private BigDecimal latitude;

    public int getMessageId() {
        return messageId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public int getAlarm() {
        return alarm;
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

    public String getInformation() {
        return information;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }
}
