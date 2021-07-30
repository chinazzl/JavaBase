package com.wwj_concurrent.leve2.classloader;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.classloader
 * @Description:
 * @Date: 2021/6/16 10:32
 */
public class BootStrapClassLoader {
    public static void main(String[] args) {
        System.out.println("Bootstrap: " + String.class.getClassLoader() );
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
