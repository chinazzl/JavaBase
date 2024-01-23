package leetCode.review;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 * <p>
 *  示例 2:
 * 输入: -123
 * 输出: -321
 * <p>
 * 示例 3:
 * 输入: 120
 * 输出: 21
 * 注意
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class ReverseSortArr {
    public static void main(String[] args) {
        int a = 1534236469;
        int r = reverse(a);
        System.out.println(r);
    }

    private static int reverse(int a) {
        /*
        temp 用于存储每个反转的数字，拼接的时候例如123 取模后123%10 = 3 ，
            第一次 3 + 0*10 = 3
            第二次 12%10 = 2 ， 2 + 3*10 = 32
            ... 以此类推
         */
        int temp = 0;
        int prev = 0;

        while (a != 0){
            temp = a%10 + prev * 10;
            //如果反转之后的数字溢出，则返回0
            if(temp/10 != prev){
                return 0;
            }
            prev =  temp;
            a /=10;
        }
        return  temp;
    }
}
