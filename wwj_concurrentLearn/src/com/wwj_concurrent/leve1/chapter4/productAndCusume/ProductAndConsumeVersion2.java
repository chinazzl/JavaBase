package com.wwj_concurrent.leve1.chapter4.productAndCusume;

import java.util.stream.Stream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/12/23 14:33
 * @Modified By：
 */
public class ProductAndConsumeVersion2 {

    private final Object LOCK = new Object();

    private int i = 0;

    //是否 已经被生产过
    private Boolean isProducted = false;

    public void product() {
        synchronized (LOCK) {
            if (isProducted) {
                //表示已经生产过
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println(Thread.currentThread().getName() + " 生产者 ==> " + i);
                LOCK.notify();
                isProducted = true;
            }
        }
    }

    public void consume() {
        synchronized (LOCK) {
            if (isProducted) {
                System.out.println(Thread.currentThread().getName() + " 消费者 ==> " + i);
                LOCK.notify();
                isProducted = false;
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        ProductAndConsumeVersion2 pac = new ProductAndConsumeVersion2();
        //多线程下的生产者和消费者
        Stream.of("P1", "P2").forEach((p) -> {
            new Thread(p) {
                @Override
                public void run() {
                    while (true) {
                        pac.product();
                    }
                }
            }.start();
        });

        Stream.of("C1", "C2").forEach((c) -> {
            new Thread(c) {
                @Override
                public void run() {
                    while (true) {
                        pac.consume();
                    }
                }
            }.start();
        });
       /* new Thread(() -> {
            while (true) {
                pac.product();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                pac.consume();
            }
        }).start();*/
    }
}
