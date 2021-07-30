package SE8;

import java.util.ArrayList;
import java.util.List;

/**
 * SE8 传递函数
 */
public class AppleFunction {

    /*
        判断是否是绿色的
     */
    public static boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());
    }

    /*
        判断是否是重的苹果
     */
    public static boolean isHeaveyApple(Apple apple){
        return apple.getWeight() > 150;

    }

    static List<Apple> filterApples(List<Apple> inventory,Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {
    }

}
