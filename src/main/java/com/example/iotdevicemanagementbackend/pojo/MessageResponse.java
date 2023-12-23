package com.example.iotdevicemanagementbackend.pojo;

public class MessageResponse {
    private int existOrNot;
    private Message[] messages;

    public MessageResponse(int existOrNot, Message[] messages)
    {
        this.messages = messages;
        this.existOrNot = existOrNot;
    }

    public MessageResponse(int existOrNot) {
        this.existOrNot = existOrNot;
    }

    public int getExistOrNot() {
        return existOrNot;
    }

    public Message[] getMessages() {
        return messages;
    }

    public void setExistOrNot(int existOrNot) {
        this.existOrNot = existOrNot;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }
}
