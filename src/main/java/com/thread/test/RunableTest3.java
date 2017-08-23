package com.thread.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class RunableTest3 implements Runnable{

		//定义障碍器
		private CyclicBarrier cyclicBarrier;
		private String name;
		
		public CyclicBarrier getCyclicBarrier() {
			return cyclicBarrier;
		}
		public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public RunableTest3(CyclicBarrier cyclicBarrier, String name) {
			super();
			this.cyclicBarrier = cyclicBarrier;
			this.name = name;
		}
		@Override
		public void run() {
			System.out.println("线程"+name+"开始执行");
			try {
				Thread.sleep(100);
				cyclicBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}