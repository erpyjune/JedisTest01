package com.erpyjune;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by erpy on 16. 3. 5..
 */
public class JedisExpireTest {
    public static void main( String[] args ) {
        Jedis jedis = null;
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "www.kiwitomato.com");
        try {
            jedis = pool.getResource();

            jedis.set("foo", "bar");
            jedis.expire("foo", 10);

            jedis.set("foo1", "bar");
            jedis.expire("foo1", 15);

            while (true) {
                String result = jedis.get("foo");
                System.out.println("get foo ==> " + result);

                String result2 = jedis.get("foo1");
                System.out.println("get foo1 ==> " + result2);
                if (result2==null) {
                    break;
                }
                Thread.sleep(1000);
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
