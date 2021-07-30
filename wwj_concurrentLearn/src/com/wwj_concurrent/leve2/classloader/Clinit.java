package com.wwj_concurrent.leve2.classloader;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.classloader
 * @Description:
 * @Date: 2021/6/11 10:28
 */
public class Clinit {
    /* 静态语句块只能对后面的静态变量进行赋值，不能对其进行访问。*/
//    static {
//        System.out.println(x); // Illegal forward reference
//        x = 100;
//    }
//    private static int x = 1;
    /*static class Parent {
        public static int value = 10;
        static {
            value = 20;
        }
    }

    static class Child extends Parent {
        private static  int i = value;
    }*/

    // 测试 是否 存在安全问题
    static {
        System.out.println(" This clss will be invoke");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //输出的是20 说明 父类的<clint>的方法 先得到了执行
//        System.out.println(Child.i);
        IntStream.rangeClosed(0, 5).forEach(i -> {
            new Thread(Clinit::new);
        });
    }
}
