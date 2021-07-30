package com.wwj_concurrent.leve1.chapter1;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/6/23 17:17
 * @Modified By：
 */
public class TicketWindowByRunnable implements Runnable {
    private final int MAX_CUSTOM = 50;

    private int custom_index = 1;

    private String customName;
    private final Object MONITOR = new Object();


    public TicketWindowByRunnable(String customName) {
        this.customName = customName;
    }

    @Override
    public void run() {
        synchronized (MONITOR) {
            while (custom_index <= MAX_CUSTOM) {
                try {
                    System.out.println("当前消费者 ：" + Thread.currentThread().getName() + " ;  排队号码为 ：" + custom_index++);
                    Thread.sleep(100_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("==>" + customName);
    }
}
