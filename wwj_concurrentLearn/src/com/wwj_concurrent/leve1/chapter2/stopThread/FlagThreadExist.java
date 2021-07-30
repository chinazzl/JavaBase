package com.wwj_concurrent.leve1.chapter2.stopThread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/7/24 14:36
 * @Modified By：
 * 使用Volatile 进行控制 线程 是否终止
 */
public class FlagThreadExist {

    static class MyTask extends Thread {
        private volatile boolean closed = true;

        @Override
        public void run() {
//            while (!closed && !isInterrupted()) {
//
//            }
            while (closed) {

            }
            System.out.println("I will be existing");
        }

        public void close() {
            this.closed = false;
//            this.interrupt();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        MyTask myTask = new MyTask();
        myTask.start();
        TimeUnit.SECONDS.sleep(10);
        System.out.println(" System wull be shutdown");
        myTask.close();
    }
}
