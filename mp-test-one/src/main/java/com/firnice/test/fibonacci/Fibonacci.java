package com.firnice.test.fibonacci;

/**
 * @author: liyl
 * @date: 2018/4/6 下午6:43
 * @since 1.0.0
 */
public class Fibonacci {
    public static Integer fibFun(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }

        return fibFun(n - 2) + fibFun(n - 1);
    }

    public static Integer fibFun2(int n) {
        int firstN = 1, secondN = 1;
        int temp;
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        for (int i = 2; i < n; i++) {
            temp = secondN;
            secondN = firstN + secondN;
            firstN = temp;
        }

        return firstN+secondN;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(fibFun(40));
        System.out.println(System.currentTimeMillis() - start);
        long start2 = System.currentTimeMillis();
        System.out.println(fibFun2(1000));
        System.out.println(System.currentTimeMillis() - start2);
    }

}
