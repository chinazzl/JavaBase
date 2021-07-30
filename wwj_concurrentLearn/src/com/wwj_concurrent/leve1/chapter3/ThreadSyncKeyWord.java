package com.wwj_concurrent.leve1.chapter3;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/12/22 10:07
 * @Modified By：
 * 测试 同步关键字
 */
public class ThreadSyncKeyWord {
    static class innerThread extends Thread {
        private static Object MONITOR = new Object();

        @Override
        public void run() {
            synchronized (MONITOR) {
                try {
                    Thread.sleep(10_000_000);
                    System.out.println("创建新的thread");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class InnerThreadByRunnable implements Runnable {

        private final Object MONITOR = new Object();

        @Override
        public void run() {
            synchronized (MONITOR) {
                try {
                    Thread.sleep(10_000_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        InnerThreadByRunnable innerThreadByRunnable = new InnerThreadByRunnable();
        for (int i = 0; i < 5; i++) {
//            new innerThread().start();
//            new Thread(new InnerThreadByRunnable()).start();
            new Thread(innerThreadByRunnable).start();
            System.out.println("==>" + i);
        }
    }

}
