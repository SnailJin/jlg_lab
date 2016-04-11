package com.jin.jlg.netty;

import java.io.Serializable;

public class Command implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String actionName;
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

}
