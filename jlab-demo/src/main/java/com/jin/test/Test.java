package com.jin.test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jin.httpclient.HttpClientUtil;
import com.jin.java.test;

public class Test {

	private static String[] deviceCodeList = new String[] { "YZ-IPHONE-0054" };
//	private static String url = "http://127.0.0.1:8888/aso/task";
	static int num = 0;

	private static Lock getlock1 = new ReentrantLock();
	private static Lock getlock2 = new ReentrantLock();
	static String url = "http://172.16.10.103:8010/bailing-web-client/app/index";

	public static void main(String[] args) throws Exception {
//		int size = 1000 ;
//		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(size);
//		for(int i = 0 ;i <size;i++){
//			fixedThreadPool.execute(new Runnable() {
//
//				@Override
//				public void run() {
//					while(true){
//						
//						try {
//							String result = HttpClientUtil.post(url, "{}");
//							System.out.println(result);
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
//			});
//		}
		
		System.out.println(getWeek("2018-08-05", "2018-09-14",  "2018-09-09"));
	}

	public static void test() {
		try {
			getlock1.lock();
			System.out.println("111111");
			Thread.sleep(100000);
			getlock1.unlock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void test2() {
		try {
			getlock2.lock();
			System.out.println("222222");
			Thread.sleep(100000);
			getlock2.unlock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 发送请求
	 * 
	 * @throws Exception
	 */
	private static String sendRequst() throws Exception {
		JSONObject json = new JSONObject();
		json.put("deviceCode", deviceCodeList[0]);
		json.put("version", 1);
		json.put("bundleId", "test");
		String result = HttpClientUtil.post(url, json.toJSONString());
		System.out.println(result);
		return result;
	}

	/**
	 * 上报接口
	 * 
	 * @return
	 */
	private static String updateResult(String taskNo, Integer resultCode) throws Exception {

		JSONObject json = new JSONObject();
		json.put("deviceCode", deviceCodeList[0]);
		json.put("taskNo", taskNo);
		json.put("vpnId", 0);
		json.put("idfa", "CCD6E1CD-8C4B-40CB-8A62-4BBC7AFE07D6");
		json.put("validateCodeFlag", 0);
		json.put("resultCode", resultCode);

		JSONObject actionResults = new JSONObject();
		actionResults.put("actionCount", 1);
		json.put("resultList", null);
		json.put("actionResults", null);

		System.out.println(json.toJSONString());
		String result = HttpClientUtil.post(url, json.toJSONString());
		System.out.println(result);
		return result;
	}

	

	//总共有几周 当天是这段时间的第几周
	public static int getWeek(String start,String end,String useDate) throws ParseException {
		 SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		 Date startTime =sdf.parse(start);
		 Date endTime =sdf.parse(end);
		 Date useTime =sdf.parse(useDate);
		if(endTime.getTime()<useTime.getTime() || startTime.getTime()>useTime.getTime()){
			//时间不在时间段内
			return -1;
		}
		Calendar startCal =Calendar.getInstance();
		startCal.setTime(startTime);
		Calendar useCal =Calendar.getInstance();
		useCal.setTime(useTime);
		int startWeek = startCal.get(Calendar.WEEK_OF_YEAR);
		int startDayOfWeek = startCal.get(Calendar.DAY_OF_WEEK);
		int useWeek = useCal.get(Calendar.WEEK_OF_YEAR);
		int useDayOfWeek = useCal.get(Calendar.DAY_OF_WEEK);
		if(startDayOfWeek == 1){
			startWeek -= 1;
		}
		if(useDayOfWeek == 1){
			useWeek -= 1;
		}
		return (useWeek- startWeek)+1;
				
	}
	
	
}
