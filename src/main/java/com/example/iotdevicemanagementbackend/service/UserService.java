package com.example.iotdevicemanagementbackend.service;

import com.example.iotdevicemanagementbackend.pojo.User;
import com.example.iotdevicemanagementbackend.pojo.LoginResult;
import com.example.iotdevicemanagementbackend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.iotdevicemanagementbackend.pojo.JwtUtils;
import com.example.iotdevicemanagementbackend.pojo.JasyptUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public int createUser(String userName, String email, String password) {
        String encryptedPassword = JasyptUtils.encrypt(password, "123456");
        User user = new User(userName, email, encryptedPassword);
        int checkNameUnique = userMapper.queryUserByName(user);
        if(checkNameUnique != 0) return 1;
        int checkEmailUnique = userMapper.queryUserByEmail(user);
        if(checkEmailUnique != 0) return 2;
        if(userName.length() < 6) return 3;
        if(password.length() < 6) return 4;
        if(!emailFormatVerify(email)) return 5;
        int changeLine = userMapper.addUser(user);
        if(changeLine != 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean emailFormatVerify(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public LoginResult login(int loginType, String loginInfo, String password) {
        User user = new User(loginType, loginInfo, password);   //loginType=0代表用户名，1代表邮箱
        LoginResult loginResult;
        if(loginType == 0) {
            int queryOne = userMapper.queryUserByName(user);
            if(queryOne == 0 ) {
                loginResult = new LoginResult(0, "", "", "", 2);
            } else {
                String userEncryptPassword = userMapper.queryPsByName(user);
                String userPassword = JasyptUtils.decrypt(userEncryptPassword, "123456");
                if(!userPassword.equals(password)) {
                    loginResult = new LoginResult(0, "", "", "", 1);
                } else {
                    int userId = userMapper.queryIdByName(user);
                    String token = JwtUtils.createToken(userId);
                    String email = userMapper.queryEmailByName(user);
                    loginResult = new LoginResult(userId, loginInfo, email, token, 0);
                }
            }
        } else {
            int queryOne = userMapper.queryUserByEmail(user);
            if(queryOne == 0) {
                loginResult = new LoginResult(0, "", "", "", 2);
            } else {
                String userEncryptPassword = userMapper.queryPsByEmail(user);
                String userPassword = JasyptUtils.decrypt(userEncryptPassword, "123456");
                if(!userPassword.equals(password)) {
                    loginResult = new LoginResult(0, "", "", "", 1);
                } else {
                    int userId = userMapper.queryIdByEmail(user);
                    String token = JwtUtils.createToken(userId);
                    String userName = userMapper.queryNameByEmail(user);
                    loginResult = new LoginResult(userId, userName, loginInfo, token, 0);
                }
            }
        }
        return loginResult;
    }

    public int passwordAuthentification(String userName, String token, String password) {
        int verifyResult = JwtUtils.verify(token);
        if(verifyResult != 0) return verifyResult;
        User user = new User(0, userName, password);
        String encryptUserPassword = userMapper.queryPsByName(user);
        String userPassword = JasyptUtils.decrypt(encryptUserPassword, "123456");
        if(!userPassword.equals(password)) return 5;
        else return 0;
    }

    public int changeEmail(int userId, String newEmail) {
        User user = new User(userId, "", newEmail, "");
        int query = userMapper.queryUserByEmail(user);
        if(query != 0) return 1;
        if(!emailFormatVerify(newEmail)) return 2;
        userMapper.changeEmail(user);
        return 0;
    }

    public int changePassword(int userId, String newPassword) {
        User user = new User(userId, "", "", newPassword);
        if(newPassword.length() < 6) return 1;
        userMapper.changePassword(user);
        return 0;
    }
}
