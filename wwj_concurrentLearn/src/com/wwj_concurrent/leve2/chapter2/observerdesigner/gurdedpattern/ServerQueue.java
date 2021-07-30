package com.wwj_concurrent.leve2.chapter2.observerdesigner.gurdedpattern;

import java.util.Random;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.gurdedpattern
 * @Description:
 * @Date: 2021/6/29 15:32
 */
public class ServerQueue extends Thread{

    private final RequestQueue requestQueue;

    private final Random random;

    private boolean isClosed = false;

    public ServerQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (!isClosed) {
            Request queue = requestQueue.getQueue();
            if (queue == null) {
                continue;
            }
            System.out.println("server queue is received -> " + queue.toString());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                return;
            }

        }
    }

    public void close() {
        this.isClosed = true;
        this.notifyAll();
    }
}
