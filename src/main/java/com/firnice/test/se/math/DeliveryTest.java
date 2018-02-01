package com.firnice.test.se.math;

/**
 * @author: liyl
 * @date: 2017/9/18 下午5:51
 * @since 1.0.0
 */
public class DeliveryTest {

    public static void main(String[] args) {

        int x = 0, y = 0;
        for (int i = 1000; i <2000; i++) {
            int temp = i % 100;
            if (temp < 50) {
                x++;
            } else {
                y++;
            }
        }
        System.out.println(x);
        System.out.println(y);
    }
}
