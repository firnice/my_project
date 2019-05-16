package com.cornerstone.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        for (int num : nums) {
            //遍历之前的
            List<List<Integer>> lists = new ArrayList<>();
            for (List<Integer> integers : result) {
                ArrayList<Integer> list = new ArrayList<>(integers);
                list.add(num);
                lists.add(list);
            }
            result.addAll(lists);
            //自己
            result.add(new ArrayList<Integer>() {{
                add(num);
            }});
        }
        //空
        result.add(new ArrayList<>());

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> subsets = subsets(new int[]{1, 2, 3});

        for (List<Integer> subset : subsets) {
            System.out.println(subset);

        }

    }
}
