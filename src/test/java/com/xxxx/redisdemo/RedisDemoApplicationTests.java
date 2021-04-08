package com.xxxx.redisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SpringBootTest
class RedisDemoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    public void initConn01(){
        Jedis jedis = new Jedis("192.168.188.100", 6379);
        jedis.auth("123456");
        jedis.select(1);
        String result = jedis.ping();
        System.out.println(result);
        jedis.set("username","lisi");
        String username = jedis.get("username");
        System.out.println(username);
        if (null != jedis)
            jedis.close();
    }
    @Test
    public void initConn02(){
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(),"192.168.188.100",6379,10000,"123456");
        Jedis jedis = jedisPool.getResource();
        jedis.select(2);
        String result = jedis.ping();
        System.out.println(result);

        jedis.set("username","zhangsan");
        String username = jedis.get("username");
        System.out.println(username);

        if (jedis != null)
            jedis.close();
    }
}
