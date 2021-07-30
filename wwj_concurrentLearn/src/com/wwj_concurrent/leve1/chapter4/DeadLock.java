package com.wwj_concurrent.leve1.chapter4;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/12/22 23:09
 * @Modified By：
 * 线程A 持有R1 的锁等待货期R2的锁，线程B持有R2的锁等待获取R1的锁
 */
public class DeadLock {
    private OtherService otherService;
    private final Object LOCK = new Object();

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }
    public void m1() {
        synchronized (LOCK) {
            System.out.println("m1 ==>");
            otherService.s1();
        }
    }

    public void m2() {
        synchronized (LOCK) {
            System.out.println("m2 ==>");
        }
    }

}
