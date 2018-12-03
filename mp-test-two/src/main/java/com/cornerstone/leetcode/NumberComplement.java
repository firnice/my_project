package com.cornerstone.leetcode;

public class NumberComplement {
    public static int findComplement(int num) {

        System.out.println(~num);
        System.out.println(Integer.highestOneBit(num));
        String s = Integer.toBinaryString(num);
        char[] tmp = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                tmp[i]='1';
            }else {
                tmp[i]='0';
            }
        }

        System.out.println(num ^ 0);


        return Integer.valueOf(new String(tmp),2);

    }

    public static void main(String[] args) {
        System.out.println(findComplement(5));
    }
}
