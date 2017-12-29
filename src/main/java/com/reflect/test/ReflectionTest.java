package com.reflect.test;

import com.test.entity.User;
import org.junit.Test;

/**
 *
 * @author 贾新轩
 * @time 17-12-28
 */
public class ReflectionTest {

    @Test
    public void ClassTest(){
        User user1=new User();
        User user2=new User();
        System.out.println(user1.getClass().hashCode()==user2.getClass().hashCode());
        Class userClasss=user1.getClass();
        ClassLoader classLoader=userClasss.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
        System.out.println(userClasss.getName());
        System.out.println(userClasss.getMethods());
    }
}
