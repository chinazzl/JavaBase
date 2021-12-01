package com.wwj_concurrent.level3.lock.forkjoin;

import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Julyan
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.lock.forkjoin
 * @Description:
 * @Date: 2021/12/1 16:46
 */
public class ForkJoinRecursiveAction {
    private static final int MAX_THRESHOLD = 3;
    private static AtomicInteger SUM = new AtomicInteger(0);

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new CalculateRecursive(0, 10));
        try {
            forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Optional.of(SUM).ifPresent(System.out::println);

    }

    /**
     * 没有返回值使用RecursiveAction，有返回值使用RecursiveTask
     */
    private static class CalculateRecursive extends RecursiveAction {
        private final int start;
        private final int end;

        private CalculateRecursive(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if ((end - start) <= MAX_THRESHOLD) {
                SUM.addAndGet(IntStream.rangeClosed(start, end).sum());
            } else {
                int middle = (start + end) / 2;
                CalculateRecursive leftRec = new CalculateRecursive(start, middle);
                CalculateRecursive rightRec = new CalculateRecursive(middle + 1, end);
                leftRec.fork();
                rightRec.fork();
            }
        }
    }
}
