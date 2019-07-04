package com.jin.java.transientTest;

import java.io.Serializable;

public class User implements Serializable{
	 private static final long serialVersionUID = 8294180014912103005L;  
	    
	    private String username;
	    private transient String passwd;
	    private static String nickName; 
	    
	    public String getUsername() {
	        return username;
	    }
	    
	    public void setUsername(String username) {
	        this.username = username;
	    }
	    
	    public String getPasswd() {
	        return passwd;
	    }
	    
	    public void setPasswd(String passwd) {
	        this.passwd = passwd;
	    }

		public static String getNickName() {
			return nickName;
		}

		public static void setNickName(String nickName) {
			User.nickName = nickName;
		}
}
