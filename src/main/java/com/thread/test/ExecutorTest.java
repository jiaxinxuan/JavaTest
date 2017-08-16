package com.thread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {
	
	
	static int COUNT=0;
	final static int THREAD_COUNT = 10; 
	private static CountDownLatch threadCompletedCounter = new CountDownLatch(THREAD_COUNT);  
	
	public static void main(String[] args) {
		List<Integer> list=new ArrayList<>();
		for(int i=1;i<=100;i++){
			list.add(i);
		}
		
		run(list);
	}
	
	public static void run(final List<Integer> list){
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);  
        for (int i = 1; i <= THREAD_COUNT; i++) { 
        	final int task=i;
            executor.submit(new Runnable() {  
                public void run() { 
                	List<Integer> zxc=getList(list,task);
                	for (Integer integer : zxc) {
						System.out.println(integer+"------------"+"第"+task+"次");
					}
                }  
            });  
        }  
       executor.shutdown();  
	}
	
	private  synchronized static  List<Integer> getList(List<Integer> list,int taskid){
		if(COUNT+10>list.size()){
			return	list.subList(COUNT, list.size()-1);
		}
		else{
			List<Integer> list1=list.subList(COUNT, COUNT+10);
			COUNT=COUNT+10;
			return list1;
		}
	}
	
}
