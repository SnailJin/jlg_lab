package com.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * 
 * @author jinliguang
 *redis 数据库操作类
 */
public class RedisDB {
	private static JedisPool pool =null;
	private RedisDB(){
		
	}
	/**
	 * 获取线程池
	 * @return
	 */
	public static JedisPool getPool(){
		if(RedisDB.pool == null){
			JedisPoolConfig config=new JedisPoolConfig();
			config.setMaxWaitMillis(RedisConfig.MAX_ACTIVE);
			config.setMaxIdle(RedisConfig.MAX_IDLE);
			config.setMaxWaitMillis(RedisConfig.MAXWAIT);
			config.setMaxTotal(RedisConfig.MAXTOTAL);
			//创建线程池
			RedisDB.pool =  new  JedisPool(config,RedisConfig.HOST,RedisConfig.PORT,
					RedisConfig.TIMEOUT, RedisConfig.PASSWRD);
		}
		return RedisDB.pool;
	}
	
	public void closeConnection(Jedis jedis){
		if(jedis !=null){
			try{
				RedisDB.pool.returnBrokenResource(jedis);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String [] args){
		JedisPool pool = RedisDB.getPool();
		Jedis jedis = pool.getResource();  
		jedis.set("testjin", "snowolf");  
		System.out.print(jedis.get("testjin"));
		
	}
}
