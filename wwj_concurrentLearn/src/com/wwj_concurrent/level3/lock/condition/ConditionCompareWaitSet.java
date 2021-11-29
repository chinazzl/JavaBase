package com.wwj_concurrent.level3.lock.condition;

import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.lock.condition
 * @Description: Condition 对比 Wait & Notify：都可以进行使线程进行阻塞（一个是进入Condition的阻塞队列中，一个是进入Monitor中的
 *              waitSet中），等待别人唤醒（Condition调用signal,Monitor调用notifyAll进行唤醒）或者interupt进行打断
 * @Date: 2021/11/29 1:19
 */
public class ConditionCompareWaitSet {

    private static final Object MONITOR = new Object();
    private static int data;
    // 数据是否没有被消费
    private static volatile boolean noUse = true;

    public static void main(String[] args) {

        new Thread(() -> {
            for (; ; ) {
                createData();
            }
        }, "productThread").start();
        new Thread(() -> {
            for (; ; ) {
                consumeData();
            }
        }, "consumeThread").start();
    }

    private static void createData() {
        synchronized (MONITOR) {
            while (noUse) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            data++;
            System.out.println("p: " + data);
            sleep(1);
            noUse = true;
            MONITOR.notifyAll();
        }
    }

    private static void consumeData() {
        synchronized (MONITOR) {
            while (!noUse) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("c: " + data);
            sleep(2);
            noUse = false;
            MONITOR.notifyAll();
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
