package com.cornerstone.leetcode;

public class HouseRobbe {


    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2：
     * <p>
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *  
     * <p>
     * 提示：
     * <p>
     * 0 <= nums.length <= 100
     * 0 <= nums[i] <= 400
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {

        /**
         *  上一个节点
         *
         *  选择
         *  不选择
         *
         *  max(上一个选择，nums+上一个不选)
         */


        int[] res = new int[nums.length];
        res[0] = nums[0];
        res[1] = Math.max(res[0], nums[1]);

        //dp[i]=max(dp[i−2]+nums[i],dp[i−1])
        for (int i = 2; i < nums.length; i++) {
            res[i] = Math.max(res[i - 2] + nums[i], res[i - 1]);
        }
        return res[nums.length - 1];
    }

    public static int rob2(int[] nums) {
        if(nums.length == 0 ){
            return 0;
        }
        if(nums.length == 1 ){
            return nums[0];
        }

        //上上个的最优
        int res1 = 0;
        //上个的最优
        int res2 = 0;
        res1 = nums[0];
        res2 = Math.max(res1, nums[1]);
        int res = res2;

        for (int i = 2; i < nums.length; i++) {
            res = Math.max(res1 + nums[i], res2);
            res1 = res2;
            res2 = res;
        }
        return res;
    }

    public static void main(String[] args) {

        int[] nums = {100, 2, 3, 100};
        System.out.println(rob2(nums));
    }
}
