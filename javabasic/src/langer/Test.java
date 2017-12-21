package langer;

/**
 * Created by Felix on 2017/8/4.
 * Java中参数传递的都是值,对应基本类型,传递的是原值的拷贝;对于类类型,传递的是引用即地址的拷贝.
 对于函数对参数的改变是否会影响原值的问题:值类型自然不会影响到原值.
 而对于类类型要看改变的是参数的地址还是值,
 如果是前者,参数和原引用指向了不同的地址,它们自然脱离了关系;
 如果是后者,修改的实例就是原引用指向的实例,这当然对原值进行了修改.
 *
 *
 */
public class Test {

    public static void main(String[] args) {
        int i =1;
        change(i);
        System.out.println("i: " + i);
        String str = "2";
        StringBuffer sb = new StringBuffer("3");
        change(str);
        change(sb);
        System.out.println("sb: " + sb);
        System.out.println("str" + str);
    }

    private static void change(int i) {
        i++;
        System.out.println(i);
    }

    private static void change(StringBuffer sb) {
        sb = new StringBuffer("sb3");
        System.out.println(sb);
    }

    private static void change(String str) {
        str = "s1";
        System.out.println(str);
    }
}
