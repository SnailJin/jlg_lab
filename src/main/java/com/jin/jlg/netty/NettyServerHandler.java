package com.jin.jlg.netty;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.alibaba.fastjson.JSONObject;

public class NettyServerHandler extends SimpleChannelHandler{

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		// TODO Auto-generated method stub
		super.messageReceived(ctx, e);
		BaseMsg msg= (BaseMsg)e.getMessage();
		Channel channel = e.getChannel();
		switch (msg.getType()) {
		case PING:
			PingMsg pingMsg = (PingMsg)msg;
			channel.write(pingMsg);
			System.out.println("client"+msg.getClientId()+" ping成功");
			break;
		case LOGIN:
			LoginMsg login = (LoginMsg)msg;
			channel.write(login);
			System.out.println("client"+msg.getClientId()+" 登录成功");
			break;
		}
	}

}
