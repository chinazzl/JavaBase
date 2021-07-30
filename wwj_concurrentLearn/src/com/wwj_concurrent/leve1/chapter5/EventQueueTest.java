package com.wwj_concurrent.leve1.chapter5;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/8/27 14:49
 * @Modified By：
 */
public class EventQueueTest {

    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        // 生产者
        new Thread(() -> {
            for (; ; ) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer").start();

        // 消费者
        new Thread(() -> {
            for (; ; ) {
                try {
                    eventQueue.take();
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "customer").start();

    }
}
