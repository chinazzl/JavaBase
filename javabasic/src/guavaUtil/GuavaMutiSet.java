package guavaUtil;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Iterator;
import java.util.Set;
import java.util.StringJoiner;

/**
 * @author Julyan
 * @version V1.0
 * @Date: 2022/11/28 22:53
 * @Description:
 */
public class GuavaMutiSet {

    public static void main(String[] args) {
        HashMultiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("b");
        multiset.add("b");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");
        multiset.add("e");
        multiset.add("f");
        // 获取指定值重复的数量
        System.out.println("Occurrence of 'b': " + multiset.count("b"));
        Set<String> set = multiset.elementSet();
        Iterator<String> iterator = set.iterator();
        StringJoiner stringJoiner = new StringJoiner(",","[","]");
        while (iterator.hasNext()) {
            String next = iterator.next();
            stringJoiner.add(next);
        }
        System.out.println(stringJoiner);
        for (Multiset.Entry<String> entry : multiset.entrySet()) {
            System.out.println("Element: " + entry.getElement() + ",Occurrence(s): " + entry.getCount());
        }
        // 移除指定出现次数的元素
        multiset.remove("b",1);
        System.out.println("Occurrence of 'b': " + multiset.count("b"));

    }
}
