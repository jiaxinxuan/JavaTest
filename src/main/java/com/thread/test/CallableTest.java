package com.thread.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试有返回值的线程
 * @author 贾新轩
 * 2017年8月23日  下午3:26:12
 * 说明:线程带有返回值的要实现Callable接口而不能实现Runable接口
 */
public class CallableTest implements Callable{

	private String oid; 

	CallableTest(String oid) { 
		this.oid = oid; 
    } 

    @Override 
	public Object call() throws Exception { 
    	return oid+"任务返回的内容"; 
	} 
	public static void main(String[] args) throws Exception {
		//创建带有返回值的线程池
		ExecutorService  pool=Executors.newFixedThreadPool(2);
		//创建实现了Callable接口的对象
		CallableTest callable1=new CallableTest("A");
		CallableTest callable2=new CallableTest("B");
		Future<String> future1=pool.submit(callable1);
		Future<String> future2=pool.submit(callable2);
		System.out.println(future1.get().toString());
		System.out.println(future2.get().toString());
		pool.shutdown();
	}
}
