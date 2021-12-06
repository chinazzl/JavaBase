package com.wwj_concurrent.leve2.chapter2.design.singletonthread;

/**
 * @author zhang zhao lin
 * @date 2021年06月21日 21:58
 * @Description: 只有一个人能够进入门
 */
public class SingletonDesignMain {
    public static void main(String[] args) {
        Gate gate = new Gate();

        User bj = new User("Beijing","BaoBao",gate);
        User sh = new User("Shanghai","GuangLao",gate);
        User gz = new User("Guangzhou","ShangLao",gate);

        bj.start();
        sh.start();
        gz.start();
    }
}
