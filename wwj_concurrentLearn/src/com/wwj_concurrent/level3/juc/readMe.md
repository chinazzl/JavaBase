JUC 并发包

1. CountDownLatch 门阀：多个线程 都就位后 getCount() == 0 后 进行下面的操作。
2. CyclicBarrier 屏障：多个线程启动 都执行到await() 后，再统一做其他事情
区别：
    1. CountDownLatch 不能reset，而CyclicBarrier是可以循环使用的
    2. 工作线程之间互不关心，工作线程必须等到同一个共同的点才去执行某个动作。