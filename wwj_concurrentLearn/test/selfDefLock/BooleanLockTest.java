package selfDefLock;

import java.util.stream.Stream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2021/1/17 13:12
 * @Modified By：
 */
public class BooleanLockTest {
    private final static  BooleanLock booleanLock = new BooleanLock();
    public static void main(String[] args) {
        Stream.of("T1","T2","T3","T4")
                .map(name ->threadPool(name))
                .forEach(Thread::start);
    /*    Stream.of("T1","T2","T3","T4")
                .forEach(name -> {
                    new Thread(() -> {
                        try {
                            booleanLock.lock();
                            System.out.println(Thread.currentThread().getName() + " have the lock Monitor");
                            works();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            booleanLock.unLock();
                        }
                    },name).start();
                });*/

    }

    public static void works() {
        System.out.println(Thread.currentThread().getName() + " is working...");
        try {
            Thread.sleep(40_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Thread threadPool(String name) {

        return new Thread(() ->{
            try {
                booleanLock.lock(10L);
                System.out.println(Thread.currentThread().getName() + " have the lock Monitor");
                works();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (Lock.TimeOutException e) {
                System.out.println(Thread.currentThread().getName() + " time out");
            }
            finally {
                booleanLock.unLock();
            }

        },name);
    }
}
