本包下的代码为 汪文军多线程教程 的基础知识包。
### 多线程的自我学习

线程启动：

1. Java应用程序main函数是一个线程，在JVM 启动的时候调用，名字为main
2. 实现一个线程，必须创建Thread的实例，并且override run方法，并且调用start方法
3. 在JVM启动后，实际上有多个线程，但是至少有一个非守护线程
4. 当你调用一个线程start方法的时候，此时至少有两个线程，一个是调用你的线程main，还有一个是执行run方法的线程

1. 线程的生命周期分为5个阶段： 
   * NEW：通过start方法进入RUNNABLE状态
   * RUNNABLE：具备CPU调度的执行资格，只能意外终止或者进入RUNNING状态
   * RUNNING：
     * 直接进入TERMINATED状态，比如调用JDK已经不推荐是以哦那个的stop方法或者判断某个逻辑标识
     * 进入BLOCKED状态，比如调用了sleep，或者wait方法而进入了BLOCKED状态
     * 进行某个阻塞的IO操作，比如因网络数据的读写而进入了BLOCKED状态
     * 获取某个锁资源，从而加入到该锁的阻塞队列钟而进入了BLOCKED状态
     * 由于CPU的调度器轮询使该线程放弃执行，进入RUNNABLE状态
     * 线程主动调用yield方法，放弃CPU的执行权，进入RUNNABLE状态
   * BLOCKED：状态切换
     * 直接进入TERMINATED状态
     * 线程阻塞的操作结束，比如读取了想要的数据字节进入到了RUNNABLE状态
     * 线程完成了指定时间的休眠，进入到了RUNNABLE状态
     * Wait中的线程被其他线程notify/notifyall唤醒，进入RUNNABLE状态
     * 线程获取到了某个锁资源，进入RUNNABLE状态
     * 线程在阻塞过程中被打断，比如其他线程调用了interrupt方法，进入RUNNABLE状态
   * TERMINATED 是一个线程的最终状态，不会切换到任何状态，下列情况将会使线程进入TERMINATED状态
     * 线程运行正常结束，结束生命周期
     * 线程已婚行出错意外结束
     * JVM Crash 导致所有的线程都结束

![](C:\Users\Julyan\Desktop\照片\threadCycle.jpg)

