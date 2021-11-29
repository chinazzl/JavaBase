package com.wwj_concurrent.level3.lock.condition;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.lock.condition
 * @Description:
 * @Date: 2021/11/29 13:58
 */
public class ProductAndConsumerCondition {
    // 创建一个非公平的锁
    private static final Lock lock = new ReentrantLock();
    // 创建两个 lock condition
    private static final Condition PRODUCT_CONDITION = lock.newCondition();
    private static final Condition CONSUMER_CONDITION = lock.newCondition();
    // 创建数据 容器
    private static final LinkedList<Long> datas = new LinkedList<>();

    private static final int MAX_CAPACITY_SIZE = 100;

    public static void main(String[] args) {
        IntStream.range(0, 5).boxed().forEach(ProductAndConsumerCondition::beginProduct);
        IntStream.range(0, 13).boxed().forEach(ProductAndConsumerCondition::beginConsumer);
    }

    private static void beginProduct(int i) {
        new Thread(() -> {
            for (; ; ) {
                createData();
                sleep(2);
            }
        }).start();
    }

    private static void beginConsumer(int i) {
        new Thread(() -> {
            for (; ; ) {
                useData();
                sleep(3);
            }
        }).start();
    }

    private static void createData() {
        try {
            lock.lock();
            while (datas.size() >= MAX_CAPACITY_SIZE) {
                PRODUCT_CONDITION.await();
            }
            long data = System.currentTimeMillis();
            datas.addLast(data);
            System.out.println("p: " + data);
            CONSUMER_CONDITION.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void useData() {
        try {
            lock.lock();
            while (datas.isEmpty()) {
                CONSUMER_CONDITION.await();
            }
            Long firstData = datas.removeFirst();
            System.out.println("c: " + firstData);
            PRODUCT_CONDITION.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
