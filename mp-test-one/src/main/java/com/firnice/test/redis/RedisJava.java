//package com.firnice.test.redis;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @author: liyl
// * @date: 2018/1/31 下午9:19
// * @since 1.0.0
// */
//public class RedisJava {
//
//
//    public static final String JACKPOT = "jackpot";
//
////    static RedisDaoImpl redisDao = new RedisDaoImpl("localhost", 32768);
//
//    static ThreadLocal<Jedis> jedisThreadLocal = new ThreadLocal<>();
//
//
////    public static class RedisDaoImpl {
////
////
////        private JedisPool jedisPool;
////
////        public Jedis getJedis() {
////            Jedis jedis = null;
////            try{
////                // 从连接池获取一个Jedis实例
////                jedis = jedisPool.getResource();
////            }finally{
////                if(null != jedis)
////                    jedis.close(); // 释放资源还给连接池
////            }
////            return jedis;
////        }
////
////        /**
////         * 修改之后的构造方法
////         *
////         * @param ip   访问的ip
////         * @param port 访问的端口
////         */
////        public RedisDaoImpl(String ip, int port) {
////            JedisPoolConfig config = new JedisPoolConfig();
////            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
////            config.setMaxIdle(10);
////            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
////            config.setMaxWaitMillis(1000 * 1000);
////            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
////            config.setTestOnBorrow(true);
////            jedisPool = new JedisPool(config, ip, port);
////        }
////    }
//
//    public static void main(String[] args) {
//        //连接本地的 Redis 服务
//        System.out.println("连接成功");
////        redisDao.getJedis().set("a", "1");
////        String a = redisDao.getJedis().get("a");
////        System.out.println(a);
//
//        Jedis jedis = new Jedis("localhost", 32768);
//        jedis.llen(JACKPOT);
//
//        for (int i = 0; i < 100; i++) {
//            jedis.rpush(JACKPOT, "V" + i);
//        }
//        System.out.println("end ");
////        while (jedis.llen(JACKPOT) != 0) {
////            String lpop = jedis.lpop(JACKPOT);
////            System.out.println(" |"+lpop);
////        }
//
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 10; i++) {
//            MyRunable myRunable = new MyRunable(i);
//            executorService.execute(myRunable);
//        }
//
//        System.out.println("main end ");
//
//        //查看服务是否运行
////        System.out.println("服务正在运行: " + jedis.ping());
//    }
//
//
//    public static class MyRunable implements Runnable {
//
//        private int id;
//
//        public MyRunable(int id) {
//            this.id = id;
//        }
//
//        @Override
//        public void run() {
//            System.out.println("in id:" + id);
//            try {
//                Jedis jedis = jedisThreadLocal.get();
//                if(jedis == null){
//                    jedis = new Jedis("localhost", 32768);
//                }
//                if (jedis.exists(JACKPOT)) {
//
//                    while (jedis.llen(JACKPOT) != 0) {
//                        String lpop = jedis.lpop(JACKPOT);
//                        System.out.println("id:" + id + " |" + lpop);
//                    }
//                } else {
//                    System.out.println("no key");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
