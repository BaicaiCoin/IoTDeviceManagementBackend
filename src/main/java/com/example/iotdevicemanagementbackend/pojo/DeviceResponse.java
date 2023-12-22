package com.example.iotdevicemanagementbackend.pojo;

import com.example.iotdevicemanagementbackend.pojo.Device;
public class DeviceResponse {
    private int existOrNot;
    private Device[] devices;

    public DeviceResponse(int existOrNot, Device[] devices)
    {
        this.devices = devices;
        this.existOrNot = existOrNot;
    }

    public DeviceResponse(int existOrNot) {
        this.existOrNot = existOrNot;
    }

    public Device[] getDevices() {
        return devices;
    }

    public int getExistOrNot() {
        return existOrNot;
    }

    public void setDevice(Device[] devices) {
        this.devices = devices;
    }

    public void setExistOrNot(int existOrNot) {
        this.existOrNot = existOrNot;
    }
}
