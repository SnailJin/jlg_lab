package com.jin.java;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;


public class BaseUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url ="https://www.baidu.com/s?wd=Invalid%20initial%20heap%20size%3A%20-Xms&rsv_spt=1&rsv_iqid=0xbc666c43001131e3&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=1&inputT=947&rsv_t=1096fKs9z2nuIXxCEMIWsCq%2Bi6PiLN%2F4tirvYbWJ0tUTSMGH6xWbxng2Lb9EmVd%2FUkTr&oq=commons-codec&rsv_pq=bf36d16500118edd&rsv_sug3=31&rsv_sug1=34&rsv_sug7=100&rsv_n=2&rsv_sug2=0&rsv_sug4=1007";
		 try {
			byte[] encodeBase64 =  Base64.encodeBase64(url.getBytes("UTF-8"));
			System.out.println(new String(encodeBase64));
			byte[] decodeBase64 = Base64.decodeBase64(new String(encodeBase64));
			System.out.println(new String(decodeBase64));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
