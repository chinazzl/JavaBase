package com.wwj_concurrent.leve1.chapter2.stopThread;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/12/21 16:40
 * @Modified By：
 *  暴力终止线程
 */
public class ThreadForce {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ThreadService threadService = new ThreadService();
        threadService.execute(() -> {
//            while(true) {
//
//            }
            try {
                Thread.sleep(2000);
                System.out.println("任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadService.shutDown(10000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
