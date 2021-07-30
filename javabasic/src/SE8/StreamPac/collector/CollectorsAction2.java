package SE8.StreamPac.collector;
//静态导入 JDK1.5 新特性

import SE8.StreamPac.Entity.Dish;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static SE8.StreamPac.collector.CollectionsAction.menu;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/9/27 15:18
 * @Modified By：
 * groupingByConcurrent
 */
public class CollectorsAction2 {
    public static void main(String[] args) {
        testGroupingByConcurrentWithFunction();
        testGroupingByConcurrentWithFunctionAndCollector();
        testGroupingByConcurrentWithFunctionAndSupplierAndCollector();
        testJoining();
        testMapping();
    }

    /**
     * 返回一个并发 Collector “由基团”上的类型的输入元件操作实现 T ，根据分类功能分组元素。
     */
    private static void testGroupingByConcurrentWithFunction() {
        ConcurrentMap<Dish.Type, List<Dish>> map = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType));
        System.out.println(map.getClass());
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    /**
     * 返回一个并发 Collector “由基团”上的类型的输入元件操作实现级联 T ，
     * 根据分类功能分组元素，然后使用下游的指定执行与给定键相关联的值的归约运算 Collector 。
     */
    private static void testGroupingByConcurrentWithFunctionAndCollector() {
        ConcurrentMap<Dish.Type, Double> map = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType,
                Collectors.averagingInt(Dish::getCalories)));
        Optional.of(map).ifPresent(System.out::println);

    }

    /**
     * 返回一个并发 Collector “由基团”上的类型的输入元件操作实现级联 T ，
     * 根据分类功能分组元素，然后使用下游的指定执行与给定键相关联的值的归约运算 Collector 。
     */

    private static void testGroupingByConcurrentWithFunctionAndSupplierAndCollector() {
        ConcurrentSkipListMap<Dish.Type, Double> map = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType,
                ConcurrentSkipListMap::new, Collectors.averagingInt(Dish::getCalories)));
        System.out.println(map.getClass());
        Optional.of(map).ifPresent(System.out::println);
    }

    /**
     * 返回一个 Collector ，按照遇到的顺序将输入元素连接到一个 String中。
     */
    private static void testJoining() {
        System.out.println("test Joining");
        Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining(",","NAME=[","]")))

                .ifPresent(System.out::println);
    }

    /**
     * mapping 可以直接使用stream进行collect操作，从而避免了需要通过map进行转换成String类型
     */
    private static void testMapping() {
        Optional.of(menu.stream().collect(Collectors.mapping(Dish::getName,Collectors.joining(","))))
                .ifPresent(System.out::println);
    }
}
