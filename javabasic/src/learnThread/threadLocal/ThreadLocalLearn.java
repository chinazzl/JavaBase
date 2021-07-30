package learnThread.threadLocal;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/2/22 10:06
 * @Modified By：
 */
public class ThreadLocalLearn {
    //①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static ThreadLocal seqNum = new ThreadLocal() {
        @Override
        public Integer initialValue() {
            return 0;
        }
    };

    //②获取下一个序列值
    public int getNextNum() {
        seqNum.set((int)seqNum.get() + 1);
        return (int)seqNum.get();
    }

    public static void main(String[] args) {
        ThreadLocalLearn sn = new ThreadLocalLearn();
//③ 3个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();
    }

    private static class TestClient extends Thread {
        private ThreadLocalLearn sn;

        public TestClient(ThreadLocalLearn sn) {
            this.sn = sn;
        }

        public void run() {
            for (int i = 0; i < 3; i++) {//④每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() +
                        "] sn[" + sn.getNextNum() + "]");
            }
        }
    }
}
