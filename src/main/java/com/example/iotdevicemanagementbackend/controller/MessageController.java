package com.example.iotdevicemanagementbackend.controller;

import com.example.iotdevicemanagementbackend.service.DeviceService;
import com.example.iotdevicemanagementbackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

}
