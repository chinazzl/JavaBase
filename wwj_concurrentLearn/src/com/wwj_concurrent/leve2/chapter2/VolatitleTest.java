package com.wwj_concurrent.leve2.chapter2;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/2/24 11:06
 * @Modified By：
 * 学习 volatile
 */
public class VolatitleTest {

    //保证内存可见性，和程序执行的有序性 happens-before
    private static int INIT_VALUE = 0;

    private static final int MAX_VALUE = 50;

    public static void main(String[] args) {
        new Thread(() -> {
            int local_value = INIT_VALUE;
            while (local_value < MAX_VALUE) {
                if (local_value != INIT_VALUE) {
                    System.out.printf("The after updated num is [%d].\n", local_value);
                    local_value = INIT_VALUE;
                }
            }
        }, "READER").start();


        new Thread(() -> {
            int local_value = INIT_VALUE;
            while (local_value < MAX_VALUE) {
                System.out.printf("update local_value is %d\n", local_value++);
                INIT_VALUE = local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATED").start();
    }
}
