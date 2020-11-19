package com.cornerstone.leetcode;

public class MinimumPathSum {

    /**
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * <p>
     * 说明：每次只能向下或者向右移动一步。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
     * 输出：7
     * 解释：因为路径 1→3→1→1→1 的总和最小。
     * 示例 2：
     * <p>
     * 输入：grid = [[1,2,3],[4,5,6]]
     * 输出：12
     *  
     * <p>
     * 提示：
     * <p>
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 200
     * 0 <= grid[i][j] <= 100
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-path-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    public static int minPathSum(int[][] grid) {

        //左边和上边节点取最小的
        int tmp[][] = new int[grid.length][grid[0].length];

        tmp[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            tmp[i][0] = tmp[i-1][0]+grid[i][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            tmp[0][i] = tmp[0][i-1]+grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                tmp[i][j] = Math.min(tmp[i-1][j],tmp[i][j-1])+grid[i][j];
            }
        }

        return tmp[grid.length-1][grid[0].length-1];
    }

    public static void main(String[] args) {
//        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int[][] grid = {{1,2,3},{4,5,6}};
        int i = minPathSum(grid);
        System.out.println(i);
    }
}
