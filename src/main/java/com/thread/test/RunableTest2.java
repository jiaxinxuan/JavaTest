package com.thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunableTest2 {

	public void ThreadTimeTest(){
		Long start=System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			Thread thread=new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(100l);
//						System.out.println("---");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			thread.start();
			System.out.println(thread.getName());
		}
		Long stop=System.currentTimeMillis();
		System.out.println(stop-start);
	}
	public static void main(String[] args) {
		ExecutorService tpe=Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			final int index=i;
		tpe.execute(new Runnable() {
			public void run() {
                 try {
                	System.out.println("ThreadName:" + Thread.currentThread().getName()+","+index);  
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}  
			}
		});
		}
		tpe.shutdown();
	}
}
