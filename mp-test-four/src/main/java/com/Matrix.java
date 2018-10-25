package com;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Matrix {

    public void fun() {
        //init
        List<List<Integer>> matrixList = Lists.newArrayList();
        List<Integer> row1 = Lists.newArrayList(0, 1, 0);
        List<Integer> row2 = Lists.newArrayList(1, 1, 0);
        List<Integer> row3 = Lists.newArrayList(0, 1, 1);
        matrixList.add(row1);
        matrixList.add(row2);
        matrixList.add(row3);
        System.out.println("排序前");
        matrixList.stream().forEach(curRow -> System.out.println(curRow));

        //排序
        List<List<Integer>> newMatrixList = Lists.newArrayList();

        matrixList.stream().forEach(curRow -> {
            newMatrixList.add(curRow.stream().sorted(
                    Integer::compare
            ).collect(Collectors.toList()));
        });
        System.out.println("排序后");

        //输出
        newMatrixList.stream().forEach(curRow -> System.out.println(curRow));


        matrixList.parallelStream().forEach(Collections::reverse);
        Collections.reverse(matrixList);

        System.out.println(matrixList);
    }
}
