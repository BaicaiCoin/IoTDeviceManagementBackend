package com.example.iotdevicemanagementbackend.controller;

import com.example.iotdevicemanagementbackend.mapper.UserMapper;
import com.example.iotdevicemanagementbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.iotdevicemanagementbackend.pojo.LoginResult;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("createUser/{userName}/{email}/{password}")
    public int createUser(@PathVariable String userName,
                          @PathVariable String email,
                          @PathVariable String password) {
        return userService.createUser(userName, email, password);
    }

    @RequestMapping("login/{loginType}/{loginInfo}/{password}")
    public LoginResult login(@PathVariable int loginType,
                             @PathVariable String loginInfo,
                             @PathVariable String password) {
        return userService.login(loginType, loginInfo, password);
    }
}
