package com.wwj_concurrent.leve2.chapter2.observerdesigner.gurdedpattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.chapter2.observerdesigner.gurdedpattern
 * @Description:
 * @Date: 2021/6/29 16:09
 */
public class GuardedSuspensionMain {
    public static void main(String[] args) throws InterruptedException {
        final RequestQueue requestQueue = new RequestQueue();
        ClientQueue clientQueue = new ClientQueue("Tom", requestQueue);
        clientQueue.start();
        ServerQueue serverQueue = new ServerQueue(requestQueue);
        serverQueue.start();


        Thread.sleep(10000L);
        serverQueue.close();
    }
}
