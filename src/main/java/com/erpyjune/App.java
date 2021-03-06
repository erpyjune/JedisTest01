package com.erpyjune;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Jedis jedis = null;
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "www.kiwitomato.com");
        try {
            jedis = pool.getResource();

            jedis.set("foo", "bar");
            jedis.expire("foo", 10);

            String foobar = jedis.get("foo");
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        } finally {
            if (jedis!=null) jedis.close();
            pool.destroy();
        }
        System.out.println( "End!!" );
    }
}
