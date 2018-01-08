/**  
 * Project Name:jlg_lab  
 * File Name:WebSocketServerInitializer.java  
 * Package Name:com.jin.jlg.netty.websock  
 * Date:2018年1月8日下午12:03:07  
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package com.jin.jlg.netty.websock;  

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.SslContext;

/**  
 * channel生成器
 * Function: TODO ADD FUNCTION. 
 * Reason:   TODO ADD REASON. 
 * Date:     2018年1月8日 下午12:03:07 
 * @author   liguang.jin  
 * @version    
 * @see        
 */
public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel>{
	private static final String WEBSOCKET_PATH = "/websocket";
	 private final SslContext sslCtx;
	 public WebSocketServerInitializer(SslContext sslCtx) {
		 this.sslCtx = sslCtx;
	 }
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		 ChannelPipeline pipeline = ch.pipeline();
		 if (sslCtx != null) {
			 pipeline.addLast(sslCtx.newHandler(ch.alloc()));
		 }
		 pipeline.addLast(new HttpServerCodec());
		 pipeline.addLast(new HttpObjectAggregator(65536));
		 pipeline.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
		 pipeline.addLast(new WebSocketIndexPageHandler(WEBSOCKET_PATH));
		 pipeline.addLast(new WebSocketFrameHandler());
	}

}
  
