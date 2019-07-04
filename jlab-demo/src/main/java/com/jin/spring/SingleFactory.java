package com.jin.spring;

public class SingleFactory {
	private static SingleFactory instance;
	private SingleFactory(){};
	public static SingleFactory getInstance() {
		if(instance == null){
			instance =new SingleFactory();
		}
		return instance;
	}

}
