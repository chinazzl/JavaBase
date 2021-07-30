package SE8.LambdaPac;

import SE8.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/1/7 22:13
 * @Modified By：
 */
public class MethodReference {

    public static void main(String[] args) {

        List<Apple> list = Arrays.asList(new Apple(123.1, "Red"),
                new Apple(423.12, "White"),
                new Apple(213.1, "Grey"));
        System.out.println(list);
        System.out.println("----");
        list.sort((a1,a2) -> a1.getColor().compareTo(a2.getColor()));
        System.out.println(list);
        System.out.println("-----");

        list.forEach(System.out::println);
        /*
            1. 方法中有静态方法继续宁方法推导
            2. 可以通过类的实例方法
            3. 引用的方法
         */
        Function<String,Integer> f = Integer::parseInt;
        Integer apply = f.apply("123");
        System.out.println(apply);
        BiFunction<String,Integer,Character> f2 = String::charAt;
        Character f2C = f2.apply("hello", 2);
        System.out.println(f2C);
        String string = new String("Hello");
        Function<Integer,Character> f3 = string::charAt;
        Character f3c = f3.apply(4);
        System.out.println(f3c);

        /*
         * 构造函数推导
         */
        Supplier<String> s = String::new;
        String s1 = s.get();

        //多个变量的构造
        BiFunction<Double,String,Apple> bfApple = Apple::new;
        Apple blackApple = bfApple.apply(123.2, "Black");
    }
}
