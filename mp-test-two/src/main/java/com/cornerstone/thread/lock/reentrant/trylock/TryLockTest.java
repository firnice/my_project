package com.cornerstone.thread.lock.reentrant.trylock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: liyl
 * @date: 2018/4/5 下午9:33
 * @since 1.0.0
 */
public class TryLockTest implements Runnable {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    private int lock;

    public TryLockTest(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        System.out.println(Thread.currentThread().getId() + ": get lock1");
                        Thread.sleep(200);
                        if (lock2.tryLock()) {
                            System.out.println(Thread.currentThread().getId() + ": get lock2");
                            try {
                                Thread.sleep(500);
                                System.out.println(Thread.currentThread().getId() + ": My Job done");
                                return;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                System.out.println(Thread.currentThread().getId() + ": release lock2");
                                lock2.unlock();
                            }
                        } else {
                            System.out.println(Thread.currentThread().getId() + ": get filed lock2");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println(Thread.currentThread().getId() + ": release lock1");
                        lock1.unlock();
                    }

                } else {
                    System.out.println(Thread.currentThread().getId() + ": get filed lock1");

                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } else {
            while (true) {
                if (lock2.tryLock()) {
                    System.out.println(Thread.currentThread().getId() + ": get lock2");
                    try {
                        Thread.sleep(50);
                        if (lock1.tryLock()) {
                            System.out.println(Thread.currentThread().getId() + ": get lock1");
                            try {
                                Thread.sleep(50);
                                System.out.println(Thread.currentThread().getId() + ": My Job done");
                                return;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                System.out.println(Thread.currentThread().getId() + ": release lock1");
                                lock1.unlock();
                            }
                        } else {
                            System.out.println(Thread.currentThread().getId() + ": get filed lock1");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println(Thread.currentThread().getId() + ": release lock2");
                        lock2.unlock();
                    }

                } else {
                    System.out.println(Thread.currentThread().getId() + ": get filed lock2");

                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        TryLockTest t1 = new TryLockTest(1);
        TryLockTest t2 = new TryLockTest(2);
        Thread tr1 = new Thread(t1);
        Thread tr2 = new Thread(t2);
        tr1.start();
        tr2.start();

    }
}
