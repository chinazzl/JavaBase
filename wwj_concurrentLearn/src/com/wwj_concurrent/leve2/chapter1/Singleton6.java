package com.wwj_concurrent.leve2.chapter1;

import java.util.stream.IntStream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/2/23 14:06
 * @Modified By：
 * 单例模式： 饿汉式： 编译初始化就把对象创建
 * 懒汉式： 运行时根据需要进行创建调用
 * 满足要求： 1. 需要满足懒加载<br/>
 *          2. 需要满足之创建一个实例，不能有线程安全问题<br/>
 *          3. 满足性能要求
 * <p>
 * 使用枚举
 */
public class Singleton6 {
    private static Singleton6 INSTANCE;

    public Singleton6() {
    }

    /**
     * 使用枚举，因为枚举初始化只实例化一次
     */
    private enum SingletonEnum {
        INSTANCE;

        private final Singleton6 INSTANCE_EnumINST;

        SingletonEnum() {
            INSTANCE_EnumINST = new Singleton6();
        }

        private Singleton6 getINSTANCE_EnumINST() {
            return INSTANCE_EnumINST;
        }
    }

    public static Singleton6 getInstance() {
        return SingletonEnum.INSTANCE.getINSTANCE_EnumINST();
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
