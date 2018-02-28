package com.cornerstone.leetcode;

/**
 * @author: liyl
 * @date: 2018/2/28 下午5:47
 * @since 1.0.0
 */
class Solution {

    public static void main(String[] args) {
        int[] ints = twoSum(new int[] {3, 2, 4}, 6);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
    }

    public static int[]  twoSum(int[] nums, int target) {
        for(int i = 0;i<nums.length-1;i++){
            for(int j = i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
}
