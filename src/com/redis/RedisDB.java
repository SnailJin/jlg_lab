package com.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDB {
	private static JedisPool pool =null;
	
	public JedisPool getPool(){
		if(this.pool == null){
			JedisPoolConfig config=new JedisPoolConfig();
			config.setMaxActive(100);
			config.setMaxIdle(RedisConfig.MAX_IDLE);
			
		}
		return this.pool;
	}
}
