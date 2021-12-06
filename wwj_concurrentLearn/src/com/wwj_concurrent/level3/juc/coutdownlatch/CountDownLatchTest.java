package com.wwj_concurrent.level3.juc.coutdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**********************************
 * @author zhang zhao lin
 * @date 2021年11月10日 21:07
 * @Description
 **********************************/
public class CountDownLatchTest {

    private static final int MAX_TABLE_SIZE = 10;

    private static final Random random = new Random(System.currentTimeMillis());

    static class Event {
        int id = 0;

        public Event(int id) {
            this.id = id;
        }
    }

    static class Table {
        String tableName;
        long sourceRecordCount;
        long targetSourceRecordCount;

        String sourceColumnSchema = "<table name = 't1'><column name = 'c1' type = 'varchar2'/></table>";
        String targetColumnSchema;

        public Table(String tableName, long sourceRecordCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourceRecordCount=" + sourceRecordCount +
                    ", targetSourceRecordCount=" + targetSourceRecordCount +
                    ", sourceColumnSchema='" + sourceColumnSchema + '\'' +
                    ", targetColumnSchema='" + targetColumnSchema + '\'' +
                    '}';
        }
    }

    interface Watcher {
        void done(Table table);
    }

    /**
     * @description 创建一个封装类
     * @date 2021/11/10 22:08
     * @return
     */
    static class TaskBatch implements Watcher {

        private CountDownLatch countDownLatch;

        private TaskGroup taskGroup;

        public TaskBatch(int size, TaskGroup taskGroup) {
            countDownLatch = new CountDownLatch(size);
            this.taskGroup = taskGroup;
        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println("table [" + table.tableName + "] , finish work " + table);
                taskGroup.done(table);
            }
        }
    }

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
                System.out.println("All table will be done. The event is [" + event.id + "]");
            }
        }
    }


    private static List<Table> capture(Event event) {
        List<Table> tables = new ArrayList<>();
        for (int i = 0; i < MAX_TABLE_SIZE; i++) {
            tables.add(new Table("table-" + event.id + "-" + i, i * 1000));
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
                // 只执行两个业务 size = 2;
                TaskBatch taskBatch = new TaskBatch(2, taskGroup);
                executorService.submit(new TrustRecordCounterRunnable(table, taskBatch));
                executorService.submit(new TrustColumnSchemaRunnable(table, taskBatch));
            }
        }
    }

    static class TrustRecordCounterRunnable implements Runnable {

        private Table table;

        private TaskBatch taskBatch;

        public TrustRecordCounterRunnable(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
                //模拟hive采集
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetSourceRecordCount = table.sourceRecordCount;
//            System.out.println("record will be done. " + "tableName is " + table.tableName + " , recordCount is " + table.targetSourceRecordCount);
            taskBatch.done(table);
        }
    }

    static class TrustColumnSchemaRunnable implements Runnable {

        private Table table;

        private TaskBatch taskBatch;

        public TrustColumnSchemaRunnable(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
                //模拟hive采集
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetColumnSchema = table.sourceColumnSchema;
//            System.out.println("column Schema will be done. " + "tableName is " + table.tableName + " , recordCount is " + table.targetColumnSchema);
            taskBatch.done(table);
        }
    }

}
