package com.cornerstone.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingSubsequence {


    /**
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     * <p>
     * 示例:
     * <p>
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * 说明:
     * <p>
     * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
     * 你算法的时间复杂度应该为 O(n2) 。
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {

        int[] dp = new int[nums.length];

        Arrays.fill(dp, 1);

        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    public static int lengthOfLIS2(int[] nums) {
        //todo 贪心，每次放入最小的

        /**
         * 找到的序列是单调递增的，个数不能超过总数和当前循环数
         */
        /**
         * 10 a[10]
         * 9  a[9]
         * 2  a[2]
         * 5  a[2,5]
         * 3  a[2,3]
         * 7  a[2,3,7]
         * 101 a[2,3,7,101]
         * 18 a[2,3,7,10]
         */

        /**
         * 1, 比a[x] 大，加
         * 2，比a[x] 小，往前找，替换
         */
        ArrayList<Integer> a = new ArrayList<>();
        a.add(nums[0]);
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > a.get(j)) {
                a.add(nums[i]);
                j++;
            } else if (nums[i] < a.get(j)) {
                //todo 二分法
                for (int i1 = 0; i1 < a.size(); i1++) {
                    if(a.get(i1) == nums[i]){
                        break;
                    }
                    if (a.get(i1) > nums[i]) {
                        a.set(i1, nums[i]);
                        break;
                    }
                }
            }
        }
        return a.size();
    }

    public static void main(String[] args) {

//        int[] a = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int[] a = new int[]{4, 10, 4, 3, 8, 9};
        int i = lengthOfLIS(a);
        System.out.println(i);
        i = lengthOfLIS2(a);
        System.out.println(i);
    }
}

/**
 * 4,10,4,3,8,9
 *
 *
 * 4, [4]
 * 10,[4,10]
 * 4, [4,10]
 * 3, [3,10]
 * 8, [3,8]
 * 9, [3,8,9]
 */
