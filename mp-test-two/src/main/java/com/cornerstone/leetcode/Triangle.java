package com.cornerstone.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Triangle {

    /**
     * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     *
     * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
     *
     *  
     *
     * 例如，给定三角形：
     *
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     *
     *  
     *
     * 说明：
     *
     * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/triangle
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    /**
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> rowRes1 = new ArrayList<>();
        rowRes1.addAll(triangle.get(0));
        res.add(rowRes1);
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> row = triangle.get(i);//当前行
            List<Integer> lastRowRes = res.get(i - 1);//上一行总计
            List<Integer> rowRes = new ArrayList<>();//当前行的总计
            for (int j = 0; j < row.size(); j++) {
                /**
                 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
                 * j = i || j = i +1
                 *
                 * 最左 i=j
                 * 中间 min
                 * 最右 j= i-1
                 */
                if (j == 0) {
                    rowRes.add(row.get(j) + lastRowRes.get(j));
                } else if (j == row.size() - 1) {
                    rowRes.add(row.get(j) + lastRowRes.get(j-1));
                } else {
                    int min = Math.min(lastRowRes.get(j), lastRowRes.get(j - 1));
                    rowRes.add(row.get(j) + min);
                }

            }
            res.add(rowRes);
        }
        List<Integer> lastRes = res.get(res.size() - 1);
        int min = lastRes.get(0);
        for (int i = 1; i < lastRes.size(); i++) {
            if (min > lastRes.get(i)) {
                min = lastRes.get(i);
            }
        }

        return min;
    }

    public static void main(String[] args) {
        /*
         * [
         *      [2],
         *     [3,4],
         *    [6,5,7],
         *   [4,1,8,3]
         * ]
         */

        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> row1 = new ArrayList<Integer>() {
        };
        row1.add(2);
        triangle.add(row1);
        List<Integer> row2 = new ArrayList<Integer>() {
        };
        row2.add(3);
        row2.add(4);
        triangle.add(row2);
        List<Integer> row3 = new ArrayList<Integer>() {
        };
        row3.add(6);
        row3.add(5);
        row3.add(7);
        triangle.add(row3);
        List<Integer> row4 = new ArrayList<Integer>() {
        };
        row4.add(4);
        row4.add(1);
        row4.add(8);
        row4.add(3);
        triangle.add(row4);

        System.out.println(minimumTotal(triangle));

    }
}
