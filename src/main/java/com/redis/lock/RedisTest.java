package com.redis.lock;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    private static void redisStandaloneTest() {
        RedisTemplateTest redisTemplateTest=RedisTemplateTest.getInstance();
        //字符串
        redisTemplateTest.setValue("jia","jiaxinxuan");
        System.out.println(redisTemplateTest.getValue("jia"));
        ValueOperations valueOperations=redisTemplateTest.getValueOperations();
        //如果key不存在，则设置一个新值，存在的话就不做任何改变。
        valueOperations.setIfAbsent("jia","xinxuanjia@caixin.com");
        //列表
        List<Integer> list=new ArrayList(16);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        ListOperations listOperations=redisTemplateTest.getListOperations();
        listOperations.leftPushAll("test",list);
        listOperations.leftPush("test",5);
        listOperations.leftPush("test",6);
        listOperations.rightPush("test",8);
        listOperations.rightPush("test",9);
        System.out.println(listOperations.range("test",0,100));
        //集合
        Set<Object> set=new HashSet();
        set.add(list);
        set.add("jiaxufjei");
        SetOperations setOperations=redisTemplateTest.getSetOperations();
        setOperations.add("set",set);
        System.out.println(setOperations.pop("set"));


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
