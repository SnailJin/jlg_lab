package redis;

import java.util.ArrayList;
import java.util.UUID;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class test {
	public static void main(String[] args) {
		ArrayList<String> urlList=new ArrayList<String>(){};
	      urlList.add("D:/tmp/Penguins.jpg");
	      urlList.add("D:/tmp/Tulips.jpg");
	      System.out.println(urlList.get(0));
	}

}
