package com.cornerstone.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public static void main(String[] args) {
        List<List<Integer>> permute = permute(new int[]{1, 2, 3});
        for (List<Integer> integers : permute) {
            System.out.println(integers);
        }

    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        for (int num : nums) {
            intList.add(num);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i; j++) {
                if (i == 0) {
                    int num = nums[j];
                    list.add(new ArrayList() {{
                        add(num);
                    }});
                } else {

                    List<List<Integer>> list2 = new ArrayList<>();//中间变量
                    for (List<Integer> integers : list) {//把之前的遍历出来
                        List<Integer> curInt = new ArrayList<>();//备选
                        for (int num : nums) {
                            if (integers.contains(num)) {
                                continue;
                            }
                            curInt.add(num);
                        }
                        for (Integer integer : curInt) {
                            List<Integer> newList = new ArrayList<>();
                            for (Integer integer1 : integers) {
                                newList.add(integer1);
                            }

                            newList.add(integer);
                            list2.add(newList);
                        }
                    }
                    if (list2.isEmpty()) {
                        break;
                    }
                    list = list2;
                }
            }

        }


        return list;
    }


}
