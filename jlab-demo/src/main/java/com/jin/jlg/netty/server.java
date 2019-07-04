package com.jin.jlg.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ClassResolvers;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;


public class server {

	public static void main(String[] args) {

			// TODO Auto-generated method stub
			ServerBootstrap bootstrap = new ServerBootstrap( new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),Executors.newCachedThreadPool()));
			bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

				@Override
				public ChannelPipeline getPipeline() throws Exception {
					// TODO Auto-generated method stub
					return Channels.pipeline(
					        new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())),
					        new ObjectServerHandler());
				}
				
			});
			 System.out.println("管道注入成功");
	        bootstrap.bind(new InetSocketAddress(8078));
	        System.out.println("端口绑定成功");

		}
		
		 private static class HelloServerHandler extends SimpleChannelHandler {

			@Override
			public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
				System.out.println("Hello world, I'm server.");  
			}
			 
		 }

	}
