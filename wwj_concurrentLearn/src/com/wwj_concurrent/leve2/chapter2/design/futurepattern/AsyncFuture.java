package com.wwj_concurrent.leve2.chapter2.design.futurepattern;

/**
 * @author zhang zhao lin
 * @date 2021年06月23日 23:01
 * @Description
 */
public class AsyncFuture<T> implements Future<T> {

    private volatile boolean done = false;

    private T result;

    public synchronized void done(T result) {
        this.result = result;
        this.done = true;
        this.notifyAll();
    }

    @Override
    public T get() {
        while (!done) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
