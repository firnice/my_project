package com.cornerstone.thread.deamon;

/**
 * @author: liyl
 * @date: 2018/4/3 上午11:54
 * @since 1.0.0
 */
public class DeamonTest {

    public static class Run implements Runnable{

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("i am alive !");
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread run = new Thread(new Run());
        run.setDaemon(true);
        run.start();
        Thread.currentThread().sleep(200);

    }
}
