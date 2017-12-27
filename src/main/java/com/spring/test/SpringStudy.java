package com.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringStudy {
	
	public static void main(String[] args) {
        ApplicationContext classApplicationContext=new ClassPathXmlApplicationContext("classpath:spring-mail.xml");
        ApplicationContext fileApplicationContext=new FileSystemXmlApplicationContext();
        classApplicationContext.getApplicationName();
		System.out.println(classApplicationContext.getBean("mailSender"));
        classApplicationContext.getApplicationName();
	}
}
