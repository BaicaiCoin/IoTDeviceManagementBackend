package com.example.iotdevicemanagementbackend.service;

import com.example.iotdevicemanagementbackend.pojo.*;
import com.example.iotdevicemanagementbackend.mapper.MessageMapper;
import com.example.iotdevicemanagementbackend.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Comparator;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    DeviceService deviceService;

    public MessageResponse queryMessages(int deviceId, String token) {
        int verifyResult = JwtUtils.verify(token);
        if(verifyResult != 0){
            return new MessageResponse(verifyResult);
        }
        Message message = new Message(deviceId);
        int queryLine = messageMapper.queryMessageExist(message);
        if(queryLine != 0) {
            Message[] messages = messageMapper.queryMessage(message);
            return new MessageResponse(1, messages);
        } else {
            return new MessageResponse(0);
        }
    }

    public MessageResponse queryPath(int deviceId, String token) {
        MessageResponse messageResponse = queryMessages(deviceId, token);
        if(messageResponse.getExistOrNot() != 1)return messageResponse;
        Message[] messages = messageResponse.getMessages();
        Arrays.sort(messages, Comparator.comparingLong(Message::sendTimeToLong));
        return new MessageResponse(1, messages);
    }

    public int queryMessageNum(int deviceId, String token) {
        MessageResponse messageResponse = queryMessages(deviceId, token);
        if(messageResponse.getExistOrNot() != 1)return 0;
        Message[] messages = messageResponse.getMessages();
        return messages.length;
    }

    public int queryUserMessageNum(int useId, String token) {
        DeviceResponse deviceResponse = deviceService.queryDevices(useId, token);
        if(deviceResponse.getExistOrNot() != 1)return 0;
        Device[] devices = deviceResponse.getDevices();
        int count = 0;
        for(Device device : devices) {
            int deviceId = device.getDeviceId();
            int messageCount = queryMessageNum(deviceId, token);
            count += messageCount;
        }
        return count;
    }

    public int addMessage(int deviceId, int alarm, String information, Timestamp sendTime, double longitude, double latitude) {
        Message message = new Message(deviceId, alarm, information, sendTime, longitude, latitude);
        return messageMapper.addMessage(message);
    }
}
