package com.firnice.test.redis;

import redis.clients.jedis.Jedis;

/**
 * @author: liyl
 * @date: 2018/1/31 下午9:19
 * @since 1.0.0
 */
public class RedisJava {

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost",32768);
        System.out.println("连接成功");
        jedis.set("a","1");
        String a = jedis.get("a");
        System.out.println(a);
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
    }
}
