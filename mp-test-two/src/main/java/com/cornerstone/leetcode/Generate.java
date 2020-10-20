package com.cornerstone.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Generate {

    /**
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     * <p>
     * <p>
     * <p>
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     * <p>
     * 示例:
     * <p>
     * 输入: 5
     * 输出:
     * [
     * [1],
     * [1,1],
     * [1,2,1],
     * [1,3,3,1],
     * [1,4,6,4,1]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/pascals-triangle
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> row = new ArrayList<>();

            for (int j = 0; j < i + 1; j++) {
                if (i == 0) {//第一行 第一个
                    row.add(1);
                    continue;
                }
                if (j == 0) {
                    row.add( list.get(i - 1).get(j));
                } else if (j > 0 && j != i) {
                    row.add(list.get(i - 1).get(j - 1) + list.get(i - 1).get(j));
                } else {
                    row.add(list.get(i - 1).get(j - 1));
                }
            }


            list.add(row);

        }

        return list;

    }

    public static void main(String[] args) {

        List<List<Integer>> generate = generate(5);
        for (List<Integer> integers : generate) {
            for (Integer integer : integers) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }

    }
}
