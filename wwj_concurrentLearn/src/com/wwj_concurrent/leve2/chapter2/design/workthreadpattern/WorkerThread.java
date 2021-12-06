package com.wwj_concurrent.leve2.chapter2.design.workthreadpattern;

import java.util.Random;

/**********************************
 * @author zhang zhao lin
 * @date 2021年07月14日 22:00
 * @Description 工人干活
 **********************************/
public class WorkerThread extends Thread {

    private final Channel channel;

    private static final Random random = new Random(System.currentTimeMillis());

    public WorkerThread(String workerName, Channel channel) {
        super(workerName);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                channel.take().execute();
                Thread.sleep(random.nextInt(1_000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
