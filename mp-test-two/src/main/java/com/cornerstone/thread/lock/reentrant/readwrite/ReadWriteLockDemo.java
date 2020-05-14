package com.cornerstone.thread.lock.reentrant.readwrite;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: liyl
 * @date: 2018/4/15 下午10:09
 * @since 1.0.0
 */
public class ReadWriteLockDemo {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static ReentrantLock lock = new ReentrantLock();

    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    public static  volatile int value = 10;

    public int handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            if (value <=0) {
//                Math.pow(a,b);
                return 0;
            }
            Thread.sleep(10);
            System.out.println(Thread.currentThread().getId()+": read value="+value);
            return value;
        } finally {
            lock.unlock();
        }

    }

    public int handleWrite(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(50);
            if (value > 0) {
                value--;
            }else {
                return 0;
            }
            System.out.println(Thread.currentThread().getId()+": wite value="+value);
            return value;
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleRead(readLock);
                    //demo.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleWrite(writeLock);
                    //demo.handleWrite(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService e = Executors.newFixedThreadPool(30);

        long l = System.currentTimeMillis();
        while (value>0){
            //new Thread(readRunnable).start();
            //
            //new Thread(writeRunnable).start();

            e.execute(readRunnable);
            e.execute(readRunnable);
            e.execute(readRunnable);
            e.execute(readRunnable);
            e.execute(readRunnable);
            e.execute(readRunnable);
            e.execute(readRunnable);
            e.execute(writeRunnable);
        }
        e.shutdown();
        System.out.println(System.currentTimeMillis() - l +" ms");
    }
}
