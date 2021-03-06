package com.thread.test;

/**
 * 自定义线程池接口
 * @author 贾新轩
 * 2017年8月23日  下午2:29:23
 * @param <Job>
 */
public interface ThreadPool<Job extends Runnable>{
	   //执行一个任务(Job),这个Job必须实现Runnable
	   void execute(Job job);
	  //关闭线程池
	  void shutdown();
	  //增加工作者线程，即用来执行任务的线程
	  void addWorkers(int num);
	  //减少工作者线程
	  void removeWorker(int num);
	  //获取正在等待执行的任务数量
	  int getJobSize();
	}
