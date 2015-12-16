package com.java;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import redis.clients.util.SafeEncoder;



public class test {
	private String username;
	public test(String username){
		this.setUsername(username);
	}
	public test(){
		this("jin");
	}
	
	public static enum Command {
		PING, SET, GET, QUIT, EXISTS, DEL, TYPE, FLUSHDB, KEYS, RANDOMKEY, RENAME, RENAMENX, RENAMEX, DBSIZE, EXPIRE, EXPIREAT, TTL, SELECT, MOVE, FLUSHALL, GETSET, MGET, SETNX, SETEX, MSET, MSETNX, DECRBY, DECR, INCRBY, INCR, APPEND, SUBSTR, HSET, HGET, HSETNX, HMSET, HMGET, HINCRBY, HEXISTS, HDEL, HLEN, HKEYS, HVALS, HGETALL, RPUSH, LPUSH, LLEN, LRANGE, LTRIM, LINDEX, LSET, LREM, LPOP, RPOP, RPOPLPUSH, SADD, SMEMBERS, SREM, SPOP, SMOVE, SCARD, SISMEMBER, SINTER, SINTERSTORE, SUNION, SUNIONSTORE, SDIFF, SDIFFSTORE, SRANDMEMBER, ZADD, ZRANGE, ZREM, ZINCRBY, ZRANK, ZREVRANK, ZREVRANGE, ZCARD, ZSCORE, MULTI, DISCARD, EXEC, WATCH, UNWATCH, SORT, BLPOP, BRPOP, AUTH, SUBSCRIBE, PUBLISH, UNSUBSCRIBE, PSUBSCRIBE, PUNSUBSCRIBE, PUBSUB, ZCOUNT, ZRANGEBYSCORE, ZREVRANGEBYSCORE, ZREMRANGEBYRANK, ZREMRANGEBYSCORE, ZUNIONSTORE, ZINTERSTORE, SAVE, BGSAVE, BGREWRITEAOF, LASTSAVE, SHUTDOWN, INFO, MONITOR, SLAVEOF, CONFIG, STRLEN, SYNC, LPUSHX, PERSIST, RPUSHX, ECHO, LINSERT, DEBUG, BRPOPLPUSH, SETBIT, GETBIT, SETRANGE, GETRANGE, EVAL, EVALSHA, SCRIPT, SLOWLOG, OBJECT, BITCOUNT, BITOP, SENTINEL, DUMP, RESTORE, PEXPIRE, PEXPIREAT, PTTL, INCRBYFLOAT, PSETEX, CLIENT, TIME, MIGRATE, HINCRBYFLOAT, SCAN, HSCAN, SSCAN, ZSCAN, WAIT, CLUSTER, ASKING;

		public final byte[] raw;

		Command() {
		    raw = SafeEncoder.encode(this.name());
		}
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 List<test> list = new ArrayList<>();
		 test A = new test("jin");
		 test B = new test("jin");
		 list.add(A);
		 list.add(B);
		 for(test t : list){
			 t.setUsername("a");
		 }
		 for(test t : list){
			 System.out.println(t.getUsername());
		 }
		 
		 

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
