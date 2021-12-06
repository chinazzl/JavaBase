package com.wwj_concurrent.leve2.chapter2.design.workthreadpattern;

import java.util.Random;

/**********************************
 * @author zhang zhao lin
 * @date 2021年07月14日 22:35
 * @Description 传送带
 **********************************/
public class TransportThread extends Thread {
    private Channel channel;
    private static final Random random = new Random(System.currentTimeMillis());

    public TransportThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; ; i++) {
                Request request = new Request(getName(), i);
                channel.put(request);
                Thread.sleep(random.nextInt(1_000));
            }
        } catch (Exception e) {

        }
    }
}
