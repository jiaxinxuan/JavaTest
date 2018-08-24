package com.lambda.test;

import org.apache.commons.lang.math.RandomUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author 贾新轩
 * @time 17-12-14
 */
public class LambdaTest {

    public static void main(String[] args) {

        //匿名函数run()
        Runnable runnable= () -> {
            System.out.println("234567");
        };
        runnable.run();
        //供给型接口,提供随机字符串
        Supplier<String> supplier=() -> {
            Integer i=1+1;
            return UUID.randomUUID().toString().replaceAll("-","");
        };
        //函数型接口，可以定义入参和返回值类型，并且定义方法体
        Function<Integer,List> function=(w) ->{
            List list=new ArrayList<String>();
            for (int i=0;i<w;i++){
                list.add(Optional.ofNullable(supplier.get()).orElse(""));
            }
            return list;
        };
        //消费型接口
        Consumer<List> consumer=(jia)->{
            jia=function.apply(6);
            jia.forEach((li)->{
                System.out.println(li);
            });
        };
        //执行方法
        consumer.accept(new ArrayList<String>());

    }


}
