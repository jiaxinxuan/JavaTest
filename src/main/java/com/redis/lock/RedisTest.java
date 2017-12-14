package com.redis.lock;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;


/**
 * @author 贾新轩
 * @time 17-12-13
 */
@Component
public class RedisTest {


    public static void main(String[] args) {
        redisStandaloneTest();
        redisSentinelTest();
        redisClusterTest();
    }

    /**
     * redis 单机模式测试
     */
    private static void redisStandaloneTest(){
        //以单机模式创建redis链接工厂
        RedisConnectionFactory redisConnectionFactory=new JedisConnectionFactory(new RedisStandaloneConfiguration());
        //创建redis的spring封装类
        RedisTemplate redisTemplate=new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //必须执行初始化操作，否则不会设置序列化，就无法存取值
        redisTemplate.afterPropertiesSet();
        //字符串方式存取值
        redisTemplate.opsForValue().set("jia","xinxuain");
        System.out.println(redisTemplate.opsForValue().get("jia"));
    }

    /**
     * redis 哨兵模式测试
     */
    private static void redisSentinelTest(){

    }

    /**
     * redis 集群模式测试
     */
    private static void redisClusterTest(){

    }
}
