package com.jin.tool.push.umeng.util;

public class CommonUtils {

	//单播任务类型(type=0-单播 )
	public static String PUSH_TASK_TYPE_UMENG_UNICAST = "unicast";
	
	public static int PUSH_TASK_TYPE_UNICAST = 0; //可以自定义
	
	//列播任务类型(type=1-列播 )
	public static String PUSH_TASK_TYPE_UMENG_LISTCAST = "listcast";
	
	public static int PUSH_TASK_TYPE_LISTCAST = 1;
	
	//文件播任务类型(type=2-文件播)
	public static String PUSH_TASK_TYPE_UMENG_FILECAST = "filecast";
	
	public static int PUSH_TASK_TYPE_FILECAST = 2;
	
	//广播任务类型(type= 3-广播)
	public static String PUSH_TASK_TYPE_UMENG_BROADCAST = "broadcast";
	
	public static int PUSH_TASK_TYPE_BROADCAST = 3;
	
	//组播
	public static String PUSH_TASK_TYPE_UMENG_GROUPCAST = "groupcast";
	
	public static int PUSH_TASK_TYPE_GROUPCAST = 4;
	//消息发送接口
	public static String PUSH_TASK_SEND_URL = "http://msg.umeng.com/api/send";
	
	//任务状态更新接口
	public static String PUSH_TASK_STATUS_URL = "http://msg.umeng.com/api/status";
	
	//任务取消接口
	public static String PUSH_TASK_CANCEL_URL = "http://msg.umeng.com/api/cancel";
		
	//文件上传接口
	public static String PUSH_TASK_UPLOAD_URL = "http://msg.umeng.com/upload";
	
	public static String PUSH_TASK_METHOD = "POST";
	
	//全部发送
	public static int PUSH_TASK_FOR_ALL_CLIENT = 0;
	
	//部分发送
	public static int PUSH_TASK_FOR_PORTION_CLIENT = 1;
	
	
	//组播发送
	public static int PUSH_TASK_FOR_GROUP_CLIENT = 2;
	
	
	//立即发送
	public static int PUSH_TASK_SEND_TIME_NOW = 0;
	
	//定时发送
	public static int PUSH_TASK_SEND_TIME_LATER = 1;
	//发送成功或失败
	public static String PUSH_SUCCESS = "SUCCESS";
	
	public static String PUSH_FAIL = "FAIL";
}
