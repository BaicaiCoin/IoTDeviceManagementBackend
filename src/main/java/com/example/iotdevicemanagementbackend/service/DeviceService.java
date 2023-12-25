package com.example.iotdevicemanagementbackend.service;

import com.example.iotdevicemanagementbackend.pojo.Device;
import com.example.iotdevicemanagementbackend.pojo.DeviceResponse;
import com.example.iotdevicemanagementbackend.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.iotdevicemanagementbackend.pojo.JwtUtils;

import java.sql.Timestamp;

@Service
public class DeviceService {
    @Autowired
    DeviceMapper deviceMapper;

    public int addDevice(String deviceName, int type, String description, int userId, String clientId, String token) {
        Device device = new Device(deviceName, type, description, userId, clientId);
        int verifyResult = JwtUtils.verify(token);
        if(verifyResult != 0) return verifyResult;
        int changeLine = deviceMapper.addDevice(device);
        if(changeLine != 0) return 0;
        else return 1;
    }

    public DeviceResponse queryDevice(int deviceId, String token) {
        Device device = new Device(0, deviceId);
        int verifyResult = JwtUtils.verify(token);
        if(verifyResult != 0){
            return new DeviceResponse(verifyResult);
        }
        int queryLine = deviceMapper.queryDeviceExist(device);
        if(queryLine != 0) {
            Device[] resDevice = new Device[]{deviceMapper.queryDevice(device)};
            return new DeviceResponse(1, resDevice);
        } else {
            return new DeviceResponse(0);
        }
    }

    public DeviceResponse queryDevices(int userId, String token) {
        Device device = new Device(1, userId);
        int verifyResult = JwtUtils.verify(token);
        if(verifyResult != 0){
            return new DeviceResponse(verifyResult);
        }
        int queryLine = deviceMapper.queryDevicesExist(device);
        if(queryLine != 0) {
            Device[] resDevices = deviceMapper.queryDevices(device);
            return new DeviceResponse(1, resDevices);
        } else {
            return new DeviceResponse(0);
        }
    }

    public int changeDevice(int deviceId, String changeItem, String changeContent, String token) {
        int verifyResult = JwtUtils.verify(token);
        if(verifyResult != 0) return verifyResult;
        Device device = new Device(changeItem, deviceId, changeContent);
        int queryLine = deviceMapper.queryDeviceExist(device);
        if(queryLine != 0) {
            int changeLine = 0;
            switch (changeItem) {
                case "name" -> changeLine = deviceMapper.changeName(device);
                case "type" -> changeLine = deviceMapper.changeType(device);
                case "description" -> changeLine = deviceMapper.changeDescription(device);
            }
            if(changeLine == 1) return 1;
            else return 2;
        } else {
            return 0;
        }
    }

    public int queryDeviceNum(int userId, String token) {
        DeviceResponse deviceResponse = queryDevices(userId, token);
        if(deviceResponse.getExistOrNot() == 0)return 0;
        Device[] devices = deviceResponse.getDevices();
        return devices.length;
    }

    public int queryConnectedDeviceNum(int userId, String token) {
        DeviceResponse deviceResponse = queryDevices(userId, token);
        if(deviceResponse.getExistOrNot() == 0)return 0;
        Device[] devices = deviceResponse.getDevices();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        int count = 0;
        for(Device device: devices) {
            if(now.getTime() - device.getLastActiveTime().getTime() <= 24*60*60*1000) {
                count++;
            }
        }
        return count;
    }

    public int queryDeviceIdByClientId(String clientId) {
        Device device = new Device(clientId);
        int queryNum = deviceMapper.queryDeviceNumByClientId(device);
        if(queryNum == 0)return 0;
        return deviceMapper.queryDeviceIdByClientId(device);
    }
}
