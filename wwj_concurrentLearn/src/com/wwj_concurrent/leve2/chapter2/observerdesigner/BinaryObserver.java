package com.wwj_concurrent.leve2.chapter2.observerdesigner;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner
 * @Description:
 * @Date: 2021/6/17 17:05
 */
public class BinaryObserver extends ObServer {

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    protected void update() {
        System.out.println("Binary Server " + Integer.toBinaryString(subject.getState()));
    }
}
