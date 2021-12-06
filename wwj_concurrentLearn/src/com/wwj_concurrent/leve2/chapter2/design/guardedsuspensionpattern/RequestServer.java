package com.wwj_concurrent.leve2.chapter2.design.guardedsuspensionpattern;

/**
 * @author zhang zhao lin
 * @date 2021年06月27日 23:18
 * @Description 用来接收数据
 */
public class RequestServer extends Thread {

    private final RequestQuee requestQuee;

    private volatile boolean isClosed = false;

    public RequestServer(RequestQuee requestQuee) {
        this.requestQuee = requestQuee;
    }

    @Override
    public void run() {
        while (!isClosed) {
            Request request = requestQuee.getQueue();
            if (request == null) {
                continue;
            }
            System.out.println(" Rquest Sever is: " + request.toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void close() {
        this.isClosed = true;
        this.interrupt();
    }
}
