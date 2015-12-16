package com.redis;

public class RedisConfig {
	public static final String HOST ="127.0.0.1";
	public static final int PORT = 6379;
	public static final int TIMEOUT = 10000;
	public static final String PASSWRD =null;
	//public static final String DATABASE ="test";
	
	//如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。 
	public static final int MAX_ACTIVE =100;
	//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
	public static final int MAX_IDLE =5;
	//表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException； 
	public static final int MAXWAIT = 1000 * 100;
	//最大连接数
	public static final int MAXTOTAL =10;
	
	
}
