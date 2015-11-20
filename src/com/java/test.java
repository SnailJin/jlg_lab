package com.java;

import java.util.ArrayList;
import java.util.regex.Pattern;



public class test {
	private String username;
	public test(String username){
		this.username=username;
	}
	public test(){
		this("jin");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	   

	}
	public static boolean is_file(String typeName){
		test t=new test("df");
    	String type="File";
    	//判断是不是file类型
    	if(type.equalsIgnoreCase(typeName.substring(0,type.length()))){
    		String StrNum=typeName.substring(type.length());
    		//文件大小命名必须为File或File[1-PIC_UPLOAD_MAX_NUM]
    		if(StrNum.length()==0){
    			return true;
    		}else if(Integer.parseInt(StrNum)>=1 && Integer.parseInt(StrNum)<9){
    		    return true;
    		}else{
    			return false;
    		}
    	}else{
    		return false;
    	}
	}
}
