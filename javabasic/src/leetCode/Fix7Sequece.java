package leetCode;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/5/25 23:19
 * @Modified By：
 * 输入一个数，如果长度小于7则在前面补0
 * 最小值  0000001
 * 最大   9999999
 */
public class Fix7Sequece {
    private static int c = 0;

    public static void main(String[] args) {
        int num = 1111111111;
        int fixs = fix7sequences(num);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7-fixs; i++) {
            sb.append("0");
        }
        sb.append(num);
        System.out.println(sb.toString());

        System.out.println(Long.MAX_VALUE);
    }

    private static int fix7sequences(int num) {
        int n = num % 10;
        num = num / 10;
        if (n != 0) {
            c++;
            fix7sequences(num);
        }
        return c;
    }
}
