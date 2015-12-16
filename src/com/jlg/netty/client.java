package com.jlg.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;
/**
 * netty 服务端代码
 * @author jlg
 *
 */
public class client {

	public static void main(String[] args) {
		 for(int i = 0;i<5;i++){
			 new MyThread().start();
		 }
		
	}
	
	public void start(){
		ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),Executors.newCachedThreadPool()));
		// 设置一个处理服务端消息和各种消息事件的类(Handler)  
	        bootstrap.setPipelineFactory(new ChannelPipelineFactory(){

				@Override
				public ChannelPipeline getPipeline() throws Exception {
					// TODO Auto-generated method stub
					//ObjectEncoder 进行编码，后面是handler处理
					//现在是单向传输所以客户端编码服务端解码，双向 的话编码解码服务客户端都要配置
					return Channels.pipeline(new ObjectEncoder(),new ObjectClientHandler(),new ObjectClientHandler());
				}
	        	 
	        });
	        System.out.println("管道注入成功");
	     // 连接到本地的8000端口的服务端  
	        bootstrap.connect(new InetSocketAddress(  
	                "127.0.0.1", 8088));  
	        
	        System.out.println("端口绑定成功");
	}
	
	 private static class HelloClientHandler extends SimpleChannelHandler {

		@Override
		public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
			// TODO Auto-generated method stub
			System.out.println("Hello world, I'm client.");  
		}
		 
	 }

}

class MyThread extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		client c = new client();
		c.start();
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
