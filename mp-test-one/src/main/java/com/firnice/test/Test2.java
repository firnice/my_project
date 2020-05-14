package com.firnice.test;

/**
 * @author: liyl
 * @date: 2018/4/9 下午9:40
 * @since 1.0.0
 */
public class Test2 {

    public static void main(String[] args) {
        System.out.println(removeDup("aabbccdefgabc"));
    }

    public static String removeDup(String str) {
        char last = str.charAt(0);
        StringBuffer result = new StringBuffer(String.valueOf(str.charAt(0)));

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != last) {
                result.append(str.charAt(i));
            }
            last = str.charAt(i);
        }
        return result.toString();
    }

}
