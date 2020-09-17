package com.cornerstone.leetcode;

import java.util.HashSet;
import java.util.Iterator;

public class IntersectionOfTwoArrays {

    public static int[] intersection(int[] nums1, int[] nums2) {
        if(nums1.length < nums2.length){
            return intersection(nums2, nums1);
        }


        HashSet<Integer> hashSet = new HashSet<>();
        HashSet<Integer> hashSet1 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            hashSet.add(nums1[i]);
        }
        for (int j = 0; j < nums2.length; j++) {
            if (hashSet.contains(nums2[j])){
                hashSet1.add(nums2[j]);
            }
        }
        int[] ints = new int[hashSet1.size()];
        int i = 0;
        Iterator<Integer> iterator = hashSet1.iterator();
        while (iterator.hasNext()) {
            ints[i]=iterator.next();
            i++;
        }

        return ints;
    }

    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * 示例 2:
     *
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[4,9]
     *  
     *
     * 说明：
     *
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
     * 我们可以不考虑输出结果的顺序。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */

    public static void main(String[] args) {
//        int[] nums1 = {1,2,2,1};
//        int[] nums2 = {2,2};

        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        int[] intersect = intersection(nums1, nums2);
        for (int i = 0; i < intersect.length; i++) {
            System.out.print(intersect[i]+" ");
        }
    }
}
