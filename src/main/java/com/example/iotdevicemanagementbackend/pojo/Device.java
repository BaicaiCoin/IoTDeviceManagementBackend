package com.example.iotdevicemanagementbackend.pojo;

import java.sql.Timestamp;

public class Device {
    private int deviceId;
    private String deviceName;
    private int type;
    private String description;
    private int userId;
    private Timestamp createTime;
    private Timestamp lastActiveTime;

    public Device(String deviceName, int type, String description, int userId) {
        this.deviceName = deviceName;
        this.type = type;
        this.description = description;
        this.userId = userId;
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.lastActiveTime = new Timestamp(System.currentTimeMillis());
    }

    public Device(String deviceName, int type, String description, int userId, Timestamp createTime, Timestamp lastActiveTime) {
        this.deviceName = deviceName;
        this.type = type;
        this.description = description;
        this.userId = userId;
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.lastActiveTime = new Timestamp(System.currentTimeMillis());
        this.createTime = createTime;
        this.lastActiveTime = lastActiveTime;
    }

    public int getUserId() {
        return userId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getLastActiveTime() {
        return lastActiveTime;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setLastActiveTime(Timestamp lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }
}