package org.javabase.beRunable;

/**
 * Created by Felix on 2017/7/31.
 */
public class Thread2 {
    public static void main(String[] args) throws InterruptedException {
//        MutiThreadRunable threadA = new MutiThreadRunable("A");
//        MutiThreadRunable threadB = new MutiThreadRunable("B");
//        threadA.run();
//        threadB.run();
        System.out.println(Thread.currentThread().getName() + "线程开始启动");
        Thread thread1 = new Thread(new MutiThreadRunable("A"));
        Thread thread2 = new Thread(new MutiThreadRunable("B"));
        thread2.start();
        thread1.start();
        thread1.join();
        thread2.join();
        System.out.println(Thread.currentThread().getName() + "线程终止");

    }
}
