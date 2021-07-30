package SE8.StreamPac.collector;

import SE8.StreamPac.Entity.Dish;

import javax.swing.text.html.Option;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static SE8.StreamPac.collector.CollectionsAction.menu;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/10/13 15:40
 * @Modified By：
 */
public class CollectionAction4 {
    public static void main(String[] args) {
        testTosummingDouble();
        testTosummingInt();
        testToCollection();
        testToConcurrentHashMapWithBinaryOption();
    }

    private static void testTosummingDouble() {
        System.out.println("testTosummingDouble");
        Optional.of(menu.stream().collect(Collectors.summingDouble(d -> d.getCalories())))
                .ifPresent(System.out::println);
        // 相当于 。。。
        Optional.of(menu.stream().map(d -> d.getCalories()).mapToDouble(Integer::intValue).sum())
                .ifPresent(System.out::println);
    }

    private static void testTosummingInt() {
        System.out.println("testTosummingInt");
        Optional.of(menu.stream().collect(Collectors.summingInt(d -> d.getCalories())))
                .ifPresent(System.out::println);
    }

    private static void testToCollection() {
        System.out.println("testToCollection");
        Optional.of(menu.stream().filter(d -> d.getCalories() > 600).collect(Collectors.toCollection(LinkedList::new)))
                .ifPresent(System.out::println);
    }

    /**
     * 用于根据操作方式进行合并
     */
    private static void testToConcurrentHashMapWithBinaryOption() {
        Optional.of(menu.stream().collect(Collectors.toConcurrentMap(Dish::getType,v->2L,(a,b) -> a +b, ConcurrentSkipListMap::new)))
                .ifPresent( v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }



}
