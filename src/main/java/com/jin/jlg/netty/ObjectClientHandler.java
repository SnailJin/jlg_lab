package com.jin.jlg.netty;


import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ObjectClientHandler extends SimpleChannelHandler{

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		super.channelConnected(ctx, e);
		send_object(ctx.getChannel());
	}
	
	private void send_object(Channel channel){
		Command command = new Command();
		command.setActionName("hello action");
		channel.write(command);
	}
}
