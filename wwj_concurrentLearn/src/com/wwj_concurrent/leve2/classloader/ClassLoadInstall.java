package com.wwj_concurrent.leve2.classloader;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.classloader.DrivingLoading
 * @Description:
 * @Date: 2021/6/9 17:01
 */
public class ClassLoadInstall {
    //2
    private static ClassLoadInstall instance = new ClassLoadInstall();
    //1
    private static int x = 0;

    private static int y;

    public ClassLoadInstall() {
        x++;
        y++;
    }

    public static ClassLoadInstall getInstance() {
        return instance;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoadInstall instance = ClassLoadInstall.getInstance();
        System.out.println(instance.x);
        System.out.println(instance.y);
    }
}
