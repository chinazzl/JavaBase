package cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * @author Julyan
 * @version V1.0
 * @Date: 2022/12/2 11:26
 * @Description:
 */
public class CaffeineCache {
    public static void main(String[] args) {
        Cache<Object, Object> cache = Caffeine.newBuilder()
                // 初始化容量
                .initialCapacity(5)
                // 最大缓存数
                .maximumSize(10)
                // 设置过期时间
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .build();
        String key = "key";
        cache.put(key,"val");
        // 获取缓存数据，如果key不存在，获取数据库中数据后再返回,
        Object b = cache.get("b", k -> {
            System.out.println("获取缓存的key：" + k);
            return "b12";
        });
        System.out.println(b);

    }
}
