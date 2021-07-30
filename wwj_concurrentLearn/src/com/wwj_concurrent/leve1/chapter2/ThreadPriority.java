package com.wwj_concurrent.leve1.chapter2;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/7/9 11:12
 * @Modified By：
 */
public class ThreadPriority {

    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("test");
        tg.setMaxPriority(7);

        Thread thead_1 = new Thread(tg, "testThread");
        thead_1.setPriority(10);

        System.out.println(thead_1.getPriority());
    }
}
