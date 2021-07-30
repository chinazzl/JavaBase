package com.wwj_concurrent.leve1.chapter4.productAndCusume;

import java.util.stream.Stream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/1/14 11:11
 * @Modified By：
 * 在多线程状态下的生产者和消费者
 */
public class ProduceAndConsumeVersion3 {
    private final Object LOCK = new Object();

    private int i = 0;

    //是否 已经被生产过
    private Boolean isProducted = false;

    //生产者
    private void produce() {
        synchronized (LOCK) {
            while (isProducted) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            LOCK.notifyAll();
            isProducted = true;
            System.out.println(Thread.currentThread().getName() + " ==> 已经生产了 " + i);

        }
    }

    //消费者
    private void consumer() {
        synchronized (LOCK) {
            while (!isProducted) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "==> 已经消费了 " + i);
            isProducted = false;
            LOCK.notify();
        }
    }

    public static void main(String[] args) {
        ProduceAndConsumeVersion3 p3 = new ProduceAndConsumeVersion3();
        Stream.of("p1", "p2", "p3").forEach(p -> {
            new Thread(() -> {
                while (true) {
                    p3.produce();
                }
            }, p).start();
        });

        Stream.of("c1", "c2", "c3").forEach(c -> {
            new Thread(() -> {
                while (true) {
                    p3.consumer();
                }
            }, c).start();
        });


    }

}
