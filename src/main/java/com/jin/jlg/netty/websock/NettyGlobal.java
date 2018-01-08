/**  
 * Project Name:jlg_lab  
 * File Name:NettyGlobal.java  
 * Package Name:com.jin.jlg.netty.websock  
 * Date:2018年1月8日下午5:11:37  
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.  
 *  
*/  
  
package com.jin.jlg.netty.websock;  

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**  
 * ClassName:NettyGlobal 
 * Function: TODO ADD FUNCTION. 
 * Reason:   TODO ADD REASON. 
 * Date:     2018年1月8日 下午5:11:37 
 * @author   liguang.jin  
 * @version    
 * @see        
 */
public class NettyGlobal {
	public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);  
}
  
