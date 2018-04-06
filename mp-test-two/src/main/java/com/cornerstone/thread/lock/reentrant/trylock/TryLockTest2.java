package com.cornerstone.thread.lock.reentrant.trylock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: liyl
 * @date: 2018/4/5 下午9:33
 * @since 1.0.0
 */
public class TryLockTest2 implements Runnable {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    private int lock;

    public TryLockTest2(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock2.tryLock()) {

                            try {
                                System.out.println(Thread.currentThread().getId() + ": My Job done");
                                return;
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } finally {
                        lock1.unlock();
                    }

                }

            }
        } else {
            while (true) {
                if (lock2.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock1.tryLock()) {

                            try {
                                System.out.println(Thread.currentThread().getId() + ": My Job done");
                                return;
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } finally {
                        lock2.unlock();
                    }

                }

            }
        }
    }

    public static void main(String[] args) {
        TryLockTest2 t1 = new TryLockTest2(1);
        TryLockTest2 t2 = new TryLockTest2(2);
        Thread tr1 = new Thread(t1);
        Thread tr2 = new Thread(t2);
        tr1.start();
        tr2.start();

    }
}
