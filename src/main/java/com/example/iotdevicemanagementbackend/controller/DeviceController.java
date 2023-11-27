package com.example.iotdevicemanagementbackend.controller;

import com.example.iotdevicemanagementbackend.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @RequestMapping("addDevice/{deviceName}/{type}/{description}/{userId}/{token}")
    public int addDevice(@PathVariable String deviceName,
                     @PathVariable int type,
                     @PathVariable String description,
                     @PathVariable int userId,
                     @PathVariable String token) {
        return deviceService.addDevice(deviceName, type, description, userId, token);
    }


}
