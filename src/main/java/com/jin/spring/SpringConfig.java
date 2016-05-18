package com.jin.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
	@Bean
	public Visitor visitor() {
		Visitor visitor = new Visitor();
		return  visitor;
	}
	
	@Bean
	public Agency agency() {
		Agency agency = new Agency();
		agency.setVisitor(visitor());
		return agency;
	}
}
