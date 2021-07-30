package SE8.StreamPac;

import SE8.StreamPac.Entity.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/2/22 18:39
 * @Modified By：
 * 描述 Stream的map() 和 flatMap()。
 * map： 获取指定的值
 * flatMap: 扁平化
 */
public class StreamMapper {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 6, 6, 7, 2, 1, 4, 2);
        List<Integer> collect = list.stream().map(i -> i * 2).collect(Collectors.toList());
        System.out.println(collect);

        ListDish().stream().map(dish -> dish.getName()).forEach(System.out::println);

        /*
            将一个数组去重, 把数组中含有l 的全部去重
         */
        String[] words = {"Hello","World"};
        //将每个单词转换为由其字母构成的数组 {H,e,l,l,o},{W,o,r,l,d}
        Stream<String[]> stream = Arrays.stream(words).map(w -> w.split(""));
        //将各个生成的流扁平化,各个数组并不是分别映射成一个流，而是映射流的内容。
        //所有使用map(Arrays::stream)时生成的单个流都被合并起来
        List<String> collect1= stream.flatMap(t -> {
            //让每个数组变成一个单独的流
            return Arrays.stream(t);
        }).distinct().collect(Collectors.toList());
        collect1.forEach(System.out::println);
    }

    public static List<Dish> ListDish(){
        return  Arrays.asList(
                new Dish("pork", false, 801, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
    }
}
