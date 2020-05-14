package com.firnice.test;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestTryAcquire {

    public static RateLimiter rateLimiter = RateLimiter.create(0.5);
    static ExecutorService exec = Executors.newCachedThreadPool();

    static int i =0;

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {

            Thread thread = new Thread(() -> {
                try {
                    //获取许可
                    int a = getI();
                    if (rateLimiter.tryAcquire(1, 1, TimeUnit.SECONDS)) {
                        System.out.println(a + " get");
                    } else {
                        System.out.println(a + " lost");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
            //执行线程
            exec.execute(thread);
        }
    }

    public static synchronized int getI(){
        i++;
        return i;
    }
}
