package SE8.LambdaPac;

import SE8.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2019/12/25 22:28
 * @Modified By：
 */
public class LambdaUsage {

    //找出重量大于160g的苹果
    private static List<Apple> getWeightThan160gApple(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> rs = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                rs.add(apple);
            }
        }
        return rs;
    }

    //使用DoublePredicate，使参数具体化
    private static List<Apple> getAppleUseDounlePredicate(List<Apple> apples, DoublePredicate dbPre) {
        List<Apple> rs = new ArrayList<>();
        for (Apple apple : apples) {
            if (dbPre.test(apple.getWeight())) {
                rs.add(apple);
            }
        }
        return rs;
    }
    //使用两个参数BiPredicate
    private static List<Apple> getAppleUseBiPredicate(List<Apple> apples, BiPredicate<String,Double> biPre) {
        List<Apple> rs = new ArrayList<>();
        for (Apple apple : apples) {
            if (biPre.test(apple.getColor(),apple.getWeight())) {
                rs.add(apple);
            }
        }
        return rs;
    }

    //使用Consumer 的accept方法，在Fucntion中 定义为 void accept(T t);
    public static void useCosumerAccept(List<Apple> apples, Consumer<Apple> consumer){
        for (Apple apple : apples) {
            consumer.accept(apple);
        }
    }

    public static void useBiConsumer(String c, List<Apple> apples, BiConsumer<Apple,String> biConsumer){
        for (Apple apple : apples) {
            biConsumer.accept(apple,c);
        }

    }

    //测试Function
    public static String useFunctionApply(Apple a,Function<Apple,String> functions){
        return functions.apply(a);
    }

    //测试Supplied
    public static Apple useSuppliedGet(Supplier<Apple> supplier){

        return supplier.get();
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple(150.0, "red"),
                new Apple(180.2, "green"),
                new Apple(165.2, "blue"));
        List<Apple> weightThan160gApple = getWeightThan160gApple(apples, apple -> apple.getWeight() > 160);
        System.out.println(weightThan160gApple);

        //使用具体类型的function
        List<Apple> appleUseDounlePredicate = getAppleUseDounlePredicate(apples, dw -> dw > 100);
        System.out.println(appleUseDounlePredicate);

        //使用带有两个参数的function
        List<Apple> appleUseBiPredicate = getAppleUseBiPredicate(apples, (sa, w) -> sa.equals("green") && w > 100);
        System.out.println(appleUseBiPredicate);

        //测试Consumer的accept
        useCosumerAccept(apples,a-> System.out.println(a));

        System.out.println("=----------------------");
        //测试BiConsumer
        useBiConsumer("XXX",apples,(a,s)-> System.out.println(s+a.getColor()+"Weight: " + a.getWeight()));

        System.out.println("------------");
        //测试Function的apply
        String grey = useFunctionApply(new Apple(45.6, "grey"), (a) -> a.toString());
        System.out.println(grey);

        System.out.println("--------------");
        //测试Supplier
        Apple green = useSuppliedGet(() -> new Apple(2938.3, "Green"));
        System.out.println(green);
    }
}
