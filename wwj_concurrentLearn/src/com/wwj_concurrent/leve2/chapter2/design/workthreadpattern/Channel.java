package com.wwj_concurrent.leve2.chapter2.design.workthreadpattern;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author zhang zhao lin
 * @date 2021年07月14日 21:44
 * @Description 传送带模式 worker
 */
public class Channel {
    private final Request[] requests;

    private final static int DEFAULT_MAX_REQUEST = 100;
    private int tail;

    private int head;

    private int count;

    private final WorkerThread[] workerThreads;


    public Channel(int works) {
        this.requests = new Request[DEFAULT_MAX_REQUEST];
        this.tail = 0;
        this.count = 0;
        this.head = 0;
        this.workerThreads = new WorkerThread[works];
        init();
    }

    /**
     * 初始化工人
     *
     * @param
     * @return
     * @date 2021/7/14 22:09
     */
    private void init() {
       /* for (int i = 0; i < workerThreads.length; i++) {
            workerThreads[i] = new WorkerThread("Worker-" + i, this);
        }*/
        IntStream.range(0, workerThreads.length).forEach(i -> {
            workerThreads[i] = new WorkerThread("Worker-" + i, this);
        });

    }

    /**
     * 开闸
     *
     * @param
     * @return
     * @date 2021/7/14 22:17
     */
    public void startWork() {
        Arrays.asList(workerThreads).forEach(WorkerThread::start);
    }

    public synchronized void put(Request request) {
        while (count >= requests.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requests[tail] = request;
        tail = (tail + 1) / requests.length;
        count++;
        this.notifyAll();
    }

    /**
     * 获取
     *
     * @param
     * @return
     * @date 2021/7/14 22:27
     */
    public synchronized Request take() {
        while (count <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request = requests[head];
        head = (head - 1) / requests.length;
        count--;
        this.notifyAll();
        return request;
    }
}
