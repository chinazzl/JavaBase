package level3.juc;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package level3.juc
 * @Description:
 * @Date: 2021/11/16 10:39
 */
public class CyclicBarrrierTest {

    public static void main(String[] args) {
        System.out.println("result ====> " + get().get().toString());
    }

    private static AtomicReference<StringBuffer> get() {
        StringBuffer stringBuffer = new StringBuffer();
        AtomicReference<StringBuffer> sb = new AtomicReference<>(stringBuffer);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println("finished thread result. ");
            sb.get().append("all Run");
        });

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                cyclicBarrier.await();
            } catch (Exception e) {
            }finally {
                System.out.println(Thread.currentThread().isInterrupted());

            }
        },"T-1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                int i =  1/0;
                cyclicBarrier.await();
            } catch (Exception e) {
                cyclicBarrier.reset();
            }
        },"T-2").start();

        System.out.println("after thread runner.");
        return sb;
    }
}
