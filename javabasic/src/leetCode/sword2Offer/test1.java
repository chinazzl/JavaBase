package leetCode.sword2Offer;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/1/10 19:41
 * @Modified By：
 * <p>
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。
 * <p>
 * 数组中某些数字是重复的，但不知道有几个数字是重复的，
 * <p>
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * Input:
 * {2, 3, 1, 0, 2, 5}
 * <p>
 * Output:
 * 2
 */
public class test1 {

    public static void main(String[] args) {
        String str1 = new StringBuilder("Hel").append("lo").toString();
        System.out.println(str1.intern() == str1); //true
        System.out.println("========");

        String strx = new StringBuilder("Hel").append("lo").toString();
        String str = "Hello";
        System.out.println(str == strx); //false

        System.out.println("=======");
        String str2 = "hello";
        String str3 = new String("hello");
        System.out.println(str2 == str3); //false

        System.out.println("=====");
        final String str4 = "hell";
        String str5 = "hello";
        String str6 = str4 + "o";
        System.out.println(str5 == str6); //true

    }

}
