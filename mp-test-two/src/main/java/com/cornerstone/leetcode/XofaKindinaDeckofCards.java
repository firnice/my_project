package com.cornerstone.leetcode;

import java.util.*;

public class XofaKindinaDeckofCards {

    public static boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int i : deck) {
            if (countMap.containsKey(i)) {
                countMap.put(i, countMap.get(i) + 1);
            } else {
                countMap.put(i, 1);
            }
        }

        Set<Integer> integers = countMap.keySet();
        Integer min = Integer.MAX_VALUE;
        for (Integer integer : integers) {
            if (countMap.get(integer) < min) {
                min = countMap.get(integer);
            }
        }
        if (min < 2) {
            return false;
        }

        boolean have = true;
        for (int i = 2; i <= min; i++) {
            have= true;
            for (Integer integer : integers) {
                if ((countMap.get(integer) % i) != 0) {
                    have = false;
                    break;
                }
            }
            if (have) {
                return have;
            }
        }

        return have;
    }

    public static void main(String[] args) {
        System.out.println(hasGroupsSizeX(new int[]{1,1,1,2,2,2,3,3}));
//        System.out.println(123);
    }
}
