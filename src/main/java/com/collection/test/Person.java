package com.collection.test;

import com.test.entity.User;

public class Person extends User{
	public static void main(String[] args) {
		Person per=new Person();
		per.protectedTest();//不同包下,只有继承了父类才能在子类中访问父类的protected方法,而default方法则永远访问不到,不同包下,永远不能访问protected和
		User user=new User();
	}
}
