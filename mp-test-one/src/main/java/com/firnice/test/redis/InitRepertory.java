//package com.firnice.test.redis;
//
//import com.alibaba.fastjson.JSON;
//import redis.clients.jedis.Jedis;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @author: liyl
// * @date: 2018/1/31 下午9:19
// * @since 1.0.0
// */
//public class InitRepertory {
//
//
//    private static final String key = "ChildrensDay_";
//
//
//    public static void main(String[] args) throws InterruptedException {
////
//
//
//        Jedis jedis = new Jedis("10.35.46.21", 6379);
//        readFileByLines("/Users/yiruike/Downloads/test.txt", jedis);
//
//        System.out.println(key + 1);
//        System.out.println("main end " + jedis.llen(key + 1));
//        jedis.expire(key + 1,60*60*24);
////        Thread.sleep(2000);
//
////        System.out.println("main end " + jedis.llen(key + 1));
//
//
//
//    }
//
//    /**
//     * 以行为单位读取文件，常用于读面向行的格式化文件
//     */
//    public static void readFileByLines(String fileName, Jedis jedis) {
//
//        File file = new File(fileName);
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new FileReader(file));
//            String tempString = null;
//            int line = 1;
//            // 一次读入一行，直到读入null为文件结束
//            while ((tempString = reader.readLine()) != null) {
//                // 显示行号
//                line++;
//                jedis.rpush(key + 1, JSON.toJSONString(tempString));
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
//    }
//
//
//}
