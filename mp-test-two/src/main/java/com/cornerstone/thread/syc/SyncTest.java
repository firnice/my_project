package com.cornerstone.thread.syc;

/**
 * @author: liyl
 * @date: 2018/4/19 下午1:51
 * @since 1.0.0
 */
public class SyncTest {
    private int id;

    public SyncTest(int id) {
        this.id = id;
    }

    public synchronized void fun1(int id) throws InterruptedException {
        System.out.println(id + "fun1 time"+System.currentTimeMillis());
        Thread.currentThread().sleep(3000);
        System.out.println(id + " fun1 end");
    }

    public static synchronized void fun2(int id) throws InterruptedException {
        System.out.println(id + "fun2 time"+System.currentTimeMillis());
        Thread.currentThread().sleep(3000);
        System.out.println(id + "fun2 end");

    }

    public void fun3(int id) throws InterruptedException {
        synchronized (this) {
            System.out.println(id + "fun3 time"+System.currentTimeMillis());

            Thread.currentThread().sleep(3000);
            System.out.println(id + "fun3 end");
        }

    }

    public static void main(String[] args) {
        SyncTest test1 = new SyncTest(1);
        SyncTest test2 = new SyncTest(2);
        Thread t = new Thread(() -> {
            try {
                //test1.fun1();
                //test1.fun2(1);
                test1.fun1(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                //test2.fun1();
                test1.fun3(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();
        t2.start();
    }

}
