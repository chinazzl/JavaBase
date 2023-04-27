package leetCode;

/**
 * @author Julyan
 * @version V1.0
 * @Date: 2023/4/26 8:55
 * @Description: 给你一个整数数组 nums 和两个整数 firstLen 和 secondLen，请你找出并返回两个非重叠 子数组 中元素的最大和，长度分别为 firstLen 和 secondLen 。
 * 长度为 firstLen 的子数组可以出现在长为 secondLen 的子数组之前或之后，但二者必须是不重叠的。
 * 子数组是数组的一个 连续 部分。
 * 使用算法中的“前缀和”
 *
 */
public class Leetcode1031 {

    public static void main(String[] args) {

    }

    /**
     * 输入：nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
     * 输出：20
     * 解释：子数组的一种选择中，[9] 长度为 1，[6,5] 长度为 2。
     * <p>
     * 输入：nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
     * 输出：29
     * 解释：子数组的一种选择中，[3,8,1] 长度为 3，[8,9] 长度为 2。
     * <p>
     * 输入：nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
     * 输出：31
     * 解释：子数组的一种选择中，[5,6,0,9] 长度为 4，[0,3,8] 长度为 3。
     *
     * @param nums
     * @param firstLen
     * @param secondLen
     * @return
     */
    static int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {

        return 0;
    }

    /**
     * 实现一个滑动窗口
     * @param nums
     * @param slideNum
     * @return
     */
    private static int slideWindow(int[] nums, int slideNum) {
        int mv = 0;
        while (mv < nums.length) {
            
            mv += slideNum;

        }
        return 0;
    }

}
