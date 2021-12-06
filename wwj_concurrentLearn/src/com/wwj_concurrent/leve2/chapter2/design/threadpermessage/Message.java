package com.wwj_concurrent.leve2.chapter2.design.threadpermessage;

/**
 * @author zhang zhao lin
 * @date 2021年07月08日 22:26
 * @Description
 */
public class Message {

    private String value;

    public Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
