package com.jin.thread.test;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

	public static void main(String[] args) {
		int size = 20 ;
		 Task task = new Task();// 新建异步任务
	     FutureTask<Integer> future = new FutureTask<Integer>(task) {

			@Override
			protected void done() {
				 try {
	                    System.out.println("future.done():" + get());
	                    Thread.sleep(500);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                } catch (ExecutionException e) {
	                    e.printStackTrace();
	                } catch (CancellationException e) {
//	                    e.printStackTrace();
	                }
			}
	    	 
	     };
	     
	     // 创建线程池（使用了预定义的配置）
	        ExecutorService executor = Executors.newCachedThreadPool();
	        executor.execute(future);
	        long start = System.currentTimeMillis();
	        try {
	            Thread.sleep(100);
	        } catch (InterruptedException e1) {
	            e1.printStackTrace();
	        }
	        // 可以取消异步任务
	        // future.cancel(true);

	        try {
	        	System.out.println("watting ....");
	        	while (!future.isDone()) {
	        		System.out.println("watting ....while ");
	        		if(System.currentTimeMillis() - start >1000){
	        			future.cancel(false);
	        			System.out.println("is cancel:"+future.isCancelled());
	        		}
				}
	            // 阻塞，等待异步任务执行完毕-获取异步任务的返回值
	            System.out.println("future.get():" + future.get());
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } catch (ExecutionException e) {
	            e.printStackTrace();
	        }
	}
	
	// 异步任务
    static class Task implements Callable<Integer> {
        // 返回异步任务的执行结果
        @Override
        public Integer call() throws Exception {
            int i = 0;
            for (; i < 10; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + "_"
                            + i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return i;
        }
    }
}
