package com.wwj_concurrent.leve2.chapter2.design.workthreadpattern;

/**********************************
 * @author zhang zhao lin
 * @date 2021年07月14日 23:11
 * @Description
 **********************************/
public class WorkerThreadClient {
    public static void main(String[] args) {
        final Channel channel = new Channel(50);
        channel.startWork();
        new TransportThread("Tom",channel).start();
        new TransportThread("Jerry",channel).start();
        new TransportThread("Scott",channel).start();
    }
}
