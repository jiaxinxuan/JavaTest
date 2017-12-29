package com.spring.test;

import com.mail.test.MailUtil;
import org.junit.Test;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author 贾新轩
 * @time 17-12-26
 */
public class ApplicationContentTest {

    /**
     * 按类路径加载配置文件（类路径）
     */
    @Test
    public void classPathXmlApplicationContextTest(){
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
    @Test
    public  void fileSystemXmlApplicationContextTest() {
        ApplicationContext fileApp=new FileSystemXmlApplicationContext("/src/main/resources/spring-mail.xml");
        Object obj=fileApp.getBean("simpleMailMessage");
        System.out.println(obj.toString());
        System.out.println(fileApp.getApplicationName());
        System.out.println(fileApp.getParent());
        System.out.println(fileApp.getId());
        System.out.println(fileApp.getDisplayName());
    }

    @Test
    public void beanFactoryTest(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-mail.xml");
        MailUtil mailUtil1 = (MailUtil) applicationContext.getBean("mailUtil");
        MailUtil mailUtil2 = (MailUtil) applicationContext.getBean("mailUtil");
        System.out.println(mailUtil1.hashCode()==mailUtil2.hashCode());
        mailUtil2.send("552387367@qq.com","ioc容器测试","ioc容器测试");
        DefaultListableBeanFactory defaultListableBeanFactory=new DefaultListableBeanFactory();
        defaultListableBeanFactory.toString();

    }

    @Test
    public void listableBeanFactory(){
        ListableBeanFactory applicationContext=new ClassPathXmlApplicationContext("spring-mail.xml");
        System.out.println(applicationContext.getBeanDefinitionCount());
        for (String name:applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }
}
