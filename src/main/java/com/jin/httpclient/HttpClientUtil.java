package com.jin.httpclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	private final static int DEFALUT_TIMEOUT = 3000 ;
	private final static boolean DEFALUT_HTTPS_CER_VERIFY = true ;
	private final static String DEFALUT_DOWNLOAD_DIR = "E:/file" ;
	
	/**
	 * 发送 get请求
	 * 
	 * @param url
	 * @param params 参数
	 * @param timeout 过期时间
	 * @param httpsCerVerify httpss证书验证    true：需要  false 不需要
	 * @return
	 */
	public static String get(String url, List<NameValuePair> params,int timeout,boolean httpsCerVerify) throws Exception {
		HttpClient httpClient = null;
		if(httpsCerVerify){
			httpClient = new DefaultHttpClient();
		}else{
			httpClient = new SSLClient();
		}
		//设置过期时间
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,timeout);
		String body = null;
		HttpGet httpget = null;
		int status;
		try {
			// Get请求
			httpget = new HttpGet(url);
			// 设置参数

			String str = EntityUtils.toString(new UrlEncodedFormEntity(params,
					"utf-8"));
			httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));

			httpget.addHeader("Content-Type", "text/html;charset=UTF-8");
			httpClient.getParams().setParameter(
					CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
			httpget.getParams().setParameter(
					CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
			// 发送请求
			HttpResponse httpresponse = httpClient.execute(httpget);
			// 获取返回数据
			httpresponse.getParams().getParameter(
					CoreProtocolPNames.HTTP_CONTENT_CHARSET);
			HttpEntity entity = httpresponse.getEntity();
			body = EntityUtils.toString(entity, "UTF-8");
			status= httpresponse.getStatusLine().getStatusCode();

			if (entity != null) {
				entity.consumeContent();
			}
		}finally {
			if (httpget != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}
		return body;
	}
	
	/**
	 * get下载应用
	 * *@param url
	 * @param params 参数
	 * @param timeout 过期时间
	 * @param fileDir 下载路径
	 * @return 状态
	 */
	public static int getFile(String url, List<NameValuePair> params,int timeout,String fileDir) throws Exception {
		HttpClient httpClient = null;
		httpClient = new DefaultHttpClient();
		//设置过期时间
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,timeout);
		String body = null;
		HttpGet httpget = null;
		int status;
		try {
			// Get请求
			httpget = new HttpGet(url);
			// 设置参数

			String str = EntityUtils.toString(new UrlEncodedFormEntity(params,
					"utf-8"));
			httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));

			httpget.addHeader("Content-Type", "text/html;charset=UTF-8");
			httpClient.getParams().setParameter(
					CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
			httpget.getParams().setParameter(
					CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
			// 发送请求
			HttpResponse httpresponse = httpClient.execute(httpget);
			// 获取返回数据
			httpresponse.getParams().getParameter(
					CoreProtocolPNames.HTTP_CONTENT_CHARSET);
			HttpEntity entity = httpresponse.getEntity();
			status= httpresponse.getStatusLine().getStatusCode();
			InputStream in = entity.getContent(); 
			File file = new File(getFilePath(url,fileDir)); 
			if (entity != null) {
				try{
					FileOutputStream fout = new FileOutputStream(file);  
					int l = -1;  
		            byte[] tmp = new byte[8192];  
		            while ((l = in.read(tmp)) != -1) {  
		                fout.write(tmp, 0, l);  
		                // 注意这里如果用OutputStream.write(buff)的话，图片会失真，大家可以试试  
		            }  
		            fout.flush();  
		            fout.close();  
				}finally{
					// 关闭低层流。  
					  in.close();  
				}
			}
		}finally {
			if (httpget != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}
		return status;
	}
	/**
	 * 获取文件名
	 * @param url
	 * @param fileDir
	 * @return
	 */
	private static String getFilePath(String url,String fileDir){
		if(fileDir == null){
			fileDir = DEFALUT_DOWNLOAD_DIR;
		}
		String fileName = url.substring(url.lastIndexOf("/"));
		return fileDir+ fileName;
	}
	

	
	/**
	 * 发送 Post请求
	 * 
	 * @param url
	 * @param params 参数
	 * @param timeout 过期时间
	 * @param httpsCerVerify httpss证书验证    true：需要  false 不需要
	 * @return
	 */
	
	public static String post(String url, List<NameValuePair> params,int timeout,boolean httpsCerVerify) throws Exception{
		HttpClient httpClient = null;
		if(httpsCerVerify){
			httpClient = new DefaultHttpClient();
		}else{
			httpClient = new SSLClient();
		}
		//设置过期时间
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,timeout);
		HttpPost httppost = new HttpPost(url);
		String body = null;
		try {
			//将请求封装在请求头里面[舍弃]
//			String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
//			httppost.setURI(new URI(httppost.getURI().toString() + "?" + str));
			httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));  
			HttpResponse httpresponse = httpClient.execute(httppost);
			HttpEntity entity = httpresponse.getEntity();
			body = EntityUtils.toString(entity, "UTF-8");
		} catch(UnknownHostException e){
			e.toString();
		}finally {
			if (httppost != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}
		return body;

	}
	
	
	/**
	 * 带参数下载
	 * @param url 
	 * @param type 0 get 1post
	 * @param timeout 过期时间
	 * @param fileDir 下载路径
	 * @return 状态
	 */
	public static int requestGetFile(String url, int type,int timeout,String fileDir)  throws Exception {
		//拆开参数
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		int i = url.indexOf("?");
		String uri,body;
		int status = -1;
		if (i == -1){
			uri = url;
		}else{
			uri = url.substring(0, i);
			String paramStr = url.substring(i + 1, url.length());
			String[] paramS = paramStr.split("&");
			for (String param : paramS) {
				String[] tempParam = param.split("=");
				if(tempParam.length==1){
					paramList.add(new BasicNameValuePair(tempParam[0],""));
				}else{
					paramList.add(new BasicNameValuePair(tempParam[0],
							tempParam[1]));
				}
				
			}
		}
		if(type == 0){
			status = getFile(uri, paramList,timeout,fileDir);
			
		}else{
//			status = post(uri, paramList,timeout);
		}
		return status;
	}
	
	public static int requestGetFile(String url, int type)  throws Exception {
		return requestGetFile(url,type,DEFALUT_TIMEOUT,null); 
	}

	
	
	
	/**
	 * 带参数连接请求
	 * @param url 
	 * @param type 0 get 1post
	 * @param timeout 过期时间
	 * @param httpsCerVerify httpss证书验证    true：需要  false 不需要
	 * @return
	 */
	public static String requestWithParam(String url, int type,int timeout,boolean httpsCerVerify)  throws Exception {
		//拆开参数
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		int i = url.indexOf("?");
		String uri,body;
		int status;
		if (i == -1){
			uri = url;
		}else{
			uri = url.substring(0, i);
			String paramStr = url.substring(i + 1, url.length());
			String[] paramS = paramStr.split("&");
			for (String param : paramS) {
				String[] tempParam = param.split("=");
				if(tempParam.length==1){
					paramList.add(new BasicNameValuePair(tempParam[0],""));
				}else{
					paramList.add(new BasicNameValuePair(tempParam[0],
							tempParam[1]));
				}
				
			}
		}
		if(type == 0){
			body = get(uri, paramList,timeout,httpsCerVerify);
			
		}else{
			body = post(uri, paramList,timeout,httpsCerVerify);
		}
		return body;
	}
	
	public static String requestWithParam(String url, int type)  throws Exception {
		return requestWithParam(url,type,DEFALUT_TIMEOUT,DEFALUT_HTTPS_CER_VERIFY); 
	}
	public static String requestWithParam(String url, int type,boolean httpsCerVerify)  throws Exception {
		return requestWithParam(url,type,DEFALUT_TIMEOUT,httpsCerVerify); 
	}
	
	

	public static void main(String[] args) throws Exception {
		String url="http://union.uc.cn/public/ios_promotion.php?ch=zhangjd@uczbjfq4&idfa=5A58EF1E-EEF2-478D-94EE-709B98407510&time=2016-03-03 15:58:05&sign=EACF5BD2EA73541DAE8925087BF72C68";
		String result = null;
//		// 拆开参数
//		int i = url.indexOf("?");
//		String uri = url.substring(0, i);
//		String paramStr = url.substring(i + 1, url.length());
//		String[] paramS = paramStr.split("&");
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
//		for (String param : paramS) {
//			String[] tempParam = param.split("=");
//			paramList.add(new BasicNameValuePair(tempParam[0], tempParam[1]));
//		}
		String uri = "http://m.api.zhe800.com/operation/click/v2/activation?source=mopinzhe800&appid=tao800&udid=02:00:00:00:00:00&idfa=35BA7DFF-56CF-4E59-B098-55026A516170&sign=8e99eedc3ac8632cb02941149431d6";
//		result = requestWithParam("http://thirdpart.kaola.com/idfa/third/notifyIdfa",1);
	}

}