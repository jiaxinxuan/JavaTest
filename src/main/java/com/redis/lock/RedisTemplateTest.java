package com.redis.lock;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 贾新轩
 * @time 17-12-15
 */
public class RedisTemplateTest {

    public static RedisTemplateTest RedisTemplateTest;

    private RedisTemplate redisTemplate;

    private RedisTemplateTest(){
        //以单机模式创建redis链接工厂
        RedisStandaloneConfiguration config=new RedisStandaloneConfiguration();
        config.setPassword(RedisPassword.of("123456"));
        RedisConnectionFactory redisConnectionFactory=new JedisConnectionFactory(config);

        //创建redis的spring封装类RedisTemplate
        redisTemplate=new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //必须执行初始化操作，否则不会设置序列化，就无法存取值
        redisTemplate.afterPropertiesSet();
    }

    public static RedisTemplateTest getInstance(){
        synchronized (RedisTemplateTest.class){
            if (RedisTemplateTest!=null){
                return RedisTemplateTest;
            }
            return RedisTemplateTest=new RedisTemplateTest();
        }
    }

    /**
     *对字符串的操作
     */
    public void setValue(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    public Object getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }
    public ValueOperations getValueOperations(){
        return redisTemplate.opsForValue();
    }
    /**
     * 获取列表操作对象
     */
    public ListOperations getListOperations(){
        return redisTemplate.opsForList();
    }

    /**
     * 获取集合操作对象
     * @return
     */
    public SetOperations getSetOperations(){
        return redisTemplate.opsForSet();
    }

    /**
     * 获取hash数据操作对象
     * @return
     */
    public HashOperations getHashOperations(){
        return redisTemplate.opsForHash();
    }

    /**
     * 获取有序数组的操作对象
     * @return
     */
    public ZSetOperations getZSetOperations(){
        return redisTemplate.opsForZSet();
    }
}
