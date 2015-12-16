package com.redis.testutil;

import com.redis.MyListen;
import com.redis.RedisDB;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {
	public static void main(String[] args){
		JedisPool pool = RedisDB.getPool();
		Jedis jedis = pool.getResource();  
		MyListen listen = new MyListen();
		System.out.println("订阅端启动ing");
		jedis.psubscribe(listen,new String[]{"hello_*"});
		System.out.println("订阅端启动成功");
	}
}
