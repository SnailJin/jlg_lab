package com.jin.thread.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 闭锁测试
 * 使用场景控制多线程同时并发
 * @author jin
 *
 */
public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		int size = 20 ;
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(size);
		ExecutorService fixdThreadPool = Executors.newFixedThreadPool(size);
		for(int i =0 ;i<size ; i++){
			fixdThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName()+" is ready");
						//阻塞线程
						startGate.await();
						try{
							System.out.println("is running");
						}finally {
							endGate.countDown();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
		}
		long start  = System.currentTimeMillis();
		Thread.sleep(1000);
		//countDown 会将统计次数减一 直到0的时候触发执行
		startGate.countDown();
		endGate.await();
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		
	}
}

