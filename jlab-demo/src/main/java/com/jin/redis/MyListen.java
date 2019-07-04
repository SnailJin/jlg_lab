package com.jin.redis;

import redis.clients.jedis.JedisPubSub;

public class MyListen extends JedisPubSub {

	@Override
	// 取得订阅的消息后的处理  
	public void onMessage(String channel, String message) {
		// TODO Auto-generated method stub
		System.out.println(channel + "=" + message);  
	}

	@Override
	// 取得按表达式的方式订阅的消息后的处理  
	public void onPMessage(String pattern, String channel, String message) {
		// TODO Auto-generated method stub
		 System.out.println(pattern + "=" + channel + "=" + message);  
	}

	@Override
	 // 初始化订阅时候的处理 
	public void onSubscribe(String channel, int subscribedChannels) {
		// TODO Auto-generated method stub

	}

	@Override
	// 取消订阅时候的处理  
	public void onUnsubscribe(String channel, int subscribedChannels) {
		// TODO Auto-generated method stub

	}

	@Override
	 // 取消按表达式的方式订阅时候的处理  
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		// TODO Auto-generated method stub

	}

	@Override
	// 初始化按表达式的方式订阅时候的处理  
	public void onPSubscribe(String pattern, int subscribedChannels) {
		// TODO Auto-generated method stub

	}

}
