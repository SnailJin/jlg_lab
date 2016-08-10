package com.jin.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jin.httpclient.HttpClientUtil;

public class JDBC {
	public static final String name = "org.postgresql.Driver";
	public static final String url = "jdbc:postgresql://192.168.100.58:1921/skypcsuit_log?charSet=utf-8";
	public static final String url_1 = "jdbc:postgresql://192.168.100.54:1921/skypcsuit?charSet=utf-8";
	public static final String user = "guosong";
	public static final String password = "H2bAJLWdUz5okpcxLzyvwlvgs";
	
//	public static final String url = "jdbc:postgresql://172.16.3.224:5432/skypcsuit_test?charSet=utf-8";
//	public static final String user = "skypcsuit_test";
//	public static final String password = "skypcsuit_test";

	public static Connection conn_log = null;
	public static Connection conn = null;
	public static PreparedStatement pst = null;

	/**
	 * 获得连接
	 * @throws Exception
	 */
	public static void getConnection() throws Exception {
		// 加载MySql的驱动类
		Class.forName(name);
		conn_log = DriverManager.getConnection(url, user, password);// 获取连接
		conn = DriverManager.getConnection(url_1, user, password);// 获取连接
	}
	
	/**
	 * 运行请求
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public static ResultSet executeQuery(String sql) throws Exception {
		JDBC.getConnection();
		JDBC.pst = JDBC.conn_log.prepareStatement(sql);// 准备执行语句
		ResultSet ret = JDBC.pst.executeQuery();
		JDBC.conn.close();
		return ret;
	}
	
	public static void QQRead() throws Exception{
		Map<String, Integer> map = new HashMap<String, Integer>();
		int i = 13;
		String time = "2016-07-0";
		String sql;
		ResultSet ret = null;
		String body;
		for (i = 1; i < 2; i++) {
			int count = 0;
			int count1=0;
			sql = "select DISTINCT idfa from skypcsuit_log.tbl_log_app_cp_report_201607  WHERE app_id ='20000457' and to_char(create_time,'yyyy-mm-dd') = '"
					+ time + i + "' and status =0";
			System.out.println(sql);
			pst = conn_log .prepareStatement(sql);// 准备执行语句
			ret = pst.executeQuery();
			while (ret.next()) {
				count1++;
				String idfa = ret.getString(1);
				String url1 ="http://ios.reader.qq.com/common/idfa?appid=487608658&idfa="+idfa+"&channel=MPTG";
				System.out.println(url1);
				body = HttpClientUtil.requestWithParam(url1, 0);
				Thread.sleep(500);
				JSONObject json =(JSONObject) JSONObject.parse(body);
				System.out.println(json.toString());
				if(json.getInteger(idfa) ==1){
					count++;
				}
				Thread.sleep(10);
			}// 显示数据
			map.put(time + i, count);
			System.out.println(time + i+":"+count1);
			// System.out.println(sql);
		}
		System.out.println(map.toString());
	}
	
	
	public static void UC() throws Exception{
		Map<String, Integer> map = new HashMap<String, Integer>();
		String sql;
		ResultSet ret = null;
		String body;
		int i =0;
			int count = 0;
			int count1=0;
			sql = "select *  from skypcsuit_log.tbl_log_app_cp_report_201606  where  app_id ='20017627' and to_char(create_time,'yyyy-mm-dd') >= '2016-06-01' and status =1";
			pst = conn_log .prepareStatement(sql);// 准备执行语句
			ret = pst.executeQuery();
			while (ret.next()) {
				count1++;
				String url = ret.getString("url");
				Date now  =new Date();
				String sign = getUrl("app_id=834878585&idfa=${idfa}&source=mopinbc6016d233de3477c13cacdbf87040ef", now,ret.getString("idfa"));
				sign=Encrypt.MD5(sign);
				url = url.replace("${sign}", sign);
				String url1 =getUrl(url,now,ret.getString("idfa"));
				body = HttpClientUtil.requestWithParam(url1, 0);
				try{
					JSONObject json =(JSONObject) JSONObject.parse(body);
					System.out.println(ret.getString("idfa"));
					if(json.getInteger("resultCode") == 2000 ){
						count++;
					}
				}catch(Exception e){
					
				}
				
				Thread.sleep(800);
			}// 显示数据
			System.out.println(count+":"+count1);
	}
	
	private static String getUrl(String url,Date nowTime,String idfa) {
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat timeMs = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.sss");
		String timeS = time.format(nowTime);
		String timeMsS = timeMs.format(nowTime);
		url = url.replace("${idfa}", idfa);
		url = url.replace("${timeStamp}", System.currentTimeMillis() + "");
		url = url.replace("${time}", timeS);
		url = url.replace("${timeMs}", timeMsS);
		return url;
	}
	
	public static void  XY() {
		String sql = "select  app_id ,count(DISTINCT IDFA ) as count from  skypcsuit_log.tbl_log_app_cp_report_201607 where "+
"app_id in ('20040732','20000587','20000314','20007995','20000332','20003917','20000063','20000638','20000918','20013853','20004841','20009497','20003226') "+
 "and to_char(create_time,'yyyy-mm-dd')  = '2016-07-15'  and status =0 GROUP by app_id ORDER BY count(DISTINCT IDFA ) desc";
		
		String sql_en= "SELECT * from skypcsuit.tbl_ios_app_genuine_info where deleted =0;";
		ResultSet ret = null;
		List<HashMap<String, String>> genuineList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		try {
			pst = conn.prepareStatement(sql_en);
			ret = pst.executeQuery();
			while (ret.next()) {
				HashMap<String, String> temp = new HashMap<String, String>();
				temp.put("app_id", ret.getString("app_id")); 
				temp.put("name", ret.getString("app_name"));
				genuineList.add(temp);
			}	
			ret.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			pst = conn_log .prepareStatement(sql);
			ret = pst.executeQuery();
			while (ret.next()) {
				HashMap<String, String> temp = new HashMap<String, String>();
				temp.put("app_id", ret.getString("app_id"));
				temp.put("count", ret.getString("count"));
				list.add(temp);
			}	
			ret.close();
			conn_log .close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(HashMap<String, String> temp : list){
			for(HashMap<String, String> genuineMap : genuineList){
				if(temp.get("app_id").equals(genuineMap.get("app_id"))){
					temp.put("name", genuineMap.get("name"));
					break;
				}
			}
		}
		for(HashMap<String, String> temp : list){
			System.out.println(temp.get("app_id")+"   "+temp.get("name")+"   "+temp.get("count")+"   ");
		}
		
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			getConnection();
			XY();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

	}

}
