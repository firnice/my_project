package com.cornerstone.threadprool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TreadProolTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 2; i++) {
            System.out.println(i);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(" end !!");
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            });
        }
        executorService.shutdown();

    }


}
