package com.wwj_concurrent.leve2.chapter2.observerdesigner.productconsumerpattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.productconsumerpattern
 * @Description:
 * @Date: 2021/7/7 9:13
 */
public class Message {
    private String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
