package com.wwj_concurrent.leve2.chapter2.design.guardedsuspensionpattern;

/**
 * @author zhang zhao lin
 * @date 2021年06月24日 23:25
 * @Description
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
