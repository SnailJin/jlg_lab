package com.jin.thread.delayQueue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UsageOfDelayQueue {

	 private static final int STUDENT_NUM = 10;

	 
	 public static void main(String[] args) throws InterruptedException {
		 long start = System.currentTimeMillis();

	        Random r = new Random();
	        DelayQueue<Student> students = new DelayQueue<Student>();
	        // to run Student
	        ExecutorService service = Executors.newFixedThreadPool(STUDENT_NUM);
//	        ExecutorService service = Executors.newSingleThreadExecutor();

	        for (int i = 0; i < STUDENT_NUM; i++) {
	            students.put(new Student("Student" + i, 2000 + r.nextInt(3000)));
	        }

	        // start take object from delayQueue
	        while (!students.isEmpty()) {
	            // take a Student and execute since it's Runnable
	            try {
					service.execute(students.take());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        service.shutdown();

	        service.awaitTermination(1, TimeUnit.HOURS);
	        long stop = System.currentTimeMillis();
	        System.out.println("Total time used: " + (stop - start) + "ms.");
	}
	 /**
     * DelayQueue的元素必须实现{@link Delayed}接口（同时也实现了{@link Comparable}）接口
     * {@link Delayed#getDelay(TimeUnit)}决定了元素是否可以被取出
     * {@link Comparable#compareTo(Object)}决定了元素在队列中的排列顺序，当很多元素都可以被取出的时候，就按这个顺序进行
     */
    private static class Student implements Runnable, Delayed {
    	 private String name;
    	// time to use, assigned by Random
         private long costTime;
         // time to finish
         private long finishedTime;
         
         Student(String name, long costTime) {
             this.name = name;
             this.costTime = costTime;
             finishedTime = costTime + System.currentTimeMillis();
         }


		@Override
		public int compareTo(Delayed arg0) {
			Student other = (Student) arg0;
			return costTime >= other.costTime ? 1 : -1;
		}

		@Override
		public long getDelay(TimeUnit arg0) {
		  return (finishedTime - System.currentTimeMillis());
		}

		@Override
		public void run() {
			System.out.println(name + " finished Time : " + finishedTime + " now:"+System.currentTimeMillis());
			 System.out.println(name + " finished. Time used: " + costTime + "ms");

	            synchronized (this) {
	                try {
	                    wait(1000);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }

	            System.out.println(name + " done~");
			
		}
    	
    }
}
