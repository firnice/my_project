package com.cornerstone.leetcode;

public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {

        int[] newNums = new int[nums.length];
        int tmp = 1;
        for (int i = 0; i < nums.length; i++) {
            newNums[i] = tmp;
            tmp *= nums[i];
        }
        tmp = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            newNums[i] *= tmp;
            tmp *= nums[i];
        }
        return newNums;
    }
}
