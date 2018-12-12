package com.cornerstone.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NondecreasingArray {

    public static boolean checkPossibility(int[] nums) {
        //反证，及必然含有去掉一个数后，是非递减数列
        List<Integer> a = new ArrayList();
        for (int num : nums) {
            a.add(num);
        }

        for (int i = 0; i < a.size(); i++) {
            Integer remove = a.remove(i);
            boolean ok = true;
            for (int j = 0; j < a.size() - 1; j++) {
                if (a.get(j) > a.get(j + 1)) {
                    ok = false;
                    break;
                }
            }
            if (ok){
                return true;
            }
            a.add(i,remove);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkPossibility(new int[]{3, 4, 2, 3}));
    }
}
