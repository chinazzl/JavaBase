package org.javabase.threadLearn.threader;

/**
 * yield()方法指的是当运行中的线程调用yield方法会转到可运行状态，当时间片段结束后再运行
 * 把运行的机会转让给优先级别高的其他线程
 */
public class yieldTest extends Thread{

    private int sb;
    public yieldTest() {
    }

    public yieldTest(int sb) {
        this.sb = sb;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if(i <= sb){
                Thread.yield();
            }
            System.out.println("线程---->"+sb);
        }
    }
}

class smTest {
    public static void main(String[] args) {
        yieldTest yieldTest1 = new yieldTest(5);
        yieldTest yieldTest2 = new yieldTest(21);
        yieldTest1.start();
        yieldTest2.start();
    }
}