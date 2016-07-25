package com.jin.spring;

public class Agency {
	private Visitor visitor;

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}
	
	public void output(String name) {
		visitor.buyTitck(name);
	}
	
}
