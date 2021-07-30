package learnThread.thread3;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/2/5 13:49
 * @Modified By：
 */
public class TestYield {
    public static void main(String[] args) {

        ThreadYield th1 = new ThreadYield("张三");
        ThreadYield th2 = new ThreadYield("李四");
        th1.start();
        th2.start();
    }

    static class ThreadYield extends Thread {
        private String name;

        public ThreadYield(String name){
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                System.out.println("当前线程 ==" + this.getName() + " ==" + i);
                if (i==30){
                    this.yield();
                }
            }
        }
    }
}

