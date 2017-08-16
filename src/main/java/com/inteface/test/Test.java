package com.inteface.test;

public interface Test {
	default void hehe(){
		System.out.println("这个是默认方法!");
	}
	static void hehee(){
		System.out.println("这里是静太方法！");
	}
}
