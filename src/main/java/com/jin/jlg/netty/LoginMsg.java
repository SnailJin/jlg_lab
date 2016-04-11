package com.jin.jlg.netty;

public class LoginMsg extends BaseMsg {
	public LoginMsg() {
		super();
		setType(MsgType.LOGIN);
	}
	private String username ;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
