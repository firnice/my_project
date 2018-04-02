package com.cornerstone.thread.interrupt;

/**
 * @author: liyl
 * @date: 2018/4/2 上午11:37
 * @since 1.0.0
 */
public class InterruptedTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("interrupted out");
                        break;
                    }
                    while (true) {
                        //这里换成interrupted 就会清空标示
                        //if (Thread.currentThread().interrupted()) {
                        if (Thread.currentThread().isInterrupted()) {
                            System.out.println("interrupted out!");
                            break;
                        }
                    }
                    doSomething();
                    try {
                        //sleep的时候会判断中断标示，如果之前有调用过interrupt，到这里直接抛异常
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted!!");
                        //抛出异常后会清空的清空真是个坑
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        };
        thread.start();

        Thread.sleep(20);
        thread.interrupt();
    }

    public static void doSomething() {
        long start = System.currentTimeMillis();
        System.out.println("start");
        while (true) {
            if (System.currentTimeMillis() - start > 1000  * 5) {
                System.out.println("out");

                break;
            }
        }
    }
}
