package com;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

public class SetTest {
    public static void main(String[] args) {
        Set<String> list = Sets.newHashSet();
        list.add("1");
        list.add("2");
//        list.add(3);
//        list.add(4);


        System.out.println(list.toString());
    }
}
