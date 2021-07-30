package com.wwj_concurrent.leve2.chapter1;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/2/23 10:23
 * @Modified By：
 *  单例模式： 饿汉式： 编译初始化就把对象创建
 *           懒汉式： 运行时根据需要进行创建调用
 *  满足要求： 1. 需要满足懒加载
 *           2. 需要满足之创建一个实例，不能有线程安全问题
 *           3. 满足性能要求
 */
public class Singleton1 {
    //饿汉式 不满足懒加载
    private static final Singleton1 INSTANCE = new Singleton1();

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}
