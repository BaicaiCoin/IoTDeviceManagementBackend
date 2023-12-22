package com.example.iotdevicemanagementbackend.service;

import com.example.iotdevicemanagementbackend.pojo.Message;
import com.example.iotdevicemanagementbackend.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.iotdevicemanagementbackend.pojo.JwtUtils;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;


}