2. Thread 的API

   1. Thread 构造：

      1. 创建线程对象Thread，默认有一个线程名，以Thread-开头，从0开始计数

      2. 如果在构造Thread的时候没有传递Runnable或者没有复写Thread的run犯法，该Thread将不会调用任何东西，如果传递了Runnable接口的实例，或者复写了Thread的run方法，则会执行该方法的逻辑单元（逻辑代码）

         

      3. - | `Thread()`  分配一个新的 `Thread`对象。                      |
           | ------------------------------------------------------------ |
           | `Thread(Runnable target)`  分配一个新的 `Thread`对象。       |
           | `Thread(Runnable target, String name)`  分配一个新的 `Thread`对象。 |
           | `Thread(String name)`  分配一个新的 `Thread`对象。           |
           | `Thread(ThreadGroup group, Runnable target)`  分配一个新的 `Thread`对象。 |
           | `Thread(ThreadGroup group, Runnable target, String name)`  分配一个新的 `Thread`对象，使其具有  `target`作为其运行对象，具有指定的 `name`作为其名称，属于  `group`引用的线程组。 |
           | `Thread(ThreadGroup group, Runnable target, String name,  long stackSize)`  分配一个新的 `Thread`对象，以便它具有  `target`作为其运行对象，将指定的 `name`正如其名，以及属于该线程组由称作  `group` ，并具有指定的 *堆栈大小* 。 |
           | `Thread(ThreadGroup group, String name)`  分配一个新的 `Thread`对象。 |

      4. Thread.setDeamon() 设置守护线程，会随着主线程的销毁而结束

   2. sleep() 和 yield()

      1. sleep() :sleep方法会使当前线程进入指定毫秒数的休眠，暂停执行，虽然给定了一个休眠的时间，但是最终要以系统的定时器和调度器的精度为准，休眠有一个特性，不会放弃monitor锁的所有权

         - 使用sleep方法的时候，可以使用TimeUnit配合使用，JDK1.5 引用TimeUnit对sleep进行封装，线程休眠3小时24分17秒88毫秒，可以这样写

         ``` java
         Thread.sleep();
         TimeUnit.HOURS.sleep(3);
         TimeUnit.MINUTES.sleep(24);
         TimeUnit.SECONDS.sleep(17);
         TimeUnit.MILLISECONDS.sleep(88);
         ```

      2. yield(): 一种启发式的方法，提醒调度器自我愿意放弃当前的CPU资源，如果CPU的资源不紧张，则会忽略这种提醒
         
         - 调用yield方法会使当前线程从RUNNING状态切换到RUNNABLE状态，一般这个方法并不常用
      3. 区别：
         * sleep会到强制当前线程暂停指定的事件，没有CPU时间片的消耗
         * yield只是对CPU调度器的一个展示，如果CPU调度器没有忽略这个提示，它会导致线程上下文的切换。
         * sleep会使线程短暂block，会在给定的事件内释放CPU资源
         * yield会使RUNNING状态的Thread进入RUNNABLE状态 （如果CPU调度器没有忽略这个提示）
         * sleep几乎百分之百完成给定时间的休眠，而yield的提示并不能一定担保
      * 一个线程sleep另一个线程调用interrupt会捕获到中断信号，而yield不会

   3. 设置线程的优先级： public void final setPriority(int new Priority) 为线程设定优先级
      - 对于root用户，它会hint操作系统你想要设置的优先级别，否则它会被忽略
      - 如果CPU比较忙，设置优先级可能会获取更多的CPU时间片，但是闲时优先级的高低几乎不会有任何作用
      - 线程的优先级不能小于1也不能大于10 ， 如果指定的线程优先级大于线程所在group的优先级，那么指定的优先级将会失效，取而代之的是group的最大优先级
      - 线程默认的优先级和它的父类保持一致，一般情况下是5，因为main线程的优先级为5

   4. 线程interrupt
      - public void interrupt()
        
        - 如果另外一个线程调用被阻塞的线程的interrupt方法，则会打断这种阻塞，一旦在线程在阻塞的情况下被打断，都会抛出一个称为InterruptedExceptiuon的异常，这个异常就像一个signal一样通知当前线程被打断。
      - public static boolean interrupted()
      - public boolean isInterrupted()

      4.1 如何暴力结束线程
      
      - 

   5. 线程Join 例如： A.join();
      1.  join某个线程A，会使当前线程B进入等待，直到线程A结束生命周期，或者到达指定的时间，那么在此期间B线程是处于BLOCKED的，而不是A线程
      2. join方法会使当前线程永远的等待下去，直到期间被另外的线程中断，或者join的线程执行结束，当然你也可以使用join的另外两个重载方法，指定毫秒数，在指定的时间到达之后，当前线程也会退出阻塞。

   6. 如何关闭一个线程？
      1. 正常关闭：
         - 线程的生命周期正常结束
      - 捕获中断信号关闭线程
         - 使用volatile开关进行控制
         - 异常退出
           - 在一个线程执行单元中，不允许抛出checked异常，不论Thread中的run方法，还是Runnable中的run方法，如果线程在运行过程中需要捕获checked异常并且判断是否还有运行下去的必要，那么从事可以将checked异常封装成unchecked异常（RuntimeException）抛出进而结束线程的生命周期

   7. 线程安全与数据同步
      1. Synchronized关键字
         1. Synchronized 关键字提供了一种锁机制，能够确保共享变量的互斥访问，从而防止数据不一致出现的问题
         2. Synchronized 关键字包括monitor enter 和 monitor exit 两个JVM指令，它能够保证在任何时候任何线程执行到monitor　enter成功之前都必须从主内存中获取数据，而不是从缓存中，在monitor　exit运行成功之后，共享变量被更新后的值必须刷入主内存
         3. Synchronized的指令严格遵守Java　happen－before　规则，一个monitor　exit 指令之前必定要有一个monitor enter
            1. Monitor enter：每一个都与一个monitor相关联，一个monitor的lock 的锁只能被一个线程在同一时间获得，在一个线程尝试获得与对象关联 monitor 得所有权时 会发生如下几件事情
               - 如果monitor 得计数器为0 则意味着该monitor 得lock还没有被获得，某个线程获得之后将立即对该计数器加一，从此该线程就是这个monitor 得所有者
            - 如果一个已经拥有该monitor所有权得线程冲入，则会导致monitor计数器再次累加
               - 如果monitor 已经被其他线程所拥有，则其他线程尝试获取该monitor得所有权时，会被陷入阻塞状态直到monitor计数器变为0，才能再次尝试获取对monitor得所有权
         2.  Monitorexit
             
               - 释放对monitor 得所有权，想要释放对某个对象关联得monitor得所有权的前提是，你曾经获得了所有权，释放monitor所有权的过程较为简单，就是将monitor的计数器减一，如果计数器的结果为0，那就意味着该线程不在拥有对该monitor的所有权，通俗地讲就是解锁。与此同时被该monitor block 的线程将再次尝试获得对该monitor的所有权
         
      2. Synchronized 作用域

         1. 作用在方法上

            ``` java
            public class TreadExtends extends Thread {
                @Override
                public synchronized void run() {
                    // ... do somethings
                }
            }
            ```

            

         2. 作用在局部代码块

            ``` java
            public class ThreadRunnabled implements Runnable {
                private final static Object MONITOR = new Object();
                @Override
                public void run() {
                    synchronized(MONITOR){
                        // ... do somethings
                    }
                }
            }
            ```

      3. this MOnitor 和 Class Monitor

   8. wait 和 notify

      1. wait
         - wait 方法的三个重载方法都将调用wait(long timeout) 这个方法，默认使用wait()等价于wait(0)，0代表永不超时
         - Object的notify 或者 notifyAll 方法才能将其唤醒，或者阻塞时间到达了timeout时间而自动唤醒
         - wait方法必须拥有该对象的monitor，也就是wait方法必须在同步方法中使用
         - 当前线程执行了该对象的wait方法之后，将会放弃该monitor的所有权并且进入与该对象关联的wait set中，也就是说一旦线程执行了某个object的wait方法之后，它就会释放对该对象monitor的所有权，其他线程也会有机会继续争抢该monitor的所有权
   2. notify `public final native void notify();`
         - 唤醒单个正在执行该对象wait方法的线程。
      - 如果有某个线程由于执行该对象的wait方法而进入阻塞则会被唤醒，如果没有则会忽略。
         - 被唤醒的线程需要重新获取对该对象所关联monitor的lock才能继续执行。

   9. wait 和 sleep

      - wait 和 sleep 方法都可以使线程进入阻塞状态
   - wait和 sleep 方法均是可中断方法，被中断后都会收到中断异常
      - wait 是Object 的方法，而 sleep 是Thread 特有的方法
      - wait方法的执行必须在同步方法中进行，而sleep则不需要
      - 线程在同步方法中执行sleep方法时，并不会释放monitor的锁，而wait方法则会释放monitor的锁，会放到等待序列中
   - sleep不需要被唤醒，而wait需要。
   
   

#### 线程池

1. 任务队列 √
2. 拒绝策略（抛出异常、直接丢弃、阻塞、临时队列）√
3. init 初始化值 √
4. active √
5. max √

 