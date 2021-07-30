package com.wwj_concurrent.leve2.chapter1;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/2/23 13:54
 * @Modified By：
 * 单例模式： 饿汉式： 编译初始化就把对象创建
 * 懒汉式： 运行时根据需要进行创建调用
 * 满足要求： 1. 需要满足懒加载</br>
 *          2. 需要满足之创建一个实例，不能有线程安全问题</br>
 *          3. 满足性能要求
 *
 *  使用instanceHolder
 */
public class Singleton5 {

    private Singleton5() {
    }

    private static class InstanceHolder {
        private static final Singleton5 INSTANCE_HOLDER = new Singleton5();
    }

    public static Singleton5 getInstance() {
        return InstanceHolder.INSTANCE_HOLDER;
    }

}
