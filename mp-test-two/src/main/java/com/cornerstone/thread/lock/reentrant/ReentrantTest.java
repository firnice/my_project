package com.cornerstone.thread.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: liyl
 * @date: 2018/4/4 下午4:57
 * @since 1.0.0
 */
public class ReentrantTest implements Runnable {

    public static Integer j = 0;

    public static ReentrantLock lock = new ReentrantLock();
    @Override
    public  void run() {

            for (int i = 0; i < 10000; i++) {
                lock.lock();
                try {
                    j++;
                } finally {
                    lock.unlock();
                }
            }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantTest reentrantTest = new ReentrantTest();
        ReentrantTest reentrantTest2 = new ReentrantTest();
        Thread thread1 = new Thread(reentrantTest);
        Thread thread2 = new Thread(reentrantTest2);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(j);
    }
}
