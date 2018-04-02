package com.cornerstone.thread.stop;

/**
 * @author: liyl
 * @date: 2018/4/2 上午10:45
 * @since 1.0.0
 */
public class StopThreadUnsafe {

    public static User u = new User();

    public static class WriteOnly extends Thread{
        @Override
        public void run() {
            while (true) {

                synchronized (u) {//锁着
                    long l = System.currentTimeMillis() / 1000;
                    u.setId(String.valueOf(l));
                    try {
                        Thread.sleep(150);//sleep是Thread的方法
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(l));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadOnly extends Thread{
        @Override
        public void run() {
            while (true) {
                synchronized (u) {
                    if(!u.getId().equals(u.getName())){
                        System.out.println(u);
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadOnly().start();
        while (true) {
            Thread t = new WriteOnly();
            t.start();
            Thread.sleep(200);
            t.stop();
        }
    }

    public static class User {
        private String id;
        private String name;

        public User() {
            this.id = "";
            this.name = "";
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "user{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
        }

    }
}
