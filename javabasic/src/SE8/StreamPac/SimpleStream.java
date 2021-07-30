package SE8.StreamPac;

import SE8.StreamPac.Entity.Dish;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/2/3 21:18
 * @Modified By：
 */
public class SimpleStream {
    public static void main(String[] args) {
       List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
        List<String> dishNamesByCollections = getDishNamesByCollections(menu);
        System.out.println(dishNamesByCollections);
        System.out.println("============");
        List<String> dishNameUseStream = getDishNameUseStream(menu);
        System.out.println(dishNameUseStream);

        List<Dish> dishes = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT));

        List<String> result = dishes.stream().filter(dish -> {
            System.out.println("filter - > " + dish.getName());
            return dish.getCalories() > 400;
        }).map(dish -> {
            System.out.println("map ->" + dish.getName());
            return dish.getName();
        }).collect(toList());
        System.out.println("------");
        System.out.println(result);
    }

    // 使用Stream流和方法推导
    private static List<String> getDishNameUseStream(List<Dish> menue){
        return menue.stream().filter(d->d.getCalories()<400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName).collect(toList());

    }

    private static List<String> getDishNamesByCollections(List<Dish> menue){
        List<Dish> rsList = new ArrayList<>();
        for (Dish dish : menue) {
            if(dish.getCalories() < 400){
                rsList.add(dish);
            }
        }
        Collections.sort(rsList,(d1,d2) ->Integer.compare(d1.getCalories(),d2.getCalories()));
        List<String> dishNames = new ArrayList<>();

        for (Dish dish : rsList) {
            dishNames.add(dish.getName());
        }
        return dishNames;
    }
}
