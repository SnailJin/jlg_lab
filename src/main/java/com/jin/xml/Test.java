package com.jin.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.jin.httpclient.HttpClientUtil;
import com.jin.java.Encrypt;

public class Test {

	/**
	 * @param args
	 */
	public static Map xmltoMap(String xml) {
		try {
			Map map = new HashMap();
			Document document = DocumentHelper.parseText(xml);
			Element nodeElement = document.getRootElement();
			List node = nodeElement.elements();
			for (Iterator it = node.iterator(); it.hasNext();) {
				Element elm = (Element) it.next();
				map.put(elm.attributeValue("label"), elm.getText());
				elm = null;
			}
			node = null;
			nodeElement = null;
			document = null;
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		Document doc = null;
		// TODO Auto-generated method stub
		String s = "";
		String md5_str="A1311346"+Encrypt.MD5("mopin2016").toLowerCase()+"1240411"+"1"+"1122334"+"20160524201214"+""+"OFCARD";
		md5_str=Encrypt.MD5(md5_str);
		String[] appIds = new String[]{"1240514","1240510","1240509","1240508","1240411","1240413","1240414","1240412",};
//		String url ="http://AXXXX.api2.ofpay.com/order.do?userid=A1311346&userpws="+Encrypt.MD5("mopin2016").toLowerCase()+"&cardid=1240411&cardnum=1&sporder_id=1122334&sporder_time=20160524201214&md5_str="+md5_str+"&version=6.0&phone=15558177991";
		String url ="http://AXXXX.api2.ofpay.com/queryprice.do?userid=A1311346&userpws="+Encrypt.MD5("mopin2016").toLowerCase()+"&version=6.0&cardid=";
		for(String str : appIds){
			String testUrl =url + str;
//			System.out.println(url);
			String body = HttpClientUtil.requestWithParam(testUrl, 0);
			System.out.println(body);
//			Document document = DocumentHelper.parseText(body);
		}
		
//		// 获取根节点元素对象
//		Element node = document.getRootElement();
//		System.out.println("当前节点的名称：：" + node.getName());
//		for (Iterator it = node.elementIterator(); it.hasNext();) {
//			Element element = (Element) it.next();	
//			System.out.println(element.getName()+"----"+element.getData());
//		}

	}

}
