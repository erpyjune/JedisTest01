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
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "www.dkiwitomato.com");
        try {
            Jedis jedis = pool.getResource();

            jedis.set("foo", "bar");
            String foobar = jedis.get("foo");
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        pool.destroy();
        System.out.println( "End!!" );
    }
}
