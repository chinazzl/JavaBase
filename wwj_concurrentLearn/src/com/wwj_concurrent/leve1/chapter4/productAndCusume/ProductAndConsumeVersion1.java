package com.wwj_concurrent.leve1.chapter4.productAndCusume;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/12/23 14:14
 * @Modified By：
 *  生产者消费者 v1
 *
 */
public class ProductAndConsumeVersion1 {
    private int i = 0;

    private final Object LOCK = new Object();

    public void product() {
        synchronized (LOCK) {
            i++;
            System.out.println("p 生产者 ==>" + i);
        }
    }

    public void cusume() {
        synchronized (LOCK) {
            System.out.println("c 消费者 ==>" + i);
        }
    }

    public static void main(String[] args) {
        //这样会导致 生产者一直生产，消费者只能消费最后一次生产者生产的数据
        ProductAndConsumeVersion1 pac = new ProductAndConsumeVersion1();
        new Thread(() -> {
            while (true) {
                pac.product();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                pac.cusume();
            }
        }).start();
    }
}
