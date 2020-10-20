package com.cornerstone.leetcode;

public class PlusOne {

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * <p>
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * <p>
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     * 解释: 输入数组表示数字 123。
     * 示例 2:
     * <p>
     * 输入: [4,3,2,1]
     * 输出: [4,3,2,2]
     * 解释: 输入数组表示数字 4321。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/plus-one
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */

    public static void main(String[] args) {
        int[] ints = plusOne(new int[]{9, 9, 9});
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]);
        }
    }

    public static int[] plusOne(int[] digits) {

        int[] result = new int[digits.length];
        int x = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] + x > 9) {
                result[i] = digits[i] + x - 10;
                x = 1;
            } else {
                result[i] = digits[i] + x;
                x = 0;
            }
        }

        if (x == 1) {
            int[] result1 = new int[digits.length + 1];
            for (int i = 0; i < result.length; i++) {
                result1[i + 1] = result[i];
            }
            result1[0] = x;
            return result1;
        }

        return result;
    }
}
