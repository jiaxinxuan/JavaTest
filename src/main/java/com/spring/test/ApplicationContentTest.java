package com.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author 贾新轩
 * @time 17-12-26
 */
public class ApplicationContentTest {

    public static void main(String[] args) {
        classPathXmlApplicationContextTest();
        fileSystemXmlApplicationContextTest();
    }

    /**
     * 按类路径加载配置文件（类路径）
     */
    private static void classPathXmlApplicationContextTest(){
        ApplicationContext classApp=new ClassPathXmlApplicationContext("spring-mail.xml");
        System.out.println(classApp.getApplicationName());
        System.out.println(classApp.getParent());
        System.out.println(classApp.getId());
        System.out.println(classApp.getDisplayName());
        Object obj=classApp.getBean("simpleMailMessage");
        System.out.println(obj.toString());

    }

    /**
     * 按指定路径加载配置文件（文件系统）
     */
    private static void fileSystemXmlApplicationContextTest() {
        ApplicationContext fileApp=new FileSystemXmlApplicationContext("/src/main/resources/spring-mail.xml");
        Object obj=fileApp.getBean("simpleMailMessage");
        System.out.println(obj.toString());
        System.out.println(fileApp.getApplicationName());
        System.out.println(fileApp.getParent());
        System.out.println(fileApp.getId());
        System.out.println(fileApp.getDisplayName());
    }
}
