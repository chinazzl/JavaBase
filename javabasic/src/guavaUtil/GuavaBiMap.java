package guavaUtil;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author Julyan
 * @version V1.0
 * @Date: 2022/12/6 22:30
 * @Description:
 */
public class GuavaBiMap {
    public static void main(String[] args) {
        BiMap<String,String> biMap = HashBiMap.create();
        biMap.put("a","b");
        biMap.forcePut("c","b");

        System.out.println(biMap.get("a"));
    }
}
