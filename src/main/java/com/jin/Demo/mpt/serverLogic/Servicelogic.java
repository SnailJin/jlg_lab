package com.jin.Demo.mpt.serverLogic;

public class Servicelogic {
	public static String getFirstInvokeClassName() {
		//获取栈信息，即从入口到当前位置的所以函数的list集合
		StackTraceElement[] elements = new Exception().getStackTrace();
		if (null == elements || 0 == elements.length) {
			return "";
		}
		return elements[elements.length - 1].getClassName();
	}
	
	public static String test1(){
		return getFirstInvokeClassName();
	}
	public static String test2(){
		return test1();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(test2());
	}

}
