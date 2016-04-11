package com.jin.java;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy; 
/**
 * 
 * @author liguang.jin
 *RetentionPolicy 不可缺少
 */
@Retention(RetentionPolicy.RUNTIME)  
public @interface MyAnnotation{
	//赋值age为18 default不是默认的
	int age() default 18;
	int[] array() default { 2, 4, 5, 6 };  
}