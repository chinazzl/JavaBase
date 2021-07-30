package com.wwj_concurrent.leve2.chapter1;

import java.util.stream.IntStream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/2/23 11:09
 * @Modified By：
 * 单例模式： 饿汉式： 编译初始化就把对象创建
 * 懒汉式： 运行时根据需要进行创建调用
 * 满足要求： 1. 需要满足懒加载
 * 2. 需要满足之创建一个实例，不能有线程安全问题
 * 3. 满足性能要求
 */
public class Singleton4 {
    private static Singleton4 INSTANCE;

    public Singleton4() {
    }

    /**
     * double check
     * 满足懒加载
     * 线程安全
     * 性能较好
     * 有时会导致空指针问题,原因是因为jvm的指令重排，jvm在代码执行中，
     * 虽然结果符合预期，但执行中无法确定执行的顺序
     *
     * @return
     */
    public static Singleton4 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton4.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton4();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {

        IntStream.rangeClosed(0, 100).forEach(i -> new Thread() {
            @Override
            public void run() {
                System.out.println(getInstance());
            }
        }.start());
    }

}
