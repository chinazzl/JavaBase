package SE8.StreamPac.collector;

import SE8.StreamPac.Entity.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static SE8.StreamPac.collector.CollectionsAction.menu;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/9/28 14:58
 * @Modified By：
 */
public class CollectorsAction3 {
    public static void main(String[] args) {
        testPartitioningByPredict();
        testPartitioningByPredictAndDownStream();
        testReducingByBinaryOperation();
        testReducingBinaryOperationWithIndentity();
        testReducingWithBinaryOperationAndFunctionAndIndentity();
    }

    /**
     * testPartitioningByPredict
     * 根据返回判断类型进行分组
     */
    private static void testPartitioningByPredict() {
        System.out.println("testPartitioningByPredict");
        Map<Boolean, List<Dish>> map = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    private static void testPartitioningByPredictAndDownStream() {
        System.out.println("testPartitioningByPredictAndDownStream");
        Optional.ofNullable(menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.averagingInt(Dish::getCalories))))
                .ifPresent(System.out::println);

    }

    /**
     * 使用 reduce 找到分组的最大值最小值
     */
    private static void testReducingByBinaryOperation() {
        System.out.println("testReducingByBinaryOperation");
        menu.stream().collect(Collectors.
                reducing(
                        BinaryOperator.
                                maxBy(
                                        Comparator.
                                                comparingInt(Dish::getCalories)
                                )
                )
        ).ifPresent(System.out::println);
    }

    /**
     * 取到具体的值进行聚合
     */
    private static void testReducingBinaryOperationWithIndentity() {
        System.out.println("testReducingBinaryOperationWithIndentity");
        Optional.ofNullable(menu.stream().map(Dish::getCalories).collect(Collectors.reducing(0, (c1, c2) -> c1 + c2)))
                .ifPresent(System.out::println);
    }

    private static void testReducingWithBinaryOperationAndFunctionAndIndentity() {
        System.out.println("testReducingWithBinaryOperationAndFunctionAndIndentity");
        Integer carlory = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (c1, c2) -> c1 + c2));
        System.out.println(carlory);
    }
}
