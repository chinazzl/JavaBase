package leetCode.review;


import java.lang.reflect.Array;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 一共有几种情况：
 *      1. 数组长度为0
 *      2. 数组长度为1
 *      3. 数组长度非0，长度随机
 *
 * 思路：
 *      1. 从第一个元素开始进行遍历比较剩下的所有元素，如果有相同的则后面指针进行前移
 *      2. 前移后，查看一下后面的元素是否全部为相同元素，如果是，则跳出循环，输出从索引0 到 有重复项的个数
 *      3. 如果全部都是不重复的，则直接输出数组长度
 *
 *扩展：
 *给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现两次，返回移除后数组的新长度。
 * 简单做法：
 *       int i = 0;
 *         for (int n : nums)
 *             if (i < 2 || n > nums[i-2])
 *                 nums[i++] = n;
 *         return i;
 */
public class ReverNumber {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,2,2,3,1,3,4};
//        int[] nums = {};
        int ints = removeDumpling(nums);
        System.out.println(ints);
    }

    private static int removeDumpling(int[] nums) {
        if(nums.length ==0 ){
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            int temp = i;
            while (temp < nums.length - 2) {
                if (nums[i] == nums[temp + 1]) {
                    //将该索引的后续元素指针向前移动
                    MovePointer(nums,temp+1);
                    if(isSame(nums,temp)){
                        break;
                    }
                }else{
                    ++temp;
                }
            }
        }
        for (int i = 0; i < nums.length -1; i++) {
            if(nums[i] == nums[i+1]){
                return i+1;
            }
        }
        return nums.length;
    }

    private static void MovePointer(int[] nums, int i) {
        int temp = 0;
        for (int j = i; j < nums.length; j++) {
                nums[j-1] = nums[j];
        }
    }

    private static boolean isSame(int[] nums,int index){
        int tar = 0;
        for (int i = index; i < nums.length -1; i++) {
            if(nums[i] == nums[i+1]){
                tar++;
            }
        }
        return nums.length - index -1 == tar;
    }

}
