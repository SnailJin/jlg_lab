package com.jin.httpclient;

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
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	/**
	 * 发送Get请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String get(String url, List<NameValuePair> params) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		String body = null;
		HttpGet httpget = null;
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
	 * 发送 Post请求
	 * 
	 * @param url
	 * @param reqXml
	 * @return
	 */
	public static String post(String url, List<NameValuePair> params) throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		String body = null;
		try {
			String str = EntityUtils.toString(new UrlEncodedFormEntity(params,
					HTTP.UTF_8));
			httppost.setURI(new URI(httppost.getURI().toString() + "?" + str));
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
	 * 带参数连接请求
	 * @param url 
	 * @param type 0 get 1post
	 * @return
	 */
	public static String requestWithParam(String url, int type)  throws Exception {
		//拆开参数
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		int i = url.indexOf("?");
		String uri,body;
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
			body = get(uri, paramList);
		}else{
			body = post(uri, paramList);
		}
		return body;
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
		result = requestWithParam("http://thirdpart.kaola.com/idfa/third/notifyIdfa",1);
	}

}