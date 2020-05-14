package com.firnice.leecode.JewelsandStones;

import java.util.HashSet;
import java.util.Set;

/**
 * 771
 */
class Solution {

    /**
     *  给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
     *
     * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones(String J, String S) {
        char[] jewels = J.toCharArray();
        Set jewelSet = new HashSet();
        for (char jewel : jewels) {
            jewelSet.add(jewel);
        }

        char[] mys = S.toCharArray();

        int have = 0;
        for (char my : mys) {
            if(jewelSet.contains(my)){
                have++;
            }
        }


        return have;
    }
}