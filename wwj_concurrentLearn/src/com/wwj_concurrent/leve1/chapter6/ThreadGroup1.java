package com.wwj_concurrent.leve1.chapter6;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/1/20 15:32
 * @Modified By：
 */
public class ThreadGroup1 {
    public static void main(String[] args) {
        /*Thread t1 = new Thread("TG1"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(this.getThreadGroup().getParent().activeCount());
            }
        };
        t1.start();*/
        ThreadGroup tg1 = new ThreadGroup("tg1");
       /* Thread t2 = new Thread(tg1, () -> {
            System.out.println("currentThread group Name is ==>" + Thread.currentThread().getThreadGroup().getName());
            System.out.println("currentThread group parent Name is ==>" + Thread.currentThread().getThreadGroup().getParent().getName());
        }, "t2");
        t2.start(); // 1. main 2. tg2*/

        ThreadGroup tg2 = new ThreadGroup(tg1, "tg2");
        System.out.println("threadGroup Name is ==> " + tg2.getName()); //tg2
        System.out.println("The threadGroup Of parent is ==> " + tg2.getParent()); //tg1
    }
}
