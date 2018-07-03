package com.reflect.test;

import com.test.entity.User;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author 贾新轩
 * @time 17-12-28
 */
public class ReflectionTest {

    @Test
    public void ClassTest() throws IllegalAccessException {
        User user1=new User();
        User user2=new User();
        System.out.println(user1.getClass().hashCode()==user2.getClass().hashCode());
        Class userClasss=user1.getClass();
        user1.setName("jiaxinxuan");
        ClassLoader classLoader=userClasss.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
        System.out.println(userClasss.getName());
        for (Method method:
             userClasss.getMethods()) {
            System.out.println(method.getName());
            System.out.println(method.getDefaultValue());
        }

        //
        for (Field field:
             userClasss.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field.getType());
            System.out.println(field.getName());
            System.out.println(field.get(user1));
        }
    }

    /**
     * 反射获取对象单个属性的值
     * @param obj
     * @param key
     * @return
     * @throws IllegalAccessException
     */
    public static Object getValueByKey(Object obj, String key) throws IllegalAccessException {

        Class cal=obj.getClass();
        for (Field field:
             cal.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field.getName());
            if (field.getName().endsWith(key)){
                System.out.println(field.get(obj));
                return field.get(obj);
            }

        }
        return "";
    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("Jiaxinxuan");
        try {
            getValueByKey(user,"name");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
