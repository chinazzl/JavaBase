package com.wwj_concurrent.leve1.chapter6;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/1/25 10:52
 * @Modified By：
 */
public class ThreadException {

    private static final int A = 1;
    private static final int B = 0;

    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5_000);
                    int a = A / B;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        t.setUncaughtExceptionHandler((thread,e) -> {
            System.out.println(Thread.currentThread().getName());
            if (e instanceof RuntimeException) {
                System.out.println("出现异常 " + e);
            }
        });
    }
}
