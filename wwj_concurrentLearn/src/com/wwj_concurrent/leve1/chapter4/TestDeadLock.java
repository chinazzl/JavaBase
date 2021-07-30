package com.wwj_concurrent.leve1.chapter4;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/12/22 23:44
 * @Modified By：
 */
public class TestDeadLock {
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);

        new Thread(() -> {
            while (true) {
                deadLock.m1();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                otherService.s2();
            }
        }).start();
    }
}
