package com.redis.testutil;

import java.util.concurrent.atomic.AtomicInteger;

import com.redis.RedisDB;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class clientTest {

	public static AtomicInteger count = new AtomicInteger(0);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i = 0;i<20 ;i++){
			new MyThread().start();
		}

	}

}
class MyThread extends Thread {
	JedisPool pool = RedisDB.getPool();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Jedis jedis = pool.getResource();
		while(true){
			jedis.publish("hello_jin", "jsifs"+clientTest.count.incrementAndGet());
			jedis.publish("you_li", "^_^)y");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}