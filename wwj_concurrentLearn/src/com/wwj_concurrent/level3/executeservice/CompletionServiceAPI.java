package com.wwj_concurrent.level3.executeservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Julyan
 * @version V1.0
 * @Description: 使用CompletionService 解决Future的一些局限性
 * @Date: 2022/1/6 15:58
 */
public class CompletionServiceAPI {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        completionServiceTake();
        completionServiceSubmit();
    }

    private static void completionServiceTake() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> callables = Arrays.asList(
                () -> {
                    sleep(20);
                    System.out.println("==== sleep 20 sec finished.");
                    return 20;
                },
                () -> {
                    sleep(10);
                    System.out.println("==== sleep 10 sec finished.");
                    return 10;
                }
        );
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        List<Future<Integer>> futures = new ArrayList<>();
        callables.forEach(callable -> futures.add(completionService.submit(callable)));
/*        Future<Integer> f;
        // take 方法是 阻塞的
        while ((f = completionService.take()) != null) {
            System.out.println(f.get());
        }*/

        // poll 非阻塞 内部任务还在执行 返回null
        System.out.println(completionService.poll());
    }

    /**
     * 使用{@link ExecutorCompletionService#submit(Runnable, Object)} ，带有返回值的ExecutorCompletionService
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void completionServiceSubmit() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ExecutorCompletionService<Event> completionService = new ExecutorCompletionService<>(executorService);
        final Event event = new Event(1);
        completionService.submit(new MyTask(event), event);
        System.out.println(completionService.take().get().result);
    }

    private static class MyTask implements Runnable {

        private Event event;

        public MyTask(Event event) {
            this.event = event;
        }

        @Override
        public void run() {
            sleep(10);
            event.setResult("Bingo...");
            System.out.println(" My task execute Successful. ");
        }
    }

    private static class Event {
        private int eventId;
        private String result;

        public Event(int eventId) {
            this.eventId = eventId;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }


    /**
     * sleep specify
     *
     * @param seconds 休眠毫秒值
     */
    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
