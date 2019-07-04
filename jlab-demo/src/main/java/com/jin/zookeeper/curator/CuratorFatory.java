package com.jin.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.DefaultACLProvider;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorFatory {
	private final static String connectString ="47.98.146.37:7001,47.98.146.37:7002,47.98.146.37:7003";
	private final static String NAMESPACE ="test";
	private static CuratorFramework client;
	public static void start() {
		try{
		CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();
        client = builder.connectString(connectString)
                .sessionTimeoutMs(30000)
                .connectionTimeoutMs(30000)
                .canBeReadOnly(false)
                .retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
//                .namespace(NAMESPACE)
                .aclProvider(new DefaultACLProvider())
                .defaultData(null)
                .build();
        client.start();
        }catch(Exception e){
        	e.printStackTrace();  
        	 // 释放客户端连接  
        	client.close();
        }
	}
	
	public static CuratorFramework getInstance(){
		if(client == null){
			start();
		}
		return client;
	}
	
	public static void closeClient() {
		client.close();
	}

}
