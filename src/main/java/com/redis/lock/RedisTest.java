package com.redis.lock;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author 贾新轩
 * @time 17-12-13
 */
@Component
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public static void main(String[] args) {
      RedisHelper redisHelper=RedisHelper.newInstance("localhost",6379,"123456",10,10,1000L);
      redisHelper.setNx("test","jiaxinxuan");
      System.out.println(redisHelper.get("test"));
    }
    private void redisTemplateTest(){
//        redisTemplate
    }
}
