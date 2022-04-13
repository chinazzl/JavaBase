package level3.juc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/4/7 14:13
 */
public class CompletableFutureTest {

    static final int[] arr = {5, 3};

    public static void main(String[] args) throws InterruptedException {

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("alarmId", "1");
        map.put("systemId", "0029");
        map.put("param", "10.1");
//        map.put("timer", 0);
        list.add(map);
        Map<String, Object> map_1 = new HashMap<>();
        map_1.put("alarmId", "1");
        map_1.put("systemId", "00230");
        map_1.put("param", "11.1");
//        map_1.put("timer", 1);
        list.add(map_1);
        CompletableFuture.runAsync(() -> {
                    list.forEach(m -> {
                        try {
                            dataProcess(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                })
                .whenComplete((v, t) -> {
                    System.out.println("done");
                });

        System.out.println("do other things ");
        Thread.currentThread().join();
    }

    private static void dataProcess(int timer) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        IntStream.range(0, 2).boxed().forEach(i -> executorService.execute(() -> {
            System.out.println("=== worker thread-" + timer + " - " + i + " start ===");
//            sleep(arr[timer]);
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
