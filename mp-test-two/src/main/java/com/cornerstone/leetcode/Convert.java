package com.cornerstone.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * <p>
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Convert {


    public static String convert(String s, int numRows) {

        List<List<String>> strList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<String> curList = new ArrayList<>();
            strList.add(curList);
        }

        int a = 0;

        int count = numRows;

        for (int x = 0; x < s.length(); x++) {
            if (count == numRows) {
                for (int j = 0; j < numRows; j++) {
                    strList.get(j).add(String.valueOf(s.charAt(a++)));
                    if(a == s.length()){
                        break;
                    }
                }
                count--;
            } else {
                strList.get(count - 1).add(String.valueOf(s.charAt(a++)));
                count--;
            }
            if (count <= 1) {
                count = numRows;
            }
            if(a == s.length()){
                break;
            }
        }

        String str = "";
        for (List<String> curList : strList) {
            for (String s1 : curList) {
                str += s1;
//                System.out.print(s1 +" ");
            }
//            System.out.println("");
        }
//        System.out.println("===============");

        return str;


    }

    public static void main(String[] args) {
//        System.out.println(convert("LEETCODEISHIRING",3));
//        System.out.println(convert("LEETCODEISHIRING",4));
        System.out.println(convert("AB",1));
    }
}
