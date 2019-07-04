package com.jin.java;


/**
 *静态代理
 * @author liguang.jin
 *
 */

//静态生成代理类
class BusinessImplProxy1 implements Buy{
	private Buy business;
	public BusinessImplProxy1(Buy business) {
		this.business=business;
	}
	@Override
	public void buyTicket(String username) {
		business.buyTicket(username);
	}
	
	public static BusinessImplProxy1 factory(Visitor visitor) {
		return  new BusinessImplProxy1(visitor);
	}
	
}
public class StaticProxy {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Visitor visitor = new Visitor();
		visitor.setUsername("jin liguang");
		for(int i = 0 ;i<100000;i++){
			BusinessImplProxy1 nip = BusinessImplProxy1.factory(visitor);
			nip.buyTicket("buinesss");
		}
		System.out.println(System.currentTimeMillis()-startTime);
		
		
	}

}
