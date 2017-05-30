package com.thread;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
import java.util.concurrent.ScheduledExecutorService;  
  
public class Ch09_Executor {  
	static int COUNT=0;
	final static int THREAD_COUNT = 10;
	
   private static void run(ExecutorService threadPool,final List<Integer> list) {  
    for(int i = 1; i < 5; i++) {    
            final int taskID = i;    
            threadPool.execute(new Runnable() {    
                @Override  
        public void run() {  
                	List<Integer> zxc=getList(list);
                	System.out.println(zxc.size()+"第"+taskID+"次");
                	for (Integer integer : zxc) {
						System.out.println(integer+"------------");
					}
                    for(int i = 1; i < 5; i++) {    
                        try {    
                            Thread.sleep(20);// 为了测试出效果，让每次任务执行都需要一定时间    
                        } catch (InterruptedException e) {    
                            e.printStackTrace();    
                        }    
                        System.out.println("第" + taskID + "次任务的第" + i + "次执行");    
                    }    
                }    
            });    
        }    
        threadPool.shutdown();// 任务执行完毕，关闭线程池    
   }  
      
   private static synchronized  List<Integer> getList(List<Integer> list){
		if(COUNT+10>list.size())
			return	list.subList(COUNT, list.size()-1);
		else{
			List<Integer> list1=list.subList(COUNT, 10);
			COUNT=COUNT+10;
			return list1;
		}
	}
   
    public static void main(String[] args) {  
        // 创建可以容纳3个线程的线程池  
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);  
          
        // 线程池的大小会根据执行的任务数动态分配  
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();  
          
            // 创建单个线程的线程池，如果当前线程在执行任务时突然中断，则会创建一个新的线程替代它继续执行任务    
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();  
          
        // 效果类似于Timer定时器  
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);  
        List<Integer> list=new ArrayList<>();
		for(int i=0;i<=100;i++){
			list.add(i);
		}
        run(fixedThreadPool,list);  
//      run(cachedThreadPool);  
//      run(singleThreadPool);  
//      run(scheduledThreadPool);  
    }  
  
} 