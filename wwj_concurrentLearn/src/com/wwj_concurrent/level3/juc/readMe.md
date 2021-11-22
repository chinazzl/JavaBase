JUC 并发包

1. CountDownLatch 门阀：多个线程 都就位后 getCount() == 0 后 进行下面的操作。
2. CyclicBarrier 屏障：多个线程启动 都执行到await() 后，再统一做其他事情
区别：
    1. CountDownLatch 不能reset，而CyclicBarrier是可以循环使用的
    2. 工作线程之间互不关心，工作线程必须等到同一个共同的点才去执行某个动作。
3. Exchanger：线程对之间的数据进行交换。
    注意：
        1. 一个线程对中，如果一个线程A执行交换后，另一个线程B执行慢导致线程A超时异常，线程B进入阻塞 状态。
        2. Exchanger 只适合线程对 进行交换，如果三个线程，则有一个线程无法交换进入阻塞状态。
4. Semaphores: 一个可用于执行具体线程的锁，可以规定一个count，具体有多少个线程可以执行锁。