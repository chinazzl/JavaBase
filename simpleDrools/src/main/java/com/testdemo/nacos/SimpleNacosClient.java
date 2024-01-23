package com.testdemo.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

/**********************************
 * @author zhang zhao lin
 * @date 2022年08月27日 10:34
 * @Description:
 **********************************/
public class SimpleNacosClient {

    // 指定nacos服务地址
    private static final String serverAddr = "49.4.114.32:8848";

    // 指定nacos dataId 定义为具体的模块配置文件
    private static final String dataId = "simple-nacos-dev.yml";

    // 指定nacos group ，可以定义为 具体的项目
    private static final String group = "DEFAULT_GROUP";

    // 指定namespace 可以自定义为 环境、租户
    private static final String namespace = "2799ffc4-4d5a-45ae-bc70-fc723d0aeb3e";
    private static final Properties properties;

    static {
        properties = new Properties();
        properties.put("serverAddr", serverAddr);
        properties.put("namespace", namespace);
    }

    public static void main(String[] args) throws NacosException, InterruptedException {
        ConfigService configService = NacosFactory.createConfigService(properties);
        String config = configService.getConfig(dataId, group, 10000);
        System.out.println(config);

        configService.addListener(dataId, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            // 当配置发生改变的时候进行调用
            @Override
            public void receiveConfigInfo(String newConfigInfo) {
                System.out.println("new Config " + newConfigInfo);
            }
        });

        Thread.currentThread().join();
    }
}
