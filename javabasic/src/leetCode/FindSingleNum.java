package leetCode;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2019/12/23 17:49
 * @Modified By：
 * [ 2,2,1] -> 1
 * <p>
 * [4,1,2,1,2] -> 4
 */
public class FindSingleNum {

    public static void main(String[] args) {
        int[] nums = {4,1,2,1,2};
        int rsInt = findLonglyNum(nums);
        //使用异或运算，变为二进制，相同取0 不同取1
        System.out.println(rsInt);
    }

    /**
     * 找到没有重复的数字
     *
     * @param nums
     * @return
     */
    private static int findLonglyNum(int[] nums) {
        int rs = 0;
        for (int i = 0; i < nums.length; i++) {
            rs ^= nums[i];
        }
        return rs;
    }

}
