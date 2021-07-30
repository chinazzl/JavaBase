package favor.SE;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Map进行遍历测试
 */
public class MapTravers {

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("hh","11");
        map.put("qq","aa");
        traversMap(map);

    }

    public static void traversMap(Map map){
        Set keySet = map.keySet();

        /***
         * 使用keySet进行遍历
         */
        //1
        for (Iterator iterator = keySet.iterator();iterator.hasNext();) {
            String key= (String) iterator.next();
            String value = (String) map.get(key);
            System.out.println("v===" + value);
        }

        //2
        for (Object str:keySet) {
            String o = (String) map.get(str);
            System.out.println("v2 == " + o);
        }

        /**
         * 使用Entryset进行遍历
         */
        Set entrySet = map.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("key:==" + key + ": value == " + value);

        }
    }
}
