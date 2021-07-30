package SE8.StreamPac.collector;

import SE8.StreamPac.Entity.Dish;

import javax.swing.text.html.Option;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.toList;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/9/25 21:31
 * @Modified By：
 * Collectors Api
 */
public class CollectionsAction {
    public static final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {
        testAveragingDouble(menu);
        testAveragingInt(menu);
        testAveragingLong(menu);
        testCollectingAndThen(menu);
        testGroupingBy();
        testGroupingByFunction();
        testGroupingBySupplierCollection();
    }

    /**
     * 应用于输入元素的双值函数的算术平均值。
     *
     * @param dishes
     */
    private static void testAveragingDouble(List<Dish> dishes) {
        System.out.println("test AverageIngDouble");
        Optional.ofNullable(dishes.stream().collect(Collectors.averagingDouble(d -> d.getCalories())))
                .ifPresent(System.out::print);
    }

    /**
     * 它产生应用于输入元素的整数值函数的算术平均值。
     *
     * @param dishes
     */
    private static void testAveragingInt(List<Dish> dishes) {
        System.out.println("test AverageIngInt");
        Optional.ofNullable(dishes.stream().collect(Collectors.averagingInt(d -> d.getCalories())))
                .ifPresent(System.out::print);
    }

    /**
     * 它产生应用于输入元素的长值函数的算术平均值。
     *
     * @param dishes
     */
    private static void testAveragingLong(List<Dish> dishes) {
        System.out.println("test AverageIngLong");
        Optional.ofNullable(dishes.stream().collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    /**
     * 测试 适应 Collector进行额外的整理转换。
     *
     * @param dishes
     */
    private static void testCollectingAndThen(List<Dish> dishes) {
        System.out.println(" test CollecingAndThen");
        Optional.ofNullable(dishes.stream().collect(Collectors.collectingAndThen(Collectors.averagingLong(Dish::getCalories), d -> {
            return "this is averageLong" + d;
        }))).ifPresent(System.out::println);
        //如果想生成一个不允许修改的List，使用Collectors.unModifytoList方法，如果对生成的List进行新增，则会报错
//        List<Dish> list = dishes.stream().filter(d -> d.getType().equals(Dish.Type.MEAT))
//                .collect(Collectors.collectingAndThen(toList(), Collections::unmodifiableList));
//        list.add(new Dish("none others", false, 1130, Dish.Type.OTHER));
//        System.out.println(list);
    }

    /**
     * 根据 类型进行分组
     */
    private static void testGroupingBy() {
        System.out.println("test GroupingBy");
        Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType))).ifPresent(System.out::println);
    }

    /**
     * 返回Collector “由基团”上的类型的输入元件操作实现级联T ，根据分类功能分组元素，
     * 然后使用下游的指定执行与给定键相关联的值的归约运算Collector 。
     */
    private static void testGroupingByFunction() {
        Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType, averagingInt(d -> d.getCalories()))))
                .ifPresent(System.out::println);
    }

    /**
     * 返回Collector “由基团”上的类型的输入元件操作实现级联T ，根据分类功能分组元素，
     * 然后使用下游的指定执行与给定键相关联的值的归约运算Collector 。 由Map生成的Map是由提供的工厂功能创建的。
     * 分类功能将元素映射到一些关键类型K 。 下游收集器上类型的元素进行操作T并产生类型的结果D 。 所得的收集器产生一个Map<K, D> 。
     */
    private static void testGroupingBySupplierCollection() {
        TreeMap<Dish.Type, Double> map = Optional.of(menu.stream().
                collect(Collectors.groupingBy(Dish::getType, TreeMap::new, averagingInt(Dish::getCalories))))
                .get();
        System.out.println(map.getClass());
        Optional.of(map).ifPresent(System.out::println);
    }
}
