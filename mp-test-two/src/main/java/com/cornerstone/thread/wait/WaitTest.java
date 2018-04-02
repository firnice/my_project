package com.cornerstone.thread.wait;

/**
 * @author: liyl
 * @date: 2018/4/2 下午4:07
 * @since 1.0.0
 */
public class WaitTest {

    public static Object object = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(" wait ");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(" notify ");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                object.notify();

                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 end");
            }
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.start();
        t2.start();
    }

}
