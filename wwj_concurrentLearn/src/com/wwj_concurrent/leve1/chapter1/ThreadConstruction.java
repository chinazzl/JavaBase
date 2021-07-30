package com.wwj_concurrent.leve1.chapter1;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/6/23 17:30
 * @Modified By：
 */
public class ThreadConstruction {

    public static void main(String[] args) {
        Thread  t1 = new Thread();
        ThreadGroup tg1 = new ThreadGroup("tg1");

        Thread  t2 = new Thread(tg1,"t2");
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();

        System.out.println("Main Thread Group belong group " + mainThreadGroup.getName());
        System.out.println("t1 and main belong the same group :" + (mainThreadGroup == t1.getThreadGroup()));
        System.out.println("t2 thread group not belong main group:" + (mainThreadGroup == t2.getThreadGroup()));
        System.out.println("t2 thread group belong main TestGroup :" + (tg1 == t2.getThreadGroup()));
    }
}
