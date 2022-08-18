package com.zkclienttest;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    private static final RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,5);

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
            String path = client.create().creatingParentContainersIfNeeded().forPath("/pzk/p1", "hello".getBytes());
            System.out.println(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getData() throws Exception {
        //byte[] path = client.getData().forPath("/zkdata");
        // 获取子节点
        //List<String> path = client.getChildren().forPath("/pzk");
        // 子节点装填查询
        Stat stat = new Stat();
        System.out.println(stat);
        byte[] info = client.getData().storingStatIn(stat).forPath("/zkdata");
        System.out.println(stat);
    }

    @After
    public void close() {
        if (client != null) {
            client.close();
        }
    }
}
