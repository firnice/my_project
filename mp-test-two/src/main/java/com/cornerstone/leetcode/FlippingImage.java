package com.cornerstone.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 832
 */
public class FlippingImage {

    public int[][] flipAndInvertImage(int[][] A) {

        //init
        List<List<Integer>> matrixList = new ArrayList<>();
        for (int[] a : A) {
            List<Integer> row = new ArrayList();
            for (int i : a) {
                row.add(i);
            }
            matrixList.add(row);
        }


        System.out.println("排序前");
        matrixList.stream().forEach(curRow -> System.out.println(curRow));

//        //排序
//        List<List<Integer>> newMatrixList = new ArrayList<>();
//
//        matrixList.stream().forEach(curRow -> {
//            newMatrixList.add(curRow.stream().sorted(
//                    Integer::compare
//            ).collect(Collectors.toList()));
//        });
//        System.out.println("排序后");
//
//        //输出
//        newMatrixList.stream().forEach(curRow -> System.out.println(curRow));


        matrixList.parallelStream().forEach(Collections::reverse);


        matrixList.stream().forEach(curRow -> {
            curRow.stream().forEach(i -> {
                i = i == 1 ? 0 : 1;
            });
        });

        System.out.println(matrixList);
        return null;
    }
}
