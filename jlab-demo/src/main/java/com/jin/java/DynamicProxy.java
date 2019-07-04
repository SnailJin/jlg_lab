package com.jin.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 缺点：动态代理效率远远慢与静态
 * 优点：接口自动去找
 * @author liguang.jin
 * 
 */
interface Buy {
	public void buyTicket(String username);
}

class Visitor implements Buy {
	private String username;

	@Override
	public void buyTicket(String username) {
		System.out.println(username + "帮" + this.username + "火车票!");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

// 动态角色：动态生成代理类
class BusinessImplProxy implements InvocationHandler {
	private Object business;

	public BusinessImplProxy(Object business) {
		this.business = business;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//System.out.println("invoke");
		return method.invoke(business, args);
	}

	public static Object factory(Object obj) {
		Class<? extends Object> cls = obj.getClass();
		return Proxy.newProxyInstance(cls.getClassLoader(),
				cls.getInterfaces(), new BusinessImplProxy(obj));
	}

}

public class DynamicProxy {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Visitor visitor = new Visitor();
		visitor.setUsername("jin liguang");
		for (int i = 0; i < 1000; i++) {
			Buy by = (Buy) BusinessImplProxy.factory(visitor);
			by.buyTicket("buinesss");
		}
		System.out.println(System.currentTimeMillis() - startTime);
	}

}
