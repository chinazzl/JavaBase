package thinkingcode;

/**
 * @author: zhang zhao lin
 * @Date: Create in 2023/12/13 下午9:59
 * @Modified By： 二分查找
 * @Description: 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在
 * 返回下标，否则返回 -1。
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 * 提示：
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间
 */
public class BiSectionSearch {
    public static void main(String[] args) {
        int[] nums = {2,4,3,1,5,7};
        int target = 3;
        int i = simpleBiSection2(nums, target);
        System.out.println(i);
    }

    /**
     * 设置左闭右闭合
     * @param nums
     * @param target
     * @return
     */
    private static  int simpleBiSection(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        // 没找到数据
        return -1;
    }

    /**
     * 左区间闭合区间，右边为开区间
     */
    private static int simpleBiSection2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + ((right - left)/2);
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
