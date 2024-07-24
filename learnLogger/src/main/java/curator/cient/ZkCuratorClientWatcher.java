package curator.cient;

import com.google.common.collect.Lists;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;

import java.nio.charset.StandardCharsets;

/**********************************
 * @author zhang zhao lin
 * @date 2024年02月29日 16:28
 * @Description:
 **********************************/
public class ZkCuratorClientWatcher implements CuratorWatcher {

    static final CuratorFramework client;

    static {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient("192.168.43.201:2181", retryPolicy);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("ZkCuratorClient starting");
        client.start();
        System.out.println("ZkCuratorClient started");
        assert client.getState().equals(CuratorFrameworkState.STARTED);
        ZkCuratorClientWatcher watcher = new ZkCuratorClientWatcher();
        client.checkExists()
                .usingWatcher(watcher)
                .forPath("/my/a/a1");
        client.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/my/b", "b_data".getBytes());


        Thread.currentThread().join();
    }

    @Override
    public void process(WatchedEvent event) throws Exception {
        System.out.println(event.getPath());
        System.out.println(event.getType());
    }
}
