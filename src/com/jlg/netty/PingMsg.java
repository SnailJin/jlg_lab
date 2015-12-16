package com.jlg.netty;

public class PingMsg extends BaseMsg {
	public PingMsg() {
		super();
		setType(MsgType.PING);
	}
}
