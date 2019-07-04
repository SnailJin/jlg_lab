package com.jin.java;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

	
public class TimerTest {

	public static void main(String[] args) {
		Timer timer = new Timer();
		Task task = new Task();
		timer.schedule(task, 0, 100);
//		while (true) {
//			timer.
//			
//		}
	}
}


class Task extends TimerTask{

	@Override
	public void run() {
		System.out.println("task is running! thread:"+Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("task is end");
		System.out.println("task is now :"+new Date());
		System.out.println("task is scheduledExecutionTime :"+ new Date(this.scheduledExecutionTime()));
	}
	
}