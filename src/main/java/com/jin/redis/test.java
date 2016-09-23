package com.jin.redis;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class test {
	public static void main(String[] args) throws InterruptedException {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		JedisPool pool = RedisDB.getPool();
		Jedis jedis = pool.getResource();
		// Long getTimeInMillis=Calendar.getInstance().getTimeInMillis();
		// jedis.zadd("test1", getTimeInMillis,"232323233");
		// System.out.println(jedis.zrange("TOP12", 0, -1).toString());
		// jedis.zrem("TOP10", "13");
		// jedis.zrem("TOP10", "12");
		// Double result = jedis.zscore("test1", "11");
		// result = (result == null)?0.0 :result;
		// Calendar calendar =Calendar.getInstance();
		// calendar.add(calendar.DATE, 6);
		// long time = calendar.getTimeInMillis()-System.currentTimeMillis();
		// System.out.println(time/(1000*3600*24));
		// Calendar cale=Calendar.getInstance();
		// cale.setTimeInMillis(getTimeInMillis);
		// System.out.println(sdf.format(cale.getTime()));
		// cale.setTimeInMillis(Math.round(jedis.zscore("test1", "232323233")));
		// System.out.println(sdf.format(cale.getTime()));
		// String [] s={"2","23"};
		// test.agrgs("1");
		long startTime = System.currentTimeMillis();
		System.out.println(startTime);
		System.out.println(jedis.zrange("C_U_L",0,-1));
//		for(int i =0 ;i<100000000;i++){
//			jedis.zadd("C_U_L",i, "490077852#490077852#"+i);
//		}
//		Set<String> list = jedis.zrangeByScore("C_U_L",  0,1000000);
//		jedis.zremrangeByScore("C_U_L",  0, System.currentTimeMillis());
//		System.out.println(jedis.smembers("P_N_O_I_S"));
//		long time =System.currentTimeMillis();
//		System.out.println(jedis.zrangeByScore("C_U_L", time-1000*60*5, time));
//		System.out.println(jedis.hgetAll("S_E:mopin1"));
//		jedis.del("C_U_L");
//		System.out.println(jedis.del("S_E:"));
		//jedis.zadd("F_N_L",  System.currentTimeMillis(), "10002#0");
	}

	public static void agrgs(String... strings) {
		for (int i = 0; i < strings.length; i++) {
			System.out.println(strings[i]);
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
		System.out.print("添加key：" + this.getName());
		jedis.set(this.getName(), this.getName() + "val");
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(this.getName() + "完成");
	}

}
