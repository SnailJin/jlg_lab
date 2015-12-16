package com.jlg.netty;

import java.util.Map;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.socket.SocketChannel;
import org.jboss.netty.util.internal.ConcurrentHashMap;

public class NettyChannelMap {
	private static Map<String,SocketChannel> map=new ConcurrentHashMap<String, SocketChannel>();
    public static void add(String clientId,SocketChannel socketChannel){
        map.put(clientId,socketChannel);
    }
    public static Channel get(String clientId){
       return map.get(clientId);
    }
    public static void remove(SocketChannel socketChannel){
        for (Map.Entry entry:map.entrySet()){
            if (entry.getValue()==socketChannel){
                map.remove(entry.getKey());
            }
        }
    }
}
