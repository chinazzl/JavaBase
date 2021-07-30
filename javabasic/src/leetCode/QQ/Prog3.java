package leetCode.QQ;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class Prog3 {
    public static void main(String[] args) {
        int[] num1 = {1,2,3};
        int[] num2 = {2,3};
        int i = checkMidNum(num1, num2);
        System.out.println(i);
    }

    private static int checkMidNum(int[] num1, int[] num2){
        //将两个数组合并为一个数组
        int len = num1.length + num2.length;
        int[] new_arr = new int[len];
        for (int i = 0,j=0; i < new_arr.length; i++) {
            if(num1.length > i){
                new_arr[i] = num1[i];
            }else{
                new_arr[i] = num2[j];
                j++;
            }
        }

        //处理数组， 1. 数组长度是偶数 2. 数组长度是奇数 3.
        int mid = len%2;
        if(mid > 0){ // 奇数
            return new_arr[mid+1];
        }else{
            return (new_arr[mid] + new_arr[mid+1])/2;
        }
    }
}
