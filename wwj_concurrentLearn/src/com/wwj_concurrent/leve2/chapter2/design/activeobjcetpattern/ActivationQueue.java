package com.wwj_concurrent.leve2.chapter2.design.activeobjcetpattern;

import java.util.LinkedList;

/**********************************
 * @author zhang zhao lin
 * @date 2021年08月03日 23:11
 * @Description
 **********************************/
public class ActivationQueue {
    private final static int MAX_METHOD_REQUEST_QUEUE_SIZE = 100;

    private final LinkedList<MethodRequest> queue;

    public ActivationQueue() {
        queue = new LinkedList<>();
    }

    public synchronized void put(MethodRequest request) {
        while (queue.size() > MAX_METHOD_REQUEST_QUEUE_SIZE) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.addLast(request);
        this.notifyAll();
    }

    public synchronized MethodRequest take() {
        while (queue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        return queue.getFirst();
    }

}
