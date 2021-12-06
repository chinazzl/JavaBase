package com.wwj_concurrent.leve2.chapter2.design.producecustonm;

/**
 * @author zhang zhao lin
 * @date 2021年06月30日 23:37
 * @Description
 */
public class Message {
    String data;

    public Message(String data) {
        this.data = data;
    }

    public Message() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
