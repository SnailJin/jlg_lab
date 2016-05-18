package com.jin.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jin.httpclient.HttpClientUtil;

public class JDBC {
	public static final String url = "jdbc:postgresql://192.168.100.58:1921/skypcsuit_log?charSet=utf-8";
	public static final String name = "org.postgresql.Driver";
	public static final String user = "guosong";
	public static final String password = "H2bAJLWdUz5okpcxLzyvwlvgs";

	public static Connection conn = null;
	public static PreparedStatement pst = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String sql="select DISTINCT url from skypcsuit_log.tbl_log_app_cp_report_201605  where app_id in ('20000777','20031972' ) and create_time <'2016-05-18' and status =1";
	    ResultSet ret = null;  
		try {
			// 加载MySql的驱动类
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);// 获取连接
			pst = conn.prepareStatement(sql);// 准备执行语句
			ret =pst.executeQuery();
			List<String> list = new ArrayList<String>();
			List<String> errorlist = new ArrayList<String>();
			int count =0 ;
			 while (ret.next()) {  
				String url =  ret.getString(1);
				list.add(url);
	            }//显示数据  
			 for(String url : list){
				 int status =0;
				 try{
					  status = HttpClientUtil.requestWithParam(url, 0);
					  count++;
					  Thread.sleep(100);
				 }catch(Exception e){
					 
				 }
				 
				 if(status != 200){
					 errorlist.add(url);
				 }
				 System.out.println(status);
			 }
			 System.out.println(errorlist.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}

	}

}
