package com.jin.redis.testutil;


import com.jin.redis.MyListen;
import com.jin.redis.RedisDB;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest2 {
	public static void main(String[] args){
		JedisPool pool = RedisDB.getPool();
		Jedis jedis = pool.getResource();  
		MyListen listen = new MyListen();
		System.out.println("订阅端启动ing");
		jedis.psubscribe(listen,new String[]{"you_*"});
		System.out.println("订阅端启动成功");
	}
}
