package com.wwj_concurrent.leve1.chapter1.video;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/11/2 15:16
 * @Modified By：
 * Thread所有的构造方法
 * Thread()
 * 分配一个新的 Thread对象。
 * Thread(Runnable target)
 * 分配一个新的 Thread对象。
 * Thread(Runnable target, String name)
 * 分配一个新的 Thread对象。
 * Thread(String name)
 * 分配一个新的 Thread对象。
 * Thread(ThreadGroup group, Runnable target)
 * 分配一个新的 Thread对象。
 * Thread(ThreadGroup group, Runnable target, String name)
 * 分配一个新的 Thread对象，使其具有 target作为其运行对象，具有指定的 name作为其名称，属于 group引用的线程组。
 * Thread(ThreadGroup group, Runnable target, String name, long stackSize)
 * 分配一个新的 Thread对象，以便它具有 target作为其运行对象，将指定的 name正如其名，以及属于该线程组由称作 group ，并具有指定的 堆栈大小 。
 * Thread(ThreadGroup group, String name)
 * 分配一个新的 Thread对象。
 */
public class ThreadConstructors {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("========");
            }
        };
        Thread t2 = new Thread();
        t1.start();
        t2.start();
        System.out.println(t1.getName());
        System.out.println(t2.getName());

        Thread t3 = new Thread("MyThread");
        t3.start();
        System.out.println(t3.getName());

        Thread t4 = new Thread(() -> {
            System.out.println("none name Of Thread Runnable");
        });
        t4.start();
        System.out.println(t4.getName());
        Thread t5 = new Thread(() -> {
            System.out.println("Thread runnable");
        }, "Runnable Thread");
        t5.start();
        System.out.println(t5.getName());

    }
}
