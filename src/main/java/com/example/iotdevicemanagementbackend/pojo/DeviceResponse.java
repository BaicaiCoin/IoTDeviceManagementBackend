package com.example.iotdevicemanagementbackend.pojo;

import com.example.iotdevicemanagementbackend.pojo.Device;
public class DeviceResponse {
    private int existOrNot;
    private Device device;

    public DeviceResponse(int existOrNot, Device device)
    {
        this.device = device;
        this.existOrNot = existOrNot;
    }
}
