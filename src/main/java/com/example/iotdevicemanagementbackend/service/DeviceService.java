package com.example.iotdevicemanagementbackend.service;

import com.example.iotdevicemanagementbackend.pojo.Device;
import com.example.iotdevicemanagementbackend.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.iotdevicemanagementbackend.pojo.JwtUtils;

import java.sql.Timestamp;

@Service
public class DeviceService {
    @Autowired
    DeviceMapper deviceMapper;

    public int addDevice(String deviceName, int type, String description, int userId, String token) {
        Device device = new Device(deviceName, type, description, userId);
        int verifyResult = JwtUtils.verify(token);
        if(verifyResult != 0) return verifyResult;
        int changeLine = deviceMapper.addDevice(device);
        if(changeLine != 0) return 0;
        else return 5;
    }
}
