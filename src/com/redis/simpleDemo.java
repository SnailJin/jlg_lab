package com.redis;

import redis.clients.jedis.Jedis;

public class simpleDemo {
	public static void main(String[] args) {
		// 1.初始化
		Jedis jedis = new Jedis("localhost");
		int num =101;
		jedis.set("num", num+"");
		jedis.incr("num");
		System.out.println(jedis.get("num"));
	}
}
