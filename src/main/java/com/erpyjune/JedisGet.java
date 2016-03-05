package com.erpyjune;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by erpy on 16. 3. 1..
 */
public class JedisGet {
    static public void main(String args[]) throws Exception {
        Jedis jedis = null;
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "www.kiwitomato.com");
        try {
            jedis = pool.getResource();
            String result = jedis.get("foo");
            System.out.println("get foo ==> " + result);

            Set<String> sose = jedis.zrange("sose", 0, -1);
            for (String data : sose) {
                System.out.println("result ==> " + data);
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        } finally {
            if (jedis!=null) jedis.close();
            pool.destroy();
        }
        System.out.println( "End!!" );
    }
}
