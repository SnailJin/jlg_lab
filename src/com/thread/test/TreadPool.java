package com.thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * @author jinliguang
 *
 */
public class TreadPool {
	public static void get_fix_thread_pool(){
		//创建一个可重用固定线程数的线程池  
		ExecutorService pool = Executors.newFixedThreadPool(2);
		//创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口  
		Thread t1 = new MyThread();  
		Thread t2 = new MyThread();  
		Thread t3 = new MyThread();  
		Thread t4 = new MyThread();  
		Thread t5 = new MyThread();  
		//将线程放入池中进行执行  
		pool.execute(t1);  
		pool.execute(t2);  
		pool.execute(t3);  
		pool.execute(t4);  
		pool.execute(t5);  
		//关闭线程池  
		pool.shutdown(); 
	}
	
	
	public static void get_single_thread_pool(){
		//创建一个可重用固定线程数的线程池  
		ExecutorService pool = Executors.newSingleThreadExecutor();   
		//创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口  
		Thread t1 = new MyThread();  
		Thread t2 = new MyThread();  
		Thread t3 = new MyThread();  
		Thread t4 = new MyThread();  
		Thread t5 = new MyThread();  
		//将线程放入池中进行执行  
		pool.execute(t1);  
		pool.execute(t2);  
		pool.execute(t3);  
		pool.execute(t4);  
		pool.execute(t5);  
		//关闭线程池  
		pool.shutdown(); 
	}
	
	public static void get_cache_thread_pool(){
		//创建一个可重用固定线程数的线程池  
		ExecutorService pool = Executors.newCachedThreadPool();   
		//创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口  
		Thread t1 = new MyThread();  
		Thread t2 = new MyThread();  
		Thread t3 = new MyThread();  
		Thread t4 = new MyThread();  
		Thread t5 = new MyThread();  
		//将线程放入池中进行执行  
		pool.execute(t1);  
		pool.execute(t2);  
		pool.execute(t3);  
		pool.execute(t4);  
		pool.execute(t5);  
		//关闭线程池  
		pool.shutdown(); 
	}
	public static class MyThread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			System.out.println(Thread.currentThread().getName()+"正在执行。。。");  
			try {
				this.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		
	}
	
	
	
	public static void main(String[] args) {
		get_cache_thread_pool();
	}
}
