package com.test.entity;

public class Student extends User{

	private String age;
	public static void main(String[] args) {
		Student stu=new Student();
		stu.defaultTest();
		stu.protectedTest();
		User user=new User();
		user.defaultTest();
		user.protectedTest();
	}
}
