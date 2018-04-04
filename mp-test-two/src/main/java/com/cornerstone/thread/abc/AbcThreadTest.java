package com.cornerstone.thread.abc;

import java.util.Random;

/**
 * @author: liyl
 * @date: 2018/4/3 上午10:26
 * @since 1.0.0
 */
public class AbcThreadTest {

    public static volatile Object object = new Object();

    public static class Player extends Thread {
        private String name;

        public Player(String name) {
            this.name = name;
        }

        @Override
        public void run() {

            synchronized (object) {
                System.out.println(String.format("name=%s,wait", name));
                try {
                    object.wait();
                    int end = new Random().nextInt(1000);
                    System.out.println(String.format("name=%s,do %d ms", name,end));
                    Thread.currentThread().sleep(end);
                    System.out.println(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public String toString() {
            return "Player{" +
                "name='" + name + '\'' +
                '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Player playerA = new Player("A");
        Player playerB = new Player("B");
        Player playerC = new Player("C");
        playerA.start();
        playerB.start();
        playerC.start();
        //主线程需要先sleep，否则容易造成子线程还未到wait，就先notify
        Thread.currentThread().sleep(1000);
        synchronized (object) {
            object.notifyAll();
        }
    }
}
