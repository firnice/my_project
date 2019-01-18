package com.cornerstone.leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();
        for (int i = 0; i < 2 * n; i++) {
            if (result.size() == 0) {
                result.add("(");
            } else {
                List newList = new ArrayList<>();
                for (int j = 0; j < result.size(); j++) {
                    String s = result.get(j);
                    result.set(j, result.get(j) + "(");
                    String s2 = s + ")";
                    newList.add(s2);
                }

                result.addAll(newList);
            }

        }

        List<String> list = new ArrayList<>();
        for (String s : result) {
            int stack = 0;
            boolean isOk = true;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if ('(' == c) {
                    stack++;
                }
                if (')' == c) {
                    stack--;
                }
                if (stack < 0 || stack > n) {
                    isOk = false;
                    break;
                }

            }
            if (isOk && stack == 0) {
                list.add(s);
            }
        }


        return list;

    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
