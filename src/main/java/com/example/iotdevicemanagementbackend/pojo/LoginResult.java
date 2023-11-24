package com.example.iotdevicemanagementbackend.pojo;

public class LoginResult {
    private String token;
    private int loginStatus;    //0是登录成功，1是密码错误，2是用户不存在

    public LoginResult(String token, int loginStatus) {
        this.token = token;
        this.loginStatus = loginStatus; //0表示登陆成功，1表示密码错误，2表示没有该用户
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public String getToken() {
        return token;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
