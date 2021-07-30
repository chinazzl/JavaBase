package com.wwj_concurrent.leve2.classloader.DrivingLoading;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.classloader.DrivingLoading
 * @Description:
 * @Date: 2021/6/9 17:10
 */
public class DrivingLoadStaticMethod {
    static {
        System.out.println("THis will be installed");
    }

    public static void init() {

    }
}
