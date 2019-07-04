package com.jin.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesDemo {

	private static void readJVM() {
		Properties pps = System.getProperties();
		pps.list(System.out);
	}

	private static void readFile() {
		Properties pps = new Properties();
		try {
			pps.load(new FileInputStream("E:/develop/code/jlg_lab/src/main/java/com/jin/java/Test.properties"));
			Enumeration enum1 = pps.propertyNames();// 得到配置文件的名字
			while (enum1.hasMoreElements()) {
				String strKey = (String) enum1.nextElement();
				String strValue = pps.getProperty(strKey);
				System.out.println(strKey + "=" + strValue);
			}
			System.out.println(pps.getProperty("jin"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		readFile();
	}

}
