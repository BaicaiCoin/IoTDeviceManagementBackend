package com.example.iotdevicemanagementbackend.pojo;

public class LoginResult {
    private int userId;
    private String userName;
    private String email;
    private String token;
    private int loginStatus;    //0是登录成功，1是密码错误，2是用户不存在

    public LoginResult(int userId, String userName, String email, String token, int loginStatus) {
        this.token = token;
        this.loginStatus = loginStatus; //0表示登陆成功，1表示密码错误，2表示没有该用户
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public String getToken() {
        return token;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
