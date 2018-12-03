package com.cornerstone.leetcode;

public class PeakIndexinaMountainArray {

    public static int peakIndexInMountainArray(int[] A) {
        int max =0;
        int maxIndex = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i]>max){
                max = A[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }


    public static void main(String[] args) {

        int[] A = {0, 2, 1, 0};
        System.out.println(peakIndexInMountainArray(A));
    }
}

