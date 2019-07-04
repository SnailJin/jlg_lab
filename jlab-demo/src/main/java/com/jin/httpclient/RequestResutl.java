package com.jin.httpclient;

import java.net.URI;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class RequestResutl {
	/**
	 * 添加伪装参数
	 * 
	 * @return
	 */
	public static HttpGet setBrowHead(HttpGet request) {
		request.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		request.setHeader("Accept-Encoding", "gzip,deflate,sdch");
		request.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		request.setHeader("Cache-Control", "max-age=0");
		request.setHeader("Connection", "keep-alive");
		request.setHeader("Host", "movie.douban.com");
		request.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
		return request;

	}

	/**
	 * 
	 * @param url
	 * @param paramMap
	 *            不定长度 如果长度为2则head+params，否则param
	 */
	public static String returnGet(String url,
			JSONObject... paramList) {
		HttpClient httpclient = new DefaultHttpClient();
		JSONObject headMap = null;
		JSONObject paramMap = null;
		String result ="";
		try {
			HttpGet httpget = new HttpGet(url);
//			httpget=setBrowHead(httpget);
			if(paramList ==null || paramList.length == 0 ){
				
			}else if(paramList.length ==1){
				paramMap = paramList[0];
			}else if(paramList.length == 2){
				headMap = paramList[0];
				paramMap = paramList[1];
			}
			
			if (headMap != null && headMap.size() > 0) {
				for (Entry entry : headMap.entrySet()) {
					httpget.setHeader(entry.getKey().toString(), entry
							.getValue().toString());
				}
			}
			
			if (paramMap != null && paramMap.size() > 0) {
				List<NameValuePair> params =new ArrayList<NameValuePair>();
				for (Entry entry : paramMap.entrySet()) {
					params.add(new BasicNameValuePair(entry.getKey().toString(), entry
							.getValue().toString()));
				}
				String str = EntityUtils.toString(new UrlEncodedFormEntity(params,
						"utf-8"));
				httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
			}
			 // 执行get请求.    
			HttpResponse response = httpclient.execute(httpget);
			 // 获取响应实体    
            HttpEntity entity = response.getEntity();
            try{
            System.out.println("--------------------------------------");  
            // 打印响应状态    
            System.out.println(response.getStatusLine());  
            if (entity != null) {  
                // 打印响应内容长度    
                System.out.println("Response content length: " + entity.getContentLength());  
                result = EntityUtils.toString(entity);
                // 打印响应内容    
                System.out.println("Response content: " +result);  
                
            }  
            System.out.println("------------------------------------");  
            }finally{
            	httpget.releaseConnection();
            }
        }catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for(int i =0 ;i<1;i++){
			String params ="{'apple_id':'1069287597','source':'mopin','ip':'0.0.0.1','idfa':'"+ UUID.randomUUID().toString().trim().replaceAll("-", "")+"'}";
			JSONObject paramMap = (JSONObject) JSONObject.parse(params);
			String result = returnGet("http://172.16.3.65:7329/web/notify", paramMap);
		}
	
	}

}
