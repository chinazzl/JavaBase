package SE8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 通过策略模式使用SE8 新特性
 */
public class AppleFilter {

    /**
     * 该注解只能有一个抽象方法，default除外
     */
    @FunctionalInterface
    public  interface AppleFilterInterFace {
        boolean selApple(Apple apple);

        default void isTest(){
            System.out.println("default Test");
        }
    }

    //创建内部类实现接口 获取绿色并且重量大于150的苹果
    public static class GetGreenAndWightThan150 implements AppleFilterInterFace{

        @Override
        public boolean selApple(Apple apple) {
            return "green".equals(apple.getColor())  && apple.getWeight() >150;
        }
    }

    public static List<Apple> findApple(List<Apple> apples,AppleFilterInterFace appleFilterInterFacep){
        List<Apple> result = new ArrayList<>();
        appleFilterInterFacep.isTest();
        for (Apple apple : apples) {
            if(appleFilterInterFacep.selApple(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple(23.1, "red"), new Apple(167.0, "green"), new Apple(234.1, "blue"));

        List<Apple> greenApples = findApple(apples, new GetGreenAndWightThan150());
        System.out.println(greenApples);

        List<Apple> apple1 = findApple(apples, (Apple apple) -> "green".equals(apple.getColor()));
        System.out.println(apple1);




        //给所有的苹果重量进行排序
        /*apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });*/
        apples.sort((Apple o1, Apple o2) -> o1.getWeight().compareTo(o2.getWeight()));
        apples.sort(Comparator.comparing(Apple :: getWeight));
    }
}
