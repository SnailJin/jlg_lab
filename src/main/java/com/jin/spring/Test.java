package com.jin.spring;

import java.net.URL;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ClassPathResource res =new ClassPathResource("bean.xml");
//		DefaultListableBeanFactory facotory =new DefaultListableBeanFactory();
//		XmlBeanDefinitionReader reader =new XmlBeanDefinitionReader(facotory);
//		reader.loadBeanDefinitions(res);
		ApplicationContext appCt2 = new FileSystemXmlApplicationContext("classpath:bean.xml");
		Agency agency = (Agency) appCt2.getBean("agency");
		agency.output();
		
	}

}
