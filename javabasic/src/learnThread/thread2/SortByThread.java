package learnThread.thread2;

public class SortByThread {

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        ThreadPrinter pa = new ThreadPrinter("A",c,a);
        ThreadPrinter pb = new ThreadPrinter("B",a,b);
        ThreadPrinter pc = new ThreadPrinter("C",b,c);

        new Thread(pa).start();
        Thread.sleep(100);
        new Thread(pb).start();
        Thread.sleep(100);
        new Thread(pc).start();
        Thread.sleep(100);
    }
}
