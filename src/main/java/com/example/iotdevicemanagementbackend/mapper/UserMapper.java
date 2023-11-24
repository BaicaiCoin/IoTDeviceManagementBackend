package com.example.iotdevicemanagementbackend.mapper;

import com.example.iotdevicemanagementbackend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper

@Repository
public interface UserMapper {
    int addUser(User user);

    int quertUserByName(User user);

    int quertUserByNameAndPs(User user);

    int queryUserByEmail(User user);

    int queryUserByEmailAndPs(User user);
}
