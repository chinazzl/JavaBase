package com.wwj_concurrent.leve2.chapter2.observerdesigner.gurdedpattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.gurdedpattern
 * @Description:
 * @Date: 2021/6/29 15:03
 */
public class Request {
    private final String value;

    public Request(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
