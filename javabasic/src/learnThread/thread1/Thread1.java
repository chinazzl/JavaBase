package learnThread.thread1;

public class Thread1 extends Thread {

    private String name;

    public Thread1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("子线程" + Thread.currentThread().getName() + "运行开始");
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行 ： " + i);
            try {
                sleep((int)Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("子线程" + Thread.currentThread().getName() + "运行结束");
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "启动main线程");
        Thread1 t1 = new Thread1("thread1");
        Thread1 t2 = new Thread1("thread2");
        t1.start();
        t2.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "主线程结束");

    }
}
