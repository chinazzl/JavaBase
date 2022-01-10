package com.wwj_concurrent.level3.executeservice;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**********************************
 * @author zhang zhao lin
 * @date 2022年01月07日 22:45
 * @Description:
 **********************************/
public class ExecutorDefect {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Runnable> taskList = IntStream.range(0, 5).boxed().map(ExecutorDefect::task).collect(toList());
        CompletionService<Object> completionService = new ExecutorCompletionService<>(executorService);
        taskList.forEach(r -> completionService.submit(Executors.callable(r)));
        Future<?> future;
        while ((future = completionService.take()) != null) {
            System.out.println(future.get());
        }*/
        /*
            问题：当给定一个时间后，关掉线程池，找出没有执行的任务
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        /*List<Runnable> taskList = IntStream.range(0, 5).boxed().map(ExecutorDefect::task).collect(toList());
        // CompletionService 只关注任务的完成情况，不关注任务中间是否出现问题并保留出现问题的任务
        CompletionService<Object> completionService = new ExecutorCompletionService<>(executorService);
        taskList.forEach(r -> completionService.submit(Executors.callable(r)));

        TimeUnit.SECONDS.sleep(12);
        List<Runnable> runnables = executorService.shutdownNow();
        // task 1 中间被打断却没有结束，但是返回的未执行的任务中却没有task 1.
        System.out.println(runnables.size());*/

        // TODO 需要进行重新封装callable
        List<Callable<Integer>> taskList = IntStream.range(0, 5).boxed().map(MyTask::new).collect(toList());
        taskList.forEach(executorService::submit);

        TimeUnit.SECONDS.sleep(12);
        executorService.shutdownNow();
        taskList.stream().filter(callable -> !((MyTask) callable).success).forEach(System.out::println);
    }

    /**
     * 重新封装task 定义是否执行成功的标志
     */
    private static class MyTask implements Callable<Integer> {

        private final Integer i;
        private Boolean success = false;

        public MyTask(Integer i) {
            this.i = i;
        }

        @Override
        public Integer call() {
            System.out.println("the task [" + i + "] has started. ");
            try {
                TimeUnit.SECONDS.sleep(i * 5 + 10);
                System.out.printf("the task [%d] has done. \n", i);
                success = true;
            } catch (InterruptedException e) {
                System.out.printf("the task [%d] has interrupt\n", i);

            }
            return i;
        }

        public Boolean isSuccess() {
            return success;
        }
    }

    private static Runnable task(Integer i) {
        return () -> {
            System.out.println("the task [" + i + "] has started. ");
            try {
                TimeUnit.SECONDS.sleep(i * 5 + 10);
                System.out.printf("the task [%d] has done. \n", i);
            } catch (InterruptedException e) {
                System.out.printf("the task [%d] has interrupt\n", i);
            }
        };
    }
}
