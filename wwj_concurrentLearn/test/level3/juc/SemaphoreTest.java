package level3.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**********************************
 * @author zhang zhao lin
 * @date 2022年04月03日 14:25
 * @Description: 使用Semaphore进行控制执行顺序
 **********************************/
public class SemaphoreTest {

    static final int[] arr = {5, 3};

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        IntStream.range(0, 2).boxed().forEach(i -> new Thread(() -> {
            try {
                semaphore.acquire();
                dataProcess(i);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start());
        System.out.println("====== process finished if before ended means asyc process =====");
        Thread.currentThread().join();
    }

    private static void dataProcess(int timer) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        IntStream.range(0, 2).boxed().forEach(i -> executorService.execute(() -> {
            System.out.println("=== worker thread-" + timer + " - " + i + " start ===");
            sleep(arr[timer]);
            System.out.println("=== worker thread-" + timer + " - " + i + "  ended ===");
            countDownLatch.countDown();
        }));
        countDownLatch.await();
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
