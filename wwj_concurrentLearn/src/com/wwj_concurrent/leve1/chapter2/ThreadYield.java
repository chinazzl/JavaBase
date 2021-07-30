package com.wwj_concurrent.leve1.chapter2;

import java.util.stream.IntStream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/7/9 10:25
 * @Modified By：
 * 提醒调度器 我愿意放弃当前的CPU资源，如果CPU资源不紧张，则会忽略这种提醒
 */
public class ThreadYield {
    public static void main(String[] args) {
        IntStream.range(0, 2).mapToObj(ThreadYield::create)
                .forEach(Thread::start);
    }

    private static Thread create(int index) {
        return new Thread(() -> {
            if (index == 0) {
                Thread.yield();
            }
            System.out.println(index);
        });
    }
}
