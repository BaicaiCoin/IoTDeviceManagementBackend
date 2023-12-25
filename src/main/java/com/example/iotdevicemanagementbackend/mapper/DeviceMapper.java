package com.example.iotdevicemanagementbackend.mapper;

import com.example.iotdevicemanagementbackend.pojo.Device;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper

@Repository
public interface DeviceMapper {
    int addDevice(Device device);

    Device queryDevice(Device device);

    int queryDeviceExist(Device device);

    int queryDevicesExist(Device device);

    Device[] queryDevices(Device device);

    int changeName(Device device);

    int changeType(Device device);

    int changeDescription(Device device);

    int queryDeviceIdByClientId(Device device);

    int queryDeviceNumByClientId(Device device);
}
