package com.example.iotdevicemanagementbackend.mapper;

import com.example.iotdevicemanagementbackend.pojo.Device;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper

@Repository
public interface DeviceMapper {
    int addDevice(Device device);


}
