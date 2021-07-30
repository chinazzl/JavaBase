package com.wwj_concurrent.leve2.chapter2.observerdesigner.latch;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.latch
 * @Description: 自定义模拟CountDownLatch
 * @Date: 2021/7/7 13:57
 */
public class CountDown {
    private int count;

    private final int total;

    public CountDown(int total) {
        this.total = total;
    }

    public synchronized void countDown() {
        count++;
        this.notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        while (count != total) {
            this.wait();
        }
    }
}
