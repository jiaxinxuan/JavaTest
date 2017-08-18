package com.thread.test;


/**
 * 
 * @author Administrator
 * 2017年8月18日  下午5:33:17
 */
public class RunableTest implements Runnable{
	private String name;
	
	public RunableTest(String name) {
		super();
		this.name = name;
	}

	public String getArray() {
		return name;
	}

	public void setArray(String name) {
		this.name = name;
	}

	/**
	 * 实现runable接口的run方法
	 * 2017年8月18日下午5:32:37
	 */
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.err.println(name+" "+i);
		}
	}
	public static void main(String[] args) {
		Thread t1=new Thread(new RunableTest("乌龟"));
		Thread t2=new Thread(new RunableTest("兔子"));
		t1.start();
		t2.start();
	}
}
