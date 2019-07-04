package com.jin.spring;

public class AgencyLog {
	public void berforeBuy() {
		System.out.println("买票前!");
	}
	public void afterBuy(String name) {
		System.out.println(name+"后!");
	}
}
