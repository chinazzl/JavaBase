package com.wwj_concurrent.leve2.classloader.DrivingLoading;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.classloader.DrivingLoading
 * @Description:
 * @Date: 2021/6/9 16:51
 */
public class DrivingLoadStaticField {

    static {
        String[] simple = new String[10];
        System.out.println(simple.length);
    }

    public static String init = "a";
}
