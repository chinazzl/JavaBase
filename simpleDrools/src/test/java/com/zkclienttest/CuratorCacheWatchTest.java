package com.zkclienttest;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Julyan
 * @version V1.0
 * @Date: 2022/8/19 16:39
 * @Description: 测试node cache watch package
 */
public class CuratorCacheWatchTest {

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
     * NodeCache
     *
     * @throws Exception
     */
    @Test
    public void nodeCacheWatch() throws Exception {
        // 创建对象
        final NodeCache nodeWatch = new NodeCache(client, "/app");
        // 注册监听
        nodeWatch.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("node changed");
                System.out.println(new String(nodeWatch.getCurrentData().getData()));
            }
        });
        // 启动监听
        nodeWatch.start(true);
        Thread.currentThread().join();
    }

    /**
     * 监听某个节点下面的某个子节点们。
     *
     * @throws Exception
     */
    @Test
    public void pathChildrenCache() throws Exception {
        // 注册监听 一个根节点
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/pzk", true);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("节点发生变化，event: " + event  );
                PathChildrenCacheEvent.Type type = event.getType();
                // 监听子节点的类型新增、修改、删除
                if (type == PathChildrenCacheEvent.Type.CHILD_UPDATED) {
                    System.out.println("update。。。");
                    System.out.println("子节点的value => " + new String(event.getData().getData()));
                }
            }
        });
        pathChildrenCache.start();
        Thread.currentThread().join();
    }

    @Test
    public void treeNodeCacheTest() throws Exception {
        TreeCache treeCache = new TreeCache(client,"/pzk");
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println("监听根节点和子节点有变动");
                System.out.println(event);
            }
        });
        treeCache.start();
        Thread.currentThread().join();

    }

    @After
    public void close() {
        if (client != null) {
            client.close();
        }
    }
}
