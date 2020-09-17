package com.firnice.test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: liyl
 * @date: 2018/4/9 下午8:52
 * @since 1.0.0
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(fun());
    }

    public static Integer fun(){
        int A[] = {1, 2, 3,6,5,2};


        Set<Integer> set = new HashSet<Integer>();

        for(int i=0; i< A.length; i++){
            set.add(A[i]);
        }

        for(int i=1; i<= A.length; i++){
            if(set.contains(i) != true) // the 1st missing element
                return i;
        }


        return A.length+1;
    }
}
