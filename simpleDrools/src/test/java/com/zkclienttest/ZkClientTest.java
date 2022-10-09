package com.zkclienttest;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**********************************
 * @author zhang zhao lin
 * @date 2022年08月18日 21:50
 * @Description: zookeeper client 客户端
 **********************************/
public class ZkClientTest {

    private static final String zkUrl = "localhost:2181";
    private static final int zkSessionTimeoutMs = 5000;
    private static final int zkConnectionTimeoutMs = 5000;
    private static final RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 5);

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
