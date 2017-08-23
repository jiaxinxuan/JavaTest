package com.thread.test;

import java.util.concurrent.CyclicBarrier;

/**
 * 障碍器练习
 * @author 贾新轩
 * 2017年8月23日  下午4:27:32
 */
public class CyclicBarrierTest extends Thread {
	
	public void run(){
		System.out.println("主任务开始执行!");
	}
	
	public static void main(String[] args) {
		//创建障碍器,并指定子线程数量和主线程
		CyclicBarrier cyclicBarrier=new CyclicBarrier(7,new CyclicBarrierTest());
		//所有子线程共有同一个障碍器的引用
		new Thread(new RunableTest3(cyclicBarrier,"a")).start();
		new Thread(new RunableTest3(cyclicBarrier,"b")).start();
		new Thread(new RunableTest3(cyclicBarrier,"c")).start();
		new Thread(new RunableTest3(cyclicBarrier,"d")).start();
		new Thread(new RunableTest3(cyclicBarrier,"e")).start();
		new Thread(new RunableTest3(cyclicBarrier,"f")).start();
		new Thread(new RunableTest3(cyclicBarrier,"g")).start();
		/**
		 * 当所有的子线程执行完毕,主线程才开始执行,
		 * 障碍器控制着这些线程的执行,保证着主子线程的执行顺序
		 */
		
	}
}
