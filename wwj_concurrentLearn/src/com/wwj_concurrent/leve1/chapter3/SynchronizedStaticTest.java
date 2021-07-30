package com.wwj_concurrent.leve1.chapter3;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/12/22 22:34
 * @Modified By：
 */
public class SynchronizedStaticTest {
    public static void main(String[] args) {
        new Thread("mthread1"){
            @Override
            public void run() {
                SynchronizedStatic.m1();
            }
        }.start();

        new Thread("mthread2"){
            @Override
            public void run() {
                SynchronizedStatic.m2();
            }
        }.start();

        new Thread("mthread3"){
            @Override
            public void run() {
                SynchronizedStatic.m3();
            }
        }.start();
    }
}
