package com.cornerstone.leetcode;

public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {

        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            if(str.length()<minLength){
                minLength = str.length();
            }
        }

        StringBuffer result = new StringBuffer("");
        if(strs.length == 0){
            return result.toString();
        }
        for (int i = 0; i < minLength; i++) {
            char a = strs[0].charAt(i);
            for (String str : strs) {
                if(a != str.charAt(i)){
                    return result.toString();
                }
            }
            result.append(a);
        }

        return result.toString();
    }

    public static void main(String[] args) {
//        String[] strs = {"flower", "flow", "flight"};
        String[] strs = {"a"};
        longestCommonPrefix(strs);

    }
}
