package com.cornerstone.thread.lock.reentrant.time;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: liyl
 * @date: 2018/4/5 下午2:14
 * @since 1.0.0
 */
public class TimeLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            //if (lock.tryLock(5, TimeUnit.SECONDS)) {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + ": run");
                Thread.sleep(6000);
                System.out.println(Thread.currentThread().getName() + " over ");
            } else {
                System.out.println("get lock failed");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName());
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TimeLock r1 = new TimeLock();
        TimeLock r2 = new TimeLock();
        Thread t1 = new Thread(r1, "r1");
        Thread t2 = new Thread(r2, "r2");
        t1.start();
        Thread.sleep(200);
        t2.start();
        Thread.sleep(200);
        t2.interrupt();

    }
}
