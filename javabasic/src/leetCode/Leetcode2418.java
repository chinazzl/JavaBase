package leetCode;

import java.util.Arrays;

/**********************************
 * @author zhang zhao lin
 * @date 2023年04月25日 22:18
 * @Description: 使用选择排序实现了
 * 给你一个字符串数组 names ，和一个由 互不相同 的正整数组成的数组 heights 。两个数组的长度均为 n 。
 * <p></p>
 * 对于每个下标 i，names[i] 和 heights[i] 表示第 i 个人的名字和身高。
 *
 * 请按身高 降序 顺序返回对应的名字数组 names
 **********************************/
public class Leetcode2418 {

    public static void main(String[] args) {
        //String[] names = {"Mary","John","Emma"};
        //int[] heights = {180, 165, 170};
        String[] names = {"Alice","Bob","Bob"};
        int[] heights = {155,185,150};

        String[] sortNames = getSortNames(names, heights);

        for (String sortName : sortNames) {
            System.out.println(sortName);
        }
    }


    /**
     * 输入：names = ["Mary","John","Emma"], heights = [180,165,170]
     * 输出：["Mary","Emma","John"]
     * 解释：Mary 最高，接着是 Emma 和 John 。
     * <p>
     * 输入：names = ["Alice","Bob","Bob"], heights = [155,185,150]
     * 输出：["Bob","Alice","Bob"]
     * 解释：第一个 Bob 最高，然后是 Alice 和第二个 Bob 。
     *
     * @param names   名字
     * @param heights 身高
     * @return 按照高到低排序数组
     */
    static String[] getSortNames(String[] names, int[] heights) {
        int[] copyArr = Arrays.copyOf(heights,heights.length);
        int maxIndex;
        for (int i = 0; i < heights.length - 1; i++) {
            maxIndex = i;
            for (int j = i + 1; j < heights.length; j++) {
                if (heights[maxIndex] < heights[j]) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                swap(heights, i, maxIndex);
            }
        }
        String[] sortNames = new String[names.length];
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < copyArr.length; j++) {
                if (heights[i] == copyArr[j]) {
                    sortNames[i] = names[j];
                }
            }
        }

        return sortNames;
    }

    private static void swap(int[] arr, int i, int maxIndex) {
        int temp = arr[maxIndex];
        arr[maxIndex] = arr[i];
        arr[i] = temp;
    }

}
