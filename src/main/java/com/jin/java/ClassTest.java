package com.jin.java;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * class
 *  基本方法测试
 * @author liguang.jin
 * 
 * @Deprecated，用来表示某个类的属性或方法已经过时，不想别人再用时，在属性和方法
 *@Override用在方法上，当我们想重写一个方法时，在方法上加@Override，当我们方法
 *的名字出错时，编译器就会报错，
 *@SuppressWarnings用来压制程序中出来的警告，比如在没有用泛型或是方法已经过时的时候，
 *@Retention可以用来修饰注解，是注解的注解，称为元注解。
 *用@Retention(RetentionPolicy.CLASS)修饰的注解，表示注解的信息被保留在class文件(字节码文件)中当程序编译时，但不会被虚拟机读取在运行的时候；
 *用@Retention(RetentionPolicy.SOURCE )修饰的注解,表示注解的信息会被编译器抛弃，不会留在class文件中，注解的信息只会留在源文件中； 
 *用@Retention(RetentionPolicy.RUNTIME )修饰的注解，表示注解的信息被保留在class文件(字节码文件)中当程序编译时，会被虚拟机保留在运行时，
 */

@MyAnnotation(age=19)
public class ClassTest {
	public ClassTest() {
		this.username="jin";
	}
	private String username;
	private int age;
	
	public void output(String name) {
		System.out.println(name+" 在的");
	}
	
	/**
	 * @param args
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws Exception {
		ClassTest test = new ClassTest();
		System.out.println("获得class："+ClassTest.class);
		Class<? extends ClassTest> testClass = test.getClass();
		System.out.println("获得class== ："+testClass);
		System.out.println(test.getClass()==ClassTest.class);
		//方法集合
		Method[] methods = testClass.getDeclaredMethods();
		//获取setUsername方法
		Method method = testClass.getDeclaredMethod("output", String.class);
		//获取对象
		Field field = testClass.getDeclaredField("username");
		method.invoke(test,"li");
		System.out.println("getName:"+testClass.getName());
		MyAnnotation myAnnotation = testClass.getAnnotation(MyAnnotation.class);
		System.out.println("myAnnotation.age:"+myAnnotation.age());
		Class<?>[] testClasschildren = testClass.getDeclaredClasses();
		System.out.println("testClasschildren.length:"+testClasschildren.length);
		Constructor<?>  constructor = testClass.getDeclaredConstructor(new Class[0]);
		System.out.println(constructor.toString());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	class test1{
		
	}
	
}


