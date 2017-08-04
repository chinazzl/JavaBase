package langer;

/**
 * Created by Felix on 2017/8/4.
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
