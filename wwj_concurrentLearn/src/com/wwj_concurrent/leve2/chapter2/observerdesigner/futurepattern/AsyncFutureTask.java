package com.wwj_concurrent.leve2.chapter2.observerdesigner.futurepattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.futurepattern
 * @Description: Future 代表一个未来的一个凭据
 * @Date: 2021/6/29 10:53
 */
public class AsyncFutureTask<T> implements Future<T> {

    private volatile boolean done = false;

    private T result;

    public synchronized void done(T result) {
        this.done = true;
        this.result = result;
        this.notifyAll();
    }

    @Override
    public synchronized T get() {
        try {
            while (!done) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
