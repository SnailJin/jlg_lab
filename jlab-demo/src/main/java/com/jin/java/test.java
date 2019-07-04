package com.jin.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;

import redis.clients.util.SafeEncoder;
import sun.misc.BASE64Decoder;


public class test {
	private ReentrantLock lock = new ReentrantLock();
	private volatile static test t;
	private String username;
	
	public void  test1() throws SocketTimeoutException {
	}
	public void  test2() throws IOException{
	}
	
	
	public test(String username){
		this.setUsername(username);
	}
	public test(){
		this("jin");
	}
	
	public static test getInstance() {
		if(t == null){
			t = new test();
		}
		return t;
	}
	public void task1() throws InterruptedException  {
		if(lock.isLocked()){
			System.out.println("1被锁住了");
		}
		lock.lock();
		System.out.println("task1 得到锁!");
		Thread.sleep(2000);
		lock.unlock();
		System.out.println("task1 释放锁!");
	}
	
	public void task2() throws InterruptedException {
		if(lock.isLocked()){
			System.out.println("2被锁住了");
		}
		lock.lock();
		System.out.println("task2得到锁!");
		Thread.sleep(2000);
		lock.unlock();
		System.out.println("task2 释放锁!");
	}
	
	public static enum Command {
		PING, SET, GET, QUIT, EXISTS, DEL, TYPE, FLUSHDB, KEYS, RANDOMKEY, RENAME, RENAMENX, RENAMEX, DBSIZE, EXPIRE, EXPIREAT, TTL, SELECT, MOVE, FLUSHALL, GETSET, MGET, SETNX, SETEX, MSET, MSETNX, DECRBY, DECR, INCRBY, INCR, APPEND, SUBSTR, HSET, HGET, HSETNX, HMSET, HMGET, HINCRBY, HEXISTS, HDEL, HLEN, HKEYS, HVALS, HGETALL, RPUSH, LPUSH, LLEN, LRANGE, LTRIM, LINDEX, LSET, LREM, LPOP, RPOP, RPOPLPUSH, SADD, SMEMBERS, SREM, SPOP, SMOVE, SCARD, SISMEMBER, SINTER, SINTERSTORE, SUNION, SUNIONSTORE, SDIFF, SDIFFSTORE, SRANDMEMBER, ZADD, ZRANGE, ZREM, ZINCRBY, ZRANK, ZREVRANK, ZREVRANGE, ZCARD, ZSCORE, MULTI, DISCARD, EXEC, WATCH, UNWATCH, SORT, BLPOP, BRPOP, AUTH, SUBSCRIBE, PUBLISH, UNSUBSCRIBE, PSUBSCRIBE, PUNSUBSCRIBE, PUBSUB, ZCOUNT, ZRANGEBYSCORE, ZREVRANGEBYSCORE, ZREMRANGEBYRANK, ZREMRANGEBYSCORE, ZUNIONSTORE, ZINTERSTORE, SAVE, BGSAVE, BGREWRITEAOF, LASTSAVE, SHUTDOWN, INFO, MONITOR, SLAVEOF, CONFIG, STRLEN, SYNC, LPUSHX, PERSIST, RPUSHX, ECHO, LINSERT, DEBUG, BRPOPLPUSH, SETBIT, GETBIT, SETRANGE, GETRANGE, EVAL, EVALSHA, SCRIPT, SLOWLOG, OBJECT, BITCOUNT, BITOP, SENTINEL, DUMP, RESTORE, PEXPIRE, PEXPIREAT, PTTL, INCRBYFLOAT, PSETEX, CLIENT, TIME, MIGRATE, HINCRBYFLOAT, SCAN, HSCAN, SSCAN, ZSCAN, WAIT, CLUSTER, ASKING;

		public final byte[] raw;

		Command() {
		    raw = SafeEncoder.encode(this.name());
		}
	    }
	class TestClass{
		
	}
	public static void main(String[] args) throws IOException  {
//		byte[] responseData = com.mzlion.easyokhttp.HttpClient.get("https://itunes.apple.com/WebObjects/MZStore.woa/wa/userReviewsRow")
//				.header("X-Apple-Store-Front", "143465-19,29")
//				.header("User-Agent", "AppStore/2.0 iOS/10.2 model/iPhone8,1 hwp/s8000 build/14B150 (6; dt:106)")
//				.header("Accept-Language", "zh-Hans-CN")
//				.header("Accept-Encoding", "gzip")
//				.header("Connection", "keep-alive")
//				.header("Content-Type", "application/json; charset=UTF-8")
//				.header("Host", "itunes.apple.com")
//				.header("Cache-Control", "no-cache")
//				.header("Postman-Token", "eef6cd57-341c-44e6-bd0e-96078ed4025c")
//				.queryString("id", "1278832543")
//				.queryString("displayable-kind", "11")
//				.queryString("startIndex", "0")
//				.queryString("endIndex", "100").customSSL()
//				.queryString("sort", "1")
//                .asByteData();
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		ByteArrayInputStream in = new ByteArrayInputStream(responseData);
//		GZIPInputStream gunzip = new GZIPInputStream(in);
//		byte[] buffer = new byte[256];
//		int n;
//		while ((n = gunzip.read(buffer)) >= 0) {
//			out.write(buffer, 0, n);
//		}
//		// toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)
//		String body = out.toString();
//		System.out.println(body);

		BASE64Decoder base64Encoder = new sun.misc.BASE64Decoder();
		String classStr = new String(base64Encoder.decodeBuffer("aHR0cDovL3d3dy55emFwcGxlLmNvbTo4MDkwL1lUZmVlZGJhY2svOTI4OTI5NjQ0LzEwMDQvQkU5%0ANTQzMEYtM0E3NC00QjUyLTk0MEQtODFDQUM5M0NFRTEx"),"UTF-8");
		System.out.println(classStr);
	}
	
	public static boolean is_file(String typeName){
		test t=new test("df");
    	String type="File";
    	//判断是不是file类型
    	if(type.equalsIgnoreCase(typeName.substring(0,type.length()))){
    		String StrNum=typeName.substring(type.length());
    		//文件大小命名必须为File或File[1-PIC_UPLOAD_MAX_NUM]
    		if(StrNum.length()==0){
    			return true;
    		}else if(Integer.parseInt(StrNum)>=1 && Integer.parseInt(StrNum)<9){
    		    return true;
    		}else{
    			return false;
    		}
    	}else{
    		return false;
    	}
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}

class Task1 implements Runnable {

	@Override
	public void run() {
		test  t = test.getInstance();
		try {
			while (true) {
				t.task1();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class Task2 implements Runnable {

	@Override
	public void run() {
		test  t = test.getInstance();
		try {
			while (true) {
				t.task2();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
