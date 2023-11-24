package com.example.iotdevicemanagementbackend.service;

import com.example.iotdevicemanagementbackend.pojo.User;
import com.example.iotdevicemanagementbackend.pojo.LoginResult;
import com.example.iotdevicemanagementbackend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.iotdevicemanagementbackend.pojo.JwtUtils;
import com.example.iotdevicemanagementbackend.pojo.JasyptUtils;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public int createUser(String userName, String email, String password) {
        String encryptedPassword = JasyptUtils.encrypt(password, "123456");
        User user = new User(userName, email, encryptedPassword);
        int changeLine = userMapper.addUser(user);
        if(changeLine != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public LoginResult login(int loginType, String loginInfo, String password) {
        String encryptedPassword = JasyptUtils.encrypt(password, "123456");
        User user = new User(loginType, loginInfo, encryptedPassword);   //loginType=0代表用户名，1代表邮箱
        LoginResult loginResult;
        if(loginType == 0) {
            int queryOne = userMapper.quertUserByName(user);
            if(queryOne == 0 ) {
                loginResult = new LoginResult("", 2);
            } else {
                int queryTwo = userMapper.quertUserByNameAndPs(user);
                if(queryTwo == 0) {
                    loginResult = new LoginResult("", 1);
                } else {
                    String token = JwtUtils.createToken(queryTwo);
                    loginResult = new LoginResult(token, 0);
                }
            }
        } else {
            int queryOne = userMapper.queryUserByEmail(user);
            if(queryOne == 0) {
                loginResult = new LoginResult("", 2);
            } else {
                int queryTwo = userMapper.queryUserByEmailAndPs(user);
                if(queryTwo == 0) {
                    loginResult = new LoginResult("", 1);
                } else {
                    String token = JwtUtils.createToken(queryTwo);
                    loginResult = new LoginResult(token, 0);
                }
            }
        }
        return loginResult;
    }
}
