package com.example.l3uri.opencv_detection;

public class Message {
    private String msg;

    public Message(){
        this("");
    }

    public Message(String str){
        this.msg = str;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}