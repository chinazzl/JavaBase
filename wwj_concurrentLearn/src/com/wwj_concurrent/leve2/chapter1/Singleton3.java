package com.wwj_concurrent.leve2.chapter1;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/2/23 11:00
 * @Modified By：
 *  单例模式： 饿汉式： 编译初始化就把对象创建
 *           懒汉式： 运行时根据需要进行创建调用
 *  满足要求： 1. 需要满足懒加载
 *           2. 需要满足之创建一个实例，不能有线程安全问题
 *           3. 满足性能要求
 */
public class Singleton3 {

    private static Singleton3 INSTANCE;

    public Singleton3() {
    }

    /**
     * 满足懒加载
     * 线程安全
     * 在读的时候有性能问题
     * @return
     */
    public synchronized Singleton3 getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Singleton3();
        return INSTANCE;
    }
}
