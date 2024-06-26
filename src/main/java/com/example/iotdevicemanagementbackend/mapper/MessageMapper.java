package com.example.iotdevicemanagementbackend.mapper;

import com.example.iotdevicemanagementbackend.pojo.Device;
import com.example.iotdevicemanagementbackend.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper

@Repository
public interface MessageMapper {

    int queryMessageExist(Message message);

    Message[] queryMessage(Message message);

    int addMessage(Message message);

}
