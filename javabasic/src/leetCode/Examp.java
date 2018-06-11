package leetCode;

public class Examp {
    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] nums = {2,5,5,11};
        int[] ints = solution.twoSum(nums, 10);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }


}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] rs = new int[2];
        int count = 0;
        for(int i = 0; i<nums.length;i++){
            for(int j =i+1;j<nums.length;j++){
                if(nums[i]+nums[j] == target && count == 0){
                    rs[0] = i;
                    rs[1] = j;
                    count++;
                }
            }
        }
        return rs;
    }
}