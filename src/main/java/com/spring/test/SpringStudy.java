package com.spring.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringStudy {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:spring-mail.xml");
		ctx.getBeanFactory();
		System.out.println(ctx.getBean("mailSender"));
	}
}
