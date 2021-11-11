package com.wwj_concurrent.level3.juc.coutdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.level3.juc.coutdownlatch
 * @Description:
 * @Date: 2021/11/10 16:38
 */
public class CountDownLatchProgram {

    private final static int MAX_TABLES = 10;

    private final static Random random = new Random(System.currentTimeMillis());

    static class Event {
        int id = 0;

        public Event(int id) {
            this.id = id;
        }
    }

    static class Table {
        String tableName;
        long sourcesRecordCount;
        long targetsRecordCount;

        String sourceColumnSchema = "<table name = 'a'><column name = 'c1' type = 'varchar2' /></table>";
        String targetColumnSchema;

        public Table(String tableName, long sourcesRecordCount) {
            this.tableName = tableName;
            this.sourcesRecordCount = sourcesRecordCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourcesRecordCount=" + sourcesRecordCount +
                    ", targetsRecordCount=" + targetsRecordCount +
                    ", sourceColumnSchema='" + sourceColumnSchema + '\'' +
                    ", targetColumnSchema='" + targetColumnSchema + '\'' +
                    '}';
        }
    }

    interface Watcher {
        void done(Table table);
    }

    /**
     * 将 所有需要修改的信息 整个到一个对象中 进行一次性 DB操作，节省性能
     */
    static class TaskBatch implements Watcher {
        private final CountDownLatch countDownLatch;

        private TaskGroup taskGroup;

        public TaskBatch(int size, TaskGroup taskGroup) {
            countDownLatch = new CountDownLatch(size);
            this.taskGroup = taskGroup;
        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println("All table is done. table is " + table.toString());
                taskGroup.done(table);
            }
        }
    }

    /**
     * 分组进行打印
     */
    static class TaskGroup implements Watcher {

        private CountDownLatch countDownLatch;
        private Event event;

        public TaskGroup(int size, Event event) {
            this.countDownLatch = new CountDownLatch(size);
            this.event = event;
        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println("all table [" + table.tableName + "] has benn done. " + "The event is [" + event.id + "]");
            }
        }
    }


    /**
     * 捕获 需要业务处理的 事件
     *
     * @param event
     * @return
     */
    private static List<Table> capture(Event event) {
        List<Table> tables = new ArrayList<>();
        for (int i = 0; i < MAX_TABLES; i++) {
            tables.add(new Table("table - " + event.id + "- " + i, i * 1000));
        }
        return tables;
    }

    public static void main(String[] args) {
        Event[] events = new Event[]{new Event(1), new Event(2)};
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (Event event : events) {
            List<Table> tables = capture(event);
            TaskGroup taskGroup = new TaskGroup(tables.size(),event);
            for (Table table : tables) {
                TaskBatch taskBatch = new TaskBatch(2, taskGroup);
                executorService.submit(new RecordCounterRunnable(table, taskBatch));
                executorService.submit(new ColumnSchemaRunnable(table, taskBatch));
            }
        }
    }

    static class RecordCounterRunnable implements Runnable {

        private Table table;

        private TaskBatch taskBatch;

        public RecordCounterRunnable(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            //从hive进行获取数据
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetsRecordCount = table.sourcesRecordCount;
//            System.out.println("record count done. table Name = " + table.tableName);
            taskBatch.done(table);
        }
    }

    static class ColumnSchemaRunnable implements Runnable {


        private Table table;

        private TaskBatch taskBatch;

        public ColumnSchemaRunnable(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            //从hive进行获取数据
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetColumnSchema = table.sourceColumnSchema;
//            System.out.println("column count done. table Name = " + table.tableName);
            taskBatch.done(table);
        }
    }

}
