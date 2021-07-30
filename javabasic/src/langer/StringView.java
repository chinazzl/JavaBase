package langer;

/**
 * Created by Felix on 2017/8/7.
 */
public class StringView {

    private final int a = 1;
    static int b = 2;
    static {
         String s1 = "static Code 1";
        System.out.println(s1);
    }

    public static void main(String[] args) {
        String s2 = "main method 1";
        System.out.println(s2);
    }

    public StringView() {
        System.out.println(b);
    }
}
