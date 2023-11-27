package com.example.iotdevicemanagementbackend.mapper;

import com.example.iotdevicemanagementbackend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper

@Repository
public interface UserMapper {
    int addUser(User user);

    int queryUserByName(User user);

    String queryPsByName(User user);

    int queryIdByName(User user);

    int queryUserByEmail(User user);

    String queryPsByEmail(User user);

    int queryIdByEmail(User user);

    String queryEmailByName(User user);

    String queryNameByEmail(User user);

    int changeEmail(User user);

    int changePassword(User user);
}
