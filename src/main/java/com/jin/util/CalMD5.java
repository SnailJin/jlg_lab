package com.jin.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 
 * @author jlg
 *md5 32位加密 
 *第二种commons-codec。jar DigestUtils.md5Hex("baidu.com"))
 */
public class CalMD5 {
	protected static char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'}; 
	//一种方法
	public String calMd5(String k) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
        md5.reset();
        byte[] keyBytes = null;
        try {
            keyBytes = k.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unknown string :" + k, e);
        }

        md5.update(keyBytes);
        BigInteger bi = new BigInteger(1, md5.digest());  
        String value=bi.toString(16);
        //首尾为0不行
        String tmp ="";
        for(int i=0;i<(32-value.length());i++){
        	tmp+="0";
        }
        value=tmp+value;
        return  value;
    }
	
	//第二种
	public String calMd5_2(String k) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
        md5.reset();
        byte[] keyBytes = null;
        try {
            keyBytes = k.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unknown string :" + k, e);
        }

        md5.update(keyBytes);
        return  bufferToHex(md5.digest(),0, md5.digest().length);
    }
	
	 private  String bufferToHex(byte bytes[], int m, int n) {  
	       StringBuffer stringbuffer = new StringBuffer(2 * n);  
	       int k = m + n;  
	       for (int l = m; l < k; l++) {  
	        appendHexPair(bytes[l], stringbuffer);  
	       }  
	       return stringbuffer.toString();  
	    }  
	      
	    private  void appendHexPair(byte bt, StringBuffer stringbuffer) {  
	       char c0 = hexDigits[(bt & 0xf0) >> 4];  
	       char c1 = hexDigits[bt & 0xf];  
	       stringbuffer.append(c0);  
	       stringbuffer.append(c1);  
	    }  
	

	public static void main(String[] args){
		CalMD5 calM5 = new CalMD5();
		//32位
		System.out.println(calM5.calMd5("jin"));
		System.out.println(calM5.calMd5_2("jin"));
	}
}
