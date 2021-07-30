package com.wwj_concurrent.leve2.chapter1;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/2/23 10:47
 * @Modified By：
 *  单例模式： 饿汉式： 编译初始化就把对象创建
 *           懒汉式： 运行时根据需要进行创建调用
 *  满足要求： 1. 需要满足懒加载
 *           2. 需要满足之创建一个实例，不能有线程安全问题
 *           3. 满足性能要求
 */
public class Singleton2 {
    private static Singleton2 INSTANCE;

    public Singleton2() {
    }

    /**
     * 满足懒加载
     * 有线程安全问题！
     * 一个线程执行if语句后 放弃CPU的执行权，然后第二个线程进入if语句 进行创建实例，返回后
     * 第一个线程 又重新创建新的实例，保证不了单例
     * @return
     */
    public static Singleton2 getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Singleton2();
        return INSTANCE;
    }
}
