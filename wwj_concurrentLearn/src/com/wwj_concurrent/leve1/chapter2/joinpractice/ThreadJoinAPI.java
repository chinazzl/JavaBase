package com.wwj_concurrent.leve1.chapter2.joinpractice;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/12/19 0:40
 * @Modified By：
 *
 *  Join : 当前线程 等待 子线程结束后再结束
 *  本例子中 当前线程属于 main 线程， 如果需要 每个线程进行采集 数据， 则需要 每个线程进行 执行成功后，结束main线程
 */
public class ThreadJoinAPI {
    public static void main(String[] args) throws InterruptedException {
        Long startTimeStamp = System.currentTimeMillis();
        Thread t1 = new Thread(new CaptureRunnable("M1",1000L));
        Thread t2 = new Thread(new CaptureRunnable("M2",30_000L));
        Thread t3 = new Thread(new CaptureRunnable("M3",15_000L));

        t1.start();
        t2.start();
        t3.start();

        // join 方法需要 在调用start 方法后 执行 方可有效
        t1.join();
        t2.join();
        t3.join();

        Long endTimeStamp = System.currentTimeMillis();
        System.out.printf("Save data begin timestamp is:%s, end timestamp is:%s\n", startTimeStamp, endTimeStamp);
    }
}

class CaptureRunnable implements Runnable {

    private String machineName;

    private Long spendTime;

    public CaptureRunnable(String machineName, Long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        // do something ...
        try {
            TimeUnit.MILLISECONDS.sleep(spendTime);
            System.out.printf(machineName + " completed data capture at timestamp [%s] and successfully.\n", System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}