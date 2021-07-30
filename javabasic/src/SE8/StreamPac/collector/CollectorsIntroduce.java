package SE8.StreamPac.collector;

import SE8.Apple;
import favor.SE.duoTai.A;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/9/25 16:09
 * @Modified By：
 * 简单描述Collector
 */
public class CollectorsIntroduce {
    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 127.1),
                new Apple("green", 127.2),
                new Apple("yellow", 127.3),
                new Apple("yellow", 127.4),
                new Apple("green", 127.5),
                new Apple("green", 127.6));
        List<Apple> appleList = list.stream().filter(a -> a.getWeight() > 50).collect(Collectors.toList());
//        Optional.ofNullable(appleList).ifPresent(System.out::print);
        Optional.ofNullable(GroupingAppleByNomal(list)).ifPresent(System.out::println);
        Optional.ofNullable(groupByColorByFunction(list)).ifPresent(System.out::println);
        System.out.println("========Using the Collector");
        Optional.ofNullable(groupByCollector(list)).ifPresent(System.out::println);

    }

    /**
     * 一般方法，将 小于50的放为一组，大于50为一组
     */
    private static Map<String, List<Apple>> GroupingAppleByNomal(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();
        for (Apple apple : apples) {
            List<Apple> a = map.get(apple.getColor());
            if (a == null) {
                a = new ArrayList<>();
                map.put(apple.getColor(), a);
            }
            a.add(apple);
        }
        return map;
    }


    /**
     * 使用Function
     * 将不同的颜色的苹果进行分组
     */
    private static Map<String, List<Apple>> groupByColorByFunction(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();
        apples.stream().forEach(a -> {
            List<Apple> appleList = Optional.ofNullable(map.get(a.getColor())).orElseGet(() -> {
                List list = new ArrayList();
                map.put(a.getColor(), list);
                return list;
            });
            appleList.add(a);
        });
        return map;
    }

    /**
     * 使用 Collector 进行分组
     */
    private static Map<String, List<Apple>> groupByCollector(List<Apple> apples) {
        return apples.stream().collect(Collectors.groupingBy(a -> a.getColor()));
    }


}
