package com.jin.java;
//*****************************浅拷贝**************************************
class Prototype{
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
class ShallowCopy implements Cloneable{
	private Object prototype;
	private int id;
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Object getPrototype() {
		return prototype;
	}

	public void setPrototype(Object prototype) {
		this.prototype = prototype;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

//*****************************深拷贝**************************************
class PrototypeC implements Cloneable {
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
class DeepCopy implements Cloneable{
	private PrototypeC prototype;
	private int id;
	public Object clone() {
		try {
			DeepCopy dc = (DeepCopy) super.clone();
			dc.prototype =(PrototypeC) this.prototype.clone();
			return dc;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public PrototypeC getPrototype() {
		return prototype;
	}

	public void setPrototype(PrototypeC prototype) {
		this.prototype = prototype;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
public class PrototypeDemo {

	public void deepCopy() {
		PrototypeC pc = new PrototypeC();
		pc.setUsername("jin");
		DeepCopy dc = new DeepCopy();
		dc.setId(1);
		dc.setPrototype(pc);
		DeepCopy dc1 = (DeepCopy) dc.clone();
		dc1.getPrototype().setUsername("li");
		dc1.setId(2);
		System.out.println("dc id:"+dc.getId());
		System.out.println("dc username:"+dc.getPrototype().getUsername());
		System.out.println("dc1 id:"+dc1.getId());
		System.out.println("dc1 username:"+dc1.getPrototype().getUsername());
	}
	
	public void shallowCopy() {
		Prototype pc = new Prototype();
		pc.setUsername("jin");
		ShallowCopy dc = new ShallowCopy();
		dc.setId(1);
		dc.setPrototype(pc);
		ShallowCopy dc1 = (ShallowCopy) dc.clone();
		((Prototype)dc1.getPrototype()).setUsername("li");
		dc1.setId(2);
		System.out.println("dc id:"+dc.getId());
		System.out.println("dc username:"+((Prototype)dc.getPrototype()).getUsername());
		System.out.println("dc1 id:"+dc1.getId());
		System.out.println("dc1 username:"+((Prototype)dc1.getPrototype()).getUsername());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrototypeDemo test =new PrototypeDemo();
		test.deepCopy();
		test.shallowCopy();
	}

}
