package com.firnice.test.se;

import java.util.Random;

public class TestRandom {

    public static void main(String[] args) {
//        Random random = new Random(System.currentTimeMillis());
        Random random = new Random();
//        int keyBucket = random.nextInt(8);
        for (int i = 0; i <10; i++) {
            System.out.println(random.nextInt(8));
        }
    }
}
