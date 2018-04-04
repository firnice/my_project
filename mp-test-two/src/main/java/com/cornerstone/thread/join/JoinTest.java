package com.cornerstone.thread.join;

/**
 * @author: liyl
 * @date: 2018/4/3 上午11:03
 * @since 1.0.0
 */
public class JoinTest {

    public static int i = 0;

    public static class Run extends Thread {
        @Override
        public void run() {
            while (true) {
                i++;
                if (i > 100000) { break; }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Run run = new Run();
        run.start();
        run.join();
        System.out.println(i);
    }
}
