package com.wwj_concurrent.level3.lock.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.lock.forkjoin
 * @Description: forkJoin 分而治之
 * @Date: 2021/12/1 15:08
 */
public class ForkJoinSimple {

    // 最大处理数据阈值
    private static final int MAX_THRESHOLD = 200;

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> future = forkJoinPool.submit(new ForkJoinRecursive(0, 1000));
        try {
            Integer integer = future.get();
            System.out.println(integer);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static class ForkJoinRecursive extends RecursiveTask<Integer> {

        private final int start;
        private final int end;

        private ForkJoinRecursive(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= MAX_THRESHOLD) {
                return IntStream.rangeClosed(start, end).sum();
            } else {
                int middle = (end + start) / 2;
                ForkJoinRecursive leftJoin = new ForkJoinRecursive(start, middle);
                ForkJoinRecursive rightJoin = new ForkJoinRecursive(middle + 1, end);
                leftJoin.fork();
                rightJoin.fork();
                return leftJoin.join() + rightJoin.join();
            }
        }
    }
}
