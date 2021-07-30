package com.wwj_concurrent.leve1.chapter2.joinpractice;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/7/13 17:50
 * @Modified By：
 */
public class FightQueryExample {
    // 返回合作的各大航空公司
    private static List<String> fightCompany = Arrays.asList("CSA", "CEA", "HNA");

    public static void main(String[] args) {
        List<String> results = search("SH", "BJ");
        System.out.println("===========result============");
        results.forEach(System.out::println);
    }

    private static List<String> search(String orginal, String destination) {
        final List<String> result = new ArrayList<>();
        //创建 查询航班信息的线程列表
        List<FightQueryTask> tasks = fightCompany.stream().map(f -> createSearchTask(f, orginal, destination))
                .collect(Collectors.toList());
        //分别启动几个线程
        tasks.forEach(Thread::start);
        //分别用每一个线程的join方法，阻塞当前线程
        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //再次之前，当前线程会被阻塞，获取每一个查询线程的结果，并加入到ressult中
        tasks.stream().map(FightQueryTask::get).forEach(result::addAll);
        return result;
    }

    private static FightQueryTask createSearchTask(String fight, String orginal, String dest) {
        return new FightQueryTask(fight, orginal, dest);
    }
}
