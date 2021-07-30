package favor.testPackage;

import favor.CompositionBetterThanInheritance.InstrumentHashSet;
import favor.CompositionBetterThanInheritance.composition.InstrumentSetClazz;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 测试复合优于继承
 * @see Effetive in Java
 * @author Julyn
 */
public class InstrumentTestClazz {
    public static void main(String[] args) {
        /*
        普通继承HashSet类
        因为调用addAll方法，而AddAll方法基于add方法实现，
        调用子类的AddAll方法，进行addCount变为3，但是！！
        利用super.addAll();来调用HashSet的addAll实现，然后又依次调用InstrumentHashSet
        的add方法，每个元素都调用一次，三次调用分别给addCount加一，一共加了6次
        所以返回结果为6
         */
        InstrumentHashSet instrumentHashSet = new InstrumentHashSet();
        instrumentHashSet.addAll(Arrays.asList("Snap","Cookie","Pop"));
        int addCount = instrumentHashSet.getAddCount();
        System.out.println(addCount);
        System.out.println("=========================");
        InstrumentSetClazz instrumentSetClazz = new InstrumentSetClazz(new HashSet());
        instrumentSetClazz.addAll(Arrays.asList("Snap","Cookie","Pop"));
        instrumentSetClazz.getAddCount();
    }
}
