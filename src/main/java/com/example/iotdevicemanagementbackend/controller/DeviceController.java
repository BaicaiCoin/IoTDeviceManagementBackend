package com.example.iotdevicemanagementbackend.controller;

import com.example.iotdevicemanagementbackend.pojo.DeviceResponse;
import com.example.iotdevicemanagementbackend.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @RequestMapping("addDevice/{deviceName}/{type}/{description}/{userId}/{clientId}/{token}")
    public int addDevice(@PathVariable String deviceName,
                     @PathVariable int type,
                     @PathVariable String description,
                     @PathVariable int userId,
                     @PathVariable String clientId,
                     @PathVariable String token) {
        return deviceService.addDevice(deviceName, type, description, userId, clientId, token);
    }

    @RequestMapping("queryDevice/{deviceId}/{token}")
    public DeviceResponse queryDevice(@PathVariable int deviceId,
                              @PathVariable String token) {
        return deviceService.queryDevice(deviceId, token);
    }

    @RequestMapping("queryDevices/{userId}/{token}")
    public DeviceResponse queryDevices(@PathVariable int userId,
                                      @PathVariable String token) {
        return deviceService.queryDevices(userId, token);
    }

    @RequestMapping("changeDevice/{deviceId}/{changeItem}/{changeContent}/{token}")
    public int changeDevice(@PathVariable int deviceId,
                            @PathVariable String changeItem,
                            @PathVariable String changeContent,
                            @PathVariable String token) {
        return deviceService.changeDevice(deviceId, changeItem, changeContent, token);
    }

    @RequestMapping("queryDeviceNum/{userId}/{token}")
    public int queryDeviceNum(@PathVariable int userId,
                              @PathVariable String token) {
        return deviceService.queryDeviceNum(userId, token);
    }

    @RequestMapping("queryConnectedDeviceNum/{userId}/{token}")
    public int queryConnectedDeviceNum(@PathVariable int userId,
                              @PathVariable String token) {
        return deviceService.queryConnectedDeviceNum(userId, token);
    }
}
