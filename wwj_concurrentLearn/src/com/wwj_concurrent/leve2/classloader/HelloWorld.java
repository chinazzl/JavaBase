package com.wwj_concurrent.leve2.classloader;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.classloader
 * @Description:
 * @Date: 2021/6/16 15:57
 */
public class HelloWorld {
    static {
        System.out.println(" Hello world will be installed.");
    }

    public String welcome() {
        return "Hello world";
    }
}
