package com.cornerstone.leetcode;

/**
 * 832
 */
public class FlippingImage2 {

    public int[][] flipAndInvertImage(int[][] A) {

        int temp = 0;

        //交换
        for (int[] arrs : A) {

            for (int i = 0; i < arrs.length / 2; i++) {
                temp = arrs[i];
                arrs[i] = arrs[arrs.length - 1 - i];
                arrs[arrs.length - 1 - i] = temp;
            }
        }


        //反转
        for (int[] arrs : A) {

            for (int i = 0; i < arrs.length; i++) {
                arrs[i] = (arrs[i] == 0 ? 1 : 0);
            }
        }

        return A;
    }


}
