package com.zkclienttest;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**********************************
 * @author zhang zhao lin
 * @date 2022年08月18日 21:50
 * @Description: zookeeper client 客户端 测试
 **********************************/
public class ZkClientTest {

    private static final String zkUrl = "61.184.20.136:2181,61.184.20.136:2182,61.184.20.136:2183";
    private static final int zkSessionTimeoutMs = 5000;
    private static final int zkConnectionTimeoutMs = 5000;
    private static final RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 5);
    private static final Logger log = LoggerFactory.getLogger(ZkClientTest.class);

    private static CuratorFramework client;

    @Before
    public void connectZk() {
        /**
         * Create a new client
         *
         * @param connectString      zk 服务端地址
         * @param sessionTimeoutMs    会话超时时长
         * @param connectionTimeoutMs 连接时长
         * @param retryPolicy         retry policy to use
         * @return client
         */
        client = CuratorFrameworkFactory.
                newClient(zkUrl, zkSessionTimeoutMs, zkConnectionTimeoutMs, retryPolicy);
        client.start();
    }

    /**
     * 顺序一致性
     * @throws Exception
     */
    @Test
    public void demonstrateSequentialConsistency() throws Exception {
        System.out.println("1. 顺序一致性演示");
        String basePath = "/sequential-demo";
        // 清理旧数据
        deletePathIfExists(basePath);
        // 创建父节点
        client.create().forPath(basePath,"parent".getBytes());
        // 按照顺序窜天多个顺序节点
        for (int i = 0; i < 5; i++) {
            String path = client.create()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath(basePath + "/node-" + ("data-" + i).getBytes());
            System.out.println("创建节点：" + path);
        }
        // 读取所有子节点，验证顺序
        System.out.println("\n读取子节点（验证顺序）");
        client.getChildren().forPath(basePath).forEach(child -> {
            System.out.println(" 子节点：" + child);
        });
        System.out.println("✓ 节点序号严格递增，保证了顺序一致性\n");

    }

    private void deletePathIfExists(String path) {
        try {
            Stat stat = client.checkExists().forPath(path);
            if (stat != null) {
                client.delete().deletingChildrenIfNeeded().forPath(path);
            }

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    /**
     * 原子性演示
     * 操作要么全部成功，要么全部失败
     */
    @Test
    public void demonstrateAtomicity() throws Exception {
        System.out.println("原子性演示");
        String basePath = "/atomic-demo";
        deletePathIfExists(basePath);
        // 创建节点
        try {
            client.create().forPath(basePath,"initial-data".getBytes());
            byte[] bytes = client.getData().forPath(basePath);
            System.out.println("创建成功" + new String(bytes, StandardCharsets.UTF_8));
        }catch (Exception e){
            log.error("x 创建失败");
        }
        // 失败案例：重复创建（会失败，不会部分成功）
        try {
            client.create().forPath(basePath, "duplicate-data".getBytes());
            log.info("不应该成功");
        } catch (Exception e) {
            log.error("创建失败：{}", e.getMessage());
            byte[] data = client.getData().forPath(basePath);
            log.info("原数据未修改：{}",new String(data, StandardCharsets.UTF_8));
        }
        System.out.println("✓ 操作具有原子性，要么完全成功，要么完全失败\n");
    }

    /**
     * 单一视图
     * 多个客户端连接到不同的服务器，看的数据一致
     */
    @Test
    public void demonstrateSingleSystemImage() throws Exception {
        System.out.println("单一视图演示");
        String basePath = "/single-view-demo";
        deletePathIfExists(basePath);
        // 客户端1写入数据
        client.create().forPath(basePath,"shared-data".getBytes());
        System.out.println("客户端1 写入数据：shared-data");
        // 创建多个客户端模拟连接不同服务器
        CuratorFramework client2 = CuratorFrameworkFactory.builder()
                .connectString(zkUrl)
                .sessionTimeoutMs(zkSessionTimeoutMs)
                .connectionTimeoutMs(zkConnectionTimeoutMs)
                .retryPolicy(retryPolicy)
                .build();
        client2.start();
        CuratorFramework client3 = CuratorFrameworkFactory.builder()
                .connectString(zkUrl)
                .sessionTimeoutMs(zkSessionTimeoutMs)
                .connectionTimeoutMs(zkConnectionTimeoutMs)
                .retryPolicy(retryPolicy)
                .build();
        client3.start();
        TimeUnit.SECONDS.sleep(1);
        // 多个客户端读取数据
        byte[] data1 = client.getData().forPath(basePath);
        byte[] data2 = client2.getData().forPath(basePath);
        byte[] data3 = client3.getData().forPath(basePath);
        System.out.println("客户端1 读取数据：" + new String(data1, StandardCharsets.UTF_8));
        System.out.println("客户端2 读取数据：" + new String(data2, StandardCharsets.UTF_8));
        System.out.println("客户端3 读取数据：" + new String(data3, StandardCharsets.UTF_8));

        System.out.println("✓ 所有客户端看到的数据一致，具有单一视图特性\n");

        client2.close();
        client3.close();
    }

    /**
     * 4. 可靠性演示
     * 数据一旦写入成功，就会持久化
     */
    private void demonstrateReliability(CuratorFramework client) throws Exception {
        System.out.println("【4. 可靠性演示】");

        String testPath = "/reliability-demo";
        deletePathIfExists(testPath);

        // 写入数据
        String originalData = "reliable-data-" + System.currentTimeMillis();
        client.create().forPath(testPath, originalData.getBytes());
        System.out.println("写入数据: " + originalData);

        // 断开并重新连接客户端
        System.out.println("\n模拟客户端断开重连...");
        client.close();

        CuratorFramework newClient = CuratorFrameworkFactory.builder()
                .connectString(zkUrl)
                .sessionTimeoutMs(zkSessionTimeoutMs)
                .connectionTimeoutMs(zkConnectionTimeoutMs)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        newClient.start();

        // 重新读取数据
        byte[] retrievedData = newClient.getData().forPath(testPath);
        System.out.println("重连后读取数据: " + new String(retrievedData));

        System.out.println("✓ 数据持久化保存，具有可靠性\n");

        newClient.close();

        // 恢复客户端
        client = CuratorFrameworkFactory.builder()
                .connectString(zkUrl)
                .sessionTimeoutMs(zkSessionTimeoutMs)
                .connectionTimeoutMs(zkConnectionTimeoutMs)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();
    }

    /**
     * 5. 实时性（最终一致性）演示
     * 读操作可能读到稍旧的数据，但最终会一致
     */
    private void demonstrateTimeliness(CuratorFramework client) throws Exception {
        System.out.println("【5. 实时性（最终一致性）演示】");

        String testPath = "/timeliness-demo";
        deletePathIfExists(testPath);

        // 创建节点
        client.create().forPath(testPath, "v1".getBytes());
        System.out.println("初始数据: v1");

        // 创建多个客户端
        CuratorFramework writeClient = CuratorFrameworkFactory.builder()
                .connectString(zkUrl)
                .sessionTimeoutMs(zkSessionTimeoutMs)
                .connectionTimeoutMs(zkConnectionTimeoutMs)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        writeClient.start();

        CuratorFramework readClient = CuratorFrameworkFactory.builder()
                .connectString(zkUrl)
                .sessionTimeoutMs(zkSessionTimeoutMs)
                .connectionTimeoutMs(zkConnectionTimeoutMs)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        readClient.start();

        System.out.println("\n演示读写时序：");

        // 写入新数据
        writeClient.setData().forPath(testPath, "v2".getBytes());
        System.out.println("时刻T1: 写客户端更新数据为 v2");

        // 立即读取（可能读到旧数据）
        byte[] data1 = readClient.getData().forPath(testPath);
        System.out.println("时刻T2: 读客户端立即读取: " + new String(data1) + " (可能是旧数据)");

        // 稍后再读取（应该读到新数据）
        Thread.sleep(200);
        byte[] data2 = readClient.getData().forPath(testPath);
        System.out.println("时刻T3: 读客户端200ms后读取: " + new String(data2) + " (最终一致)");

        // 使用 sync() 强制同步后读取（保证读到最新数据）
        System.out.println("\n使用 sync() 保证读到最新数据：");
        writeClient.setData().forPath(testPath, "v3".getBytes());
        System.out.println("写客户端更新数据为 v3");

        readClient.sync().forPath(testPath); // 强制同步
        byte[] data3 = readClient.getData().forPath(testPath);
        System.out.println("读客户端使用sync()后读取: " + new String(data3));

        System.out.println("✓ ZooKeeper 保证最终一致性，可通过 sync() 保证实时性\n");

        writeClient.close();
        readClient.close();
    }

   private static AtomicInteger notifyCount = new AtomicInteger(0);

    /**
     * 持续进行监听，zookeeper每次消费完一个watcher自动销毁，在客户端中需要重新注册一个watcher
     * 原因一：避免服务器压力过大
     * 原因二：保证通知的可靠性
     * 圆心三：简化编程模型
     *
     * @throws Exception
     */
    @Test
    public void testWatcher() throws Exception {
        String path = "/curator-recursive-demo";
        deletePathIfExists(path);
        client.create().forPath(path,"v0".getBytes());
        System.out.println("=========curator watcher 递归注册==========");
        watcherRecursively(client, path);
        // 多次修改数据
        for (int i = 1; i < 5; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("修改 #" + i + "：V" + (i-1) + "：V" + i);
            client.setData().forPath(path, ("v" + i).getBytes());
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("\n总共收到 " + notifyCount.get() + " 次通知");
        System.out.println("✓ 通过递归注册实现了持续监听\n");
    }

    private void watcherRecursively(CuratorFramework curatorClient,String path) throws Exception {
        curatorClient.getData().usingWatcher(new CuratorWatcher() {
            @Override
            public void process(WatchedEvent watchedEvent) throws Exception {
                int count = notifyCount.incrementAndGet();
                // 获取数据
                byte[] data = curatorClient.getData().forPath(path);
                System.out.println(" -> 接收到通知 #" + count + "，最新数据：" + new String(data));
                watcherRecursively(curatorClient,path);
            }
        }).forPath(path);
    }

    /**
     * 创建zookeeper节点
     */
    @Test
    public void createZk() {
        try {
            // 创建节点
            //client.create().forPath("/zk");
            // 创建待数据的
            //client.create().forPath("/zkdata","withdata".getBytes());
            // 创建临时节点
            //String path = client.create().withMode(CreateMode.EPHEMERAL).forPath("/tmpZk");
            // 创建多路径节点
            String path = client.create().creatingParentContainersIfNeeded().forPath("/pzk/p1", "hello p1".getBytes());
            client.create().creatingParentContainersIfNeeded().forPath("/pzk/p2", "hello p2".getBytes());
            System.out.println(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取zookeeper 数据
     *
     * @throws Exception
     */
    @Test
    public void getData() throws Exception {
        //byte[] path = client.getData().forPath("/zkdata");
        // 获取子节点
        //List<String> path = client.getChildren().forPath("/pzk");
        // 子节点状态查询
        Stat stat = new Stat();
        System.out.println(stat);
        byte[] info = client.getData().storingStatIn(stat).forPath("/zkdata");
        System.out.println(stat);
    }

    /**
     * 对节点进行修改
     *
     * @throws Exception
     */
    @Test
    public void updteData() throws Exception {
        // 修改节点数据
//        Stat stat = client.setData().forPath("/zkdata", "updteData".getBytes(StandardCharsets.UTF_8));
        //修改父节带有字节点数据
        Stat stat = client.setData().forPath("/pzk/p1", "update P1 data".getBytes(StandardCharsets.UTF_8));
        // 根据版本号进行修改，目的防止线程干扰，乐观锁，原子性操作
//        Stat stat = new Stat();
//        client.getData().storingStatIn(stat).forPath("/zk");
//        int version = stat.getVersion();
//        System.out.println("version ->"  + version);
//        client.setData().withVersion(version).forPath("/zk","use update version".getBytes());
//        System.out.println(stat);
    }

    /**
     * 删除节点
     *
     * @throws Exception
     */
    @Test
    public void delZk() throws Exception {
        // 删除节点
//        client.delete().forPath("/zk");
        // 删除具有父节点的子节点
//        client.delete().deletingChildrenIfNeeded().forPath("/pzk");
        // 一定成功的删除
//        client.delete().guaranteed().forPath("/zkdata");
        // 具有回调的删除
                Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath("/tmp");
        int version = stat.getVersion();
        System.out.println("version ->"  + version);
        client.delete().withVersion(version).inBackground((curatorFramework, curatorEvent) -> {
            System.out.println("删除后的数据："+ new String(curatorEvent.getData()));
        }).forPath("/tmp");
    }

    @After
    public void close() {
        if (client != null) {
            client.close();
        }
    }
}
