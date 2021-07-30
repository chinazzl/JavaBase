package langer.StringPac;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2019/12/30 16:26
 * @Modified By：
 */
public class Jvm4String {

    public static void main(String[] args) {
//        test1();

//        test2();
        String  a = "test";

        String b = new String("test");

        System.out.println( a == b.intern());
        System.out.println( a == b);
//        test3();
    }

    private static void test3() {
        final String str1 = "hell";
        String str2 = "hello";
        String str3 = str1 + "o";
        System.out.println(str2 == str3);
    }

    private static void test2() {
        String str2 = new String("hello");
        String str1 = "hello";
        System.out.println(str2 == str1);
    }

    private static void test1() {
        String str1 = new StringBuilder("Hel").append("lo").toString();

        System.out.println(str1.intern() == str1);

        String str = "Hello";
        System.out.println(str == str1);
    }


}
