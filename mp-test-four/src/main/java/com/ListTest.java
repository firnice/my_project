package com;

import com.google.common.collect.Lists;

import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);


        list.add(1,10);
        System.out.println(list);
    }
}
