package com.thread.test;
/**
 * 实现多线程runable方法
 * @author Administrator
 *
 */
public class Thread1 {
	
	public static  Integer num=0;
	public static void main(String[] args) {
		
		Thread the1=new Thread(new Runnable() {
			public void run() {
				while(num<=100){
					System.out.println(add());
				}
			}
		});
		the1.start();
		Thread the2=new Thread(new Runnable() {
			public void run() {
				while(num<=100){
					System.out.println(add());
				}
			}
		});
		the2.start();
	}
	
	private static int add(){
		return num++;
	}
}
