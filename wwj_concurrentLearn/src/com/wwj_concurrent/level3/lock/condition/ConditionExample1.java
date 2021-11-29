package com.wwj_concurrent.level3.lock.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.lock.condition
 * @Description: Condition的基本使用
 * @Date: 2021/11/28 14:16
 */
public class ConditionExample1 {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static int data;
    private static volatile boolean noUse = true;

    public static void main(String[] args) throws InterruptedException {
        Thread pThread = new Thread(() -> {
            for (; ; ) {
                ConditionExample1.createDataBuild();
            }
        });
        pThread.start();
        TimeUnit.SECONDS.sleep(2);
        Thread cThread = new Thread(() -> {
            for (; ; ) {
                ConditionExample1.consumeData();
            }
        });
        cThread.start();
    }

    /**
     * 创建 数据
     */
    private static void createDataBuild() {
        try {
            lock.lock();
            // 数据没有消费则等待数据消费，如果消费了 noUse 修改为 false
            while (noUse) {
                condition.await();
            }
            data++;
            System.out.println("p: " + data);
            TimeUnit.SECONDS.sleep(2);
            noUse = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void consumeData() {
        try {
            lock.lock();
            // 数据已经消费了，则等待生产数据，并且noUse 修改为 true
            while (!noUse) {
                condition.await();
            }
            System.out.println("c: " + data);
            noUse = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
