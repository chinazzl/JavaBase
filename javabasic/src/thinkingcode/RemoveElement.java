package thinkingcode;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**********************************
 * @author zhang zhao lin
 * @date 2023年12月15日 15:40
 * @Description: 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 示例 1: 给定 nums = [3,2,2,3], val = 3, 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2: 给定 nums = [0,1,2,2,3,0,4,2], val = 2, 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 **********************************/
public class RemoveElement {
    public static void main(String[] args) {
        int[] elements = {1, 2, 4, 5, 3, 2};
        int removeElement = 2;
        System.out.println(removeElement(elements, removeElement));

        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        long monthDiff = getMonthDiff(startDate, endDate);
        System.out.println("相差月份数：" + monthDiff);
    }


    public static long getMonthDiff(LocalDate startDate, LocalDate endDate) {
        long monthDiff = ChronoUnit.MONTHS.between(startDate.withDayOfMonth(1), endDate.withDayOfMonth(1));
        return monthDiff;
    }


    /**
     * 快慢指针处理数组，快指针用于遍历数据，慢指针用于返回最后结果
     * @param elements
     * @param removeElement
     * @return
     */
    private static int fastSlowSameDirect(int[] elements, int removeElement) {
        return 0;
    }


    /**
     * 方法一：暴力破解
     *
     * @param nums
     * @param removeValue
     * @return
     */
    private static int removeElement(int[] nums, int removeValue) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] == removeValue) {
                for (int j = i + 1; j < size; j++) {
                    nums[j - 1] = nums[j];
                }
                i--;
                size--;
            }
        }
        return size;
    }

}