package com.example.iotdevicemanagementbackend.controller;

import com.example.iotdevicemanagementbackend.pojo.DeviceResponse;
import com.example.iotdevicemanagementbackend.pojo.Message;
import com.example.iotdevicemanagementbackend.pojo.MessageResponse;
import com.example.iotdevicemanagementbackend.pojo.MqttConsumer;
import com.example.iotdevicemanagementbackend.service.DeviceService;
import com.example.iotdevicemanagementbackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @RequestMapping("queryMessages/{deviceId}/{token}")
    public MessageResponse queryMessages(@PathVariable int deviceId,
                                        @PathVariable String token) {
        return messageService.queryMessages(deviceId, token);
    }

    @RequestMapping("queryPath/{deviceId}/{token}")
    public MessageResponse queryPath(@PathVariable int deviceId,
                                     @PathVariable String token) {
        return messageService.queryPath(deviceId, token);
    }

    @RequestMapping("queryMessageNum/{deviceId}/{token}")
    public int queryMessageNum(@PathVariable int deviceId,
                               @PathVariable String token) {
        return messageService.queryMessageNum(deviceId, token);
    }

    @RequestMapping("queryUserMessageNum/{userId}/{token}")
    public int queryUserMessageNum(@PathVariable int userId,
                                   @PathVariable String token) {
        return messageService.queryUserMessageNum(userId, token);
    }

    @RequestMapping("subscribe/{topic}/{qos}")
    public Object subscribe(@PathVariable String topic,
                            @PathVariable int qos) {
        MqttConsumer.subscribe(topic, qos);
        return "订阅主题"+topic+"成功";
    }

    @RequestMapping("latestWeekMessages/{deviceId}/{token}")
    public int[] latestWeekMessages(@PathVariable int deviceId,
                                    @PathVariable String token) {
        return messageService.latestWeekMessages(deviceId, token);
    }

    @RequestMapping("alarmNum/{deviceId}/{token}")
    public int[] alarmNum(@PathVariable int deviceId,
                          @PathVariable String token) {
        return messageService.alarmNum(deviceId, token);
    }
}
