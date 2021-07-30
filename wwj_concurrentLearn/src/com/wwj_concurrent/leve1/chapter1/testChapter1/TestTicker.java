package com.wwj_concurrent.leve1.chapter1.testChapter1;

import com.wwj_concurrent.leve1.chapter1.TicketWindow;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/6/23 17:10
 * @Modified By：
 */
public class TestTicker {

    public static void main(String[] args) {
        /**
         * 使用 集成Thread类
         * Thread类不允许进行线程之间的共享， A线程 调用 B线程的run()方法
         */
        TicketWindow tw1 = new TicketWindow("stu1");
        TicketWindow tw2 = new TicketWindow("stu2");
        TicketWindow tw3 = new TicketWindow("stu3");

        tw1.start();
        tw2.start();
        tw3.start();

        /**
         *  使用Runnable 接口
         */
//        TicketWindowByRunnable twRunabled = new TicketWindowByRunnable("s1");
//
//        Thread t1 = new Thread(twRunabled);
//        t1.start();
//        Thread t2 = new Thread(twRunabled);
//        t2.start();
//
//        Thread t3 = new Thread(twRunabled);
//        t3.start();






    }
}
