package com.cornerstone.leetcode;

import java.util.Arrays;

public class Heaters {

    public static int findRadius(int[] houses, int[] heaters) {

        Arrays.sort(houses);
        Arrays.sort(heaters);
        int j = 0;
        int max = 0;
        for (int i = 0; i < houses.length; i++) {
            int left = Integer.MAX_VALUE, right = Integer.MAX_VALUE, min = -1;
            if (houses[i] < heaters[j]) {
                right = heaters[j] - houses[i];
            } else {
                while ((j + 1 < heaters.length) && (houses[i] > heaters[j] && houses[i] >= heaters[j + 1])) {
                    j++;
                }
                left = houses[i] - heaters[j];
                if (j + 1 < heaters.length) {
                    right = heaters[j + 1] - houses[i];
                }
            }
            if (right == 0 || left == 0) {
                continue;
            }
            if (right < left) {
                min = right;
            } else {
                min = left;
            }
            if (max < min) {
                max = min;
            }
        }
        return max;
    }


    public static void main(String[] args) {


        //2
//        [282475249,622650073,984943658,144108930,470211272,101027544,457850878,458777923]
//[823564440,115438165,784484492,74243042,114807987,137522503,441282327,16531729,823378840,143542612]
        System.out.println(findRadius(new int[]{282475249, 622650073, 984943658, 144108930, 470211272, 101027544, 457850878, 458777923}, new int[]{823564440, 115438165, 784484492, 74243042, 114807987, 137522503, 441282327, 16531729, 823378840, 143542612}));

//        System.out.println(findRadius(new int[]{282, 622, 984, 144, 470, 101, 457, 458}, new int[]{823564440, 115438165, 784484492, 74243042, 114807987, 137522503, 441282327, 16531729, 823378840, 143542612}));

    }
}