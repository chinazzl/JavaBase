package com.wwj_concurrent.leve2.chapter2.observerdesigner;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner
 * @Description:
 * @Date: 2021/6/17 17:04
 */
public abstract class ObServer {
    public Subject subject;

    public ObServer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    protected abstract void update();

}
