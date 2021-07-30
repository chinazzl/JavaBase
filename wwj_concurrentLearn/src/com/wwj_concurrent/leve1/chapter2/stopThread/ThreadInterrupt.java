package com.wwj_concurrent.leve1.chapter2.stopThread;


/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/12/20 23:07
 * @Modified By：
 * JavaDoc:
 *  如果该线程阻塞的调用wait() ， wait(long) ，或wait(long, int)的方法Object类，
 *  或者在join() ， join(long) ， join(long, int) ， sleep(long) ，或sleep(long, int) ，
 *  这个类的方法，那么它的中断状态将被清除，并且将收到一个InterruptedException 。
 */
public class ThreadInterrupt {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("接收到打断信号");
                    // 在Lambda 表达式中需要使用Thread.interrupted(); 进行调用自己的是否被终端的方法。
                    System.out.println(Thread.interrupted());
                    e.printStackTrace();
                }
            }
        });
        t.start();
     /*   System.out.println("Main thread is interrupt:" + Thread.interrupted());
        Thread.currentThread().interrupt();
        System.out.println("Main thread is interrupt?:" + Thread.currentThread().isInterrupted());*/

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("主线程 接收打断信号");
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        /*当前线程正在执行可中断方法被阻塞，调用interrupt方法将其中断，会导致flag被清除*/

        t.interrupt();
        System.out.println(t.isInterrupted());
        System.out.println("t 线程 被打断");
    }


}
