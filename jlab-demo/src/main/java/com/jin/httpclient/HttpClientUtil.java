package com.jin.httpclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	private final static int DEFALUT_TIMEOUT = 3000;
	private final static boolean DEFALUT_HTTPS_CER_VERIFY = false;
	private static String clientType = "pc"; // pc,iphone,android
	private static String usserAgentSplit = ";;";
	private final static Map<String, String> usserAgent = new HashMap<String, String>() {
		{
			put("iphone",
					"User-Agent:Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
			put("android",
					"User-Agent:Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.23 Mobile Safari/537.36");
			put("pc",
					"User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
			put("appStore",
					"Host:itunes.apple.com;;X-Apple-Store-Front: 143465-19,29;;User-Agent: AppStore/2.0 iOS/10.2 model/iPhone8,1 hwp/s8000 build/14B150 ;;Accept-Language: zh-Hans-CN;;Accept-Encoding: gzip, deflate;;Connection: keep-alive");
		}
	};
	/**
	 * 是否要验证证书
	 */
	private static Boolean cerVerify = DEFALUT_HTTPS_CER_VERIFY;

	/**
	 * 发送 get请求
	 * 
	 * @param url
	 * @param params
	 *            参数
	 * @param timeout
	 *            过期时间
	 * @param httpsCerVerify
	 *            httpss证书验证 true：需要 false 不需要
	 * @param isUrlEncoder
	 *            是否转义
	 * @return
	 */
	public static String get(String url, String params, int timeout, boolean httpsCerVerify, boolean isUrlEncoder)
			throws Exception {
		HttpClient httpClient = null;
		if (httpsCerVerify || url.startsWith("https:")) {
			httpClient = new SSLClient();
		} else {
			httpClient = new DefaultHttpClient();
		}
		// 设置过期时间
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
		String body = null;
		HttpGet httpget = null;
		int status;
		try {
			// Get请求
			httpget = new HttpGet(url);
			// 设置参数
			String str = "";
			if (isUrlEncoder) {
				List<NameValuePair> paramList = _parseBasicNameValuePairList(params);
				str = EntityUtils.toString(new UrlEncodedFormEntity(paramList, "utf-8"));
			} else {
				str = EntityUtils.toString(new StringEntity(params, "utf-8"));
			}
			httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));

			// httpget.addHeader("Content-Type", "text/html;charset=UTF-8");
			String headerStr = usserAgent.get(clientType);
			if (!headerStr.isEmpty()) {
				String[] headerS = headerStr.split(usserAgentSplit);
				for (String header : headerS) {
					String[] element = header.split(":");
					httpget.addHeader(element[0].trim(), element[1].trim());
				}

			}
			// httpget.addHeader("User-Agent", usserAgent.get(clientType));

			httpClient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
			// 发送请求
			HttpResponse httpresponse = httpClient.execute(httpget);
			// 获取返回数据
			httpresponse.getParams().getParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET);
			HttpEntity entity = httpresponse.getEntity();
			// InputStream in = entity.getContent();
			// StringBuffer out = new StringBuffer();
			// byte[] b = new byte[4096];
			// for (int n; (n = in.read(b)) != -1;) {
			// out.append(new String(b, 0, n));
			// }
			// body=out.toString();
			status = httpresponse.getStatusLine().getStatusCode();
			Header header = httpresponse.getFirstHeader("Content-Encoding");
			if(header.getValue().equals("gzip")){
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				GZIPInputStream gunzip = new GZIPInputStream(entity.getContent());
				byte[] buffer = new byte[256];
				int n;
				while ((n = gunzip.read(buffer)) >= 0) {
					out.write(buffer, 0, n);
				}
				// toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)
				body = out.toString();
			}else{
				body = EntityUtils.toString(entity, "UTF-8");
			}

			
			

			if (entity != null) {
				entity.consumeContent();
			}
		} finally {
			if (httpget != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}
		return body;
	}

	public static String post(String url, String params) throws Exception {
		return post(url, params, DEFALUT_TIMEOUT, false, false);
	}

	/**
	 * 发送 Post请求
	 * 
	 * @param url
	 * @param params
	 *            参数
	 * @param timeout
	 *            过期时间
	 * @param httpsCerVerify
	 *            httpss证书验证 true：需要 false 不需要
	 * @return
	 */

	public static String post(String url, String params, int timeout, boolean httpsCerVerify, boolean isUrlEncoder)
			throws Exception {
		HttpClient httpClient = null;
		if (httpsCerVerify) {
			httpClient = new DefaultHttpClient();
		} else {
			httpClient = new SSLClient();
		}
		// 设置过期时间
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
		HttpPost httppost = new HttpPost(url);
		String body = null;
		try {
			// 将请求封装在请求头里面[舍弃]
			// String str = EntityUtils.toString(new
			// UrlEncodedFormEntity(params));
			// httppost.setURI(new URI(httppost.getURI().toString() + "?" +
			// str));
			StringEntity stringEntity;
			if (isUrlEncoder) {
				List<NameValuePair> paramList = _parseBasicNameValuePairList(params);
				stringEntity = new UrlEncodedFormEntity(paramList, "utf-8");
			} else {
				stringEntity = new StringEntity(params, "utf-8");
				stringEntity.setContentType("application/x-www-form-urlencoded;charset=UTF-8");
			}
			httppost.addHeader("User-Agent", usserAgent.get(clientType));
			httppost.setEntity(stringEntity);
			HttpResponse httpresponse = httpClient.execute(httppost);
			HttpEntity entity = httpresponse.getEntity();
			body = EntityUtils.toString(entity, "UTF-8");
		} catch (UnknownHostException e) {
			e.toString();
		} finally {
			if (httppost != null) {
				httpClient.getConnectionManager().shutdown();
			}
		}
		return body;

	}

	/**
	 * 带参数连接请求
	 * 
	 * @param url
	 * @param type
	 *            0 get 1post
	 * @param timeout
	 *            过期时间
	 * @param httpsCerVerify
	 *            httpss证书验证 true：需要 false 不需要
	 * @return
	 */
	public static String requestWithParam(String url, int type, int timeout, boolean httpsCerVerify,
			boolean isUrlEncoder) throws Exception {
		String params = "";
		int i = url.indexOf("?");
		String uri, body;
		if (i == -1) {
			uri = url;
		} else {
			uri = url.substring(0, i);
			params = url.substring(i + 1, url.length());
		}
		if (type == 0) {
			body = get(uri, params, timeout, httpsCerVerify, isUrlEncoder);

		} else {
			body = post(uri, params, timeout, httpsCerVerify, isUrlEncoder);
		}
		return body;
	}

	public static String requestWithParam(String url, int type) throws Exception {
		return requestWithParam(url, type, DEFALUT_TIMEOUT, cerVerify, true);
	}

	public static String requestWithParamNotUrlEncoder(String url, int type) throws Exception {
		return requestWithParam(url, type, DEFALUT_TIMEOUT, cerVerify, false);
	}

	public static String requestWithParam(String url, int type, boolean httpsCerVerify) throws Exception {
		return requestWithParam(url, type, DEFALUT_TIMEOUT, httpsCerVerify, true);
	}

	/**
	 * 根据客户端类型上报
	 * 
	 * @param url
	 * @param type
	 * @param clientType
	 *            pc,iphone,android
	 * @return
	 * @throws Exception
	 */
	public static String requestWithParamAndMobilePhone(String url, int type, String clientType) throws Exception {
		HttpClientUtil.clientType = clientType;
		return requestWithParam(url, type, DEFALUT_TIMEOUT, cerVerify, true);
	}

	/**
	 * url参数转化为List<NameValuePair>
	 * 
	 * @param params
	 *            参数url；
	 * @return
	 */
	private static List<NameValuePair> _parseBasicNameValuePairList(String params) {
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		String[] paramS = params.split("&");
		for (String param : paramS) {
			String[] tempParam = param.split("=");
			if (tempParam.length == 1) {
				paramList.add(new BasicNameValuePair(tempParam[0], ""));
			} else {
				paramList.add(new BasicNameValuePair(tempParam[0], tempParam[1]));
			}
		}
		return paramList;
	}

	public Boolean getCerVerify() {
		return cerVerify;
	}

	public void setCerVerify(Boolean cerVerify) {
		this.cerVerify = cerVerify;
	}

	public static void main(String[] args) throws Exception {
		String url = "https://itunes.apple.com/WebObjects/MZStore.woa/wa/userReviewsRow?id=1278832543&displayable-kind=11&startIndex=0&endIndex=100&sort=1";
		String result = null;
		// url =
		// "http://127.0.0.1:8888/?id=1278832543&displayable-kind=11&startIndex=0&endIndex=100&sort=1";
		result = requestWithParamAndMobilePhone(url, 0, "appStore");
		System.out.println(result);
	}

}