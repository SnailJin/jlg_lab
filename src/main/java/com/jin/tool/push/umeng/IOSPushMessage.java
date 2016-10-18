package com.jin.tool.push.umeng;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jin.httpclient.HttpClientUtil;
import com.jin.java.Encrypt;
import com.jin.tool.push.umeng.entity.TaskStatus;
import com.jin.tool.push.umeng.util.CommonUtils;
import com.jin.tool.push.umeng.util.UMengeException;

/**
 * 推送消息类
 * @author liguang.jin
 *
 */
public class IOSPushMessage {
	private String appkey;  // 必填 应用唯一标识
	private String timestamp;  // 必填 时间戳，10位或者13位均可，时间戳有效期为10分钟
	private String type;  // 必填 消息发送类型,其值可以为:
                           //unicast-单播
                          // listcast-列播(要求不超过500个device_token)
                         //filecast-文件播(多个device_token可通过文件形式批量发送）
                         //broadcast-广播
                         //groupcast-组播 (按照filter条件筛选特定用户群, 具体请参照filter参数)
                         //customizedcast(通过开发者自有的alias进行推送),  包括以下两种case:
                         // - alias: 对单个或者多个alias进行推送
                         //- file_id: 将alias存放到文件后，根据file_id来推送
	private String device_tokens; // 可选 设备唯一表示
	                              //当type=unicast时,必填, 表示指定的单个设备  ;    当type=listcast时,必填,要求不超过500个,;多个device_token以英文逗号间隔
	private String alias_type;  // 可选 当type=customizedcast时，必填，alias的类型, 
	private String alias;    // 可选 当type=customizedcast时, 开发者填写自己的alias。 要求不超过50个alias,多个alias以英文逗号间隔。
	private String file_id;   // 可选 当type=filecast时，file内容为多条device_token,    device_token以回车符分隔     当type=customizedcast时，file内容为多条alias，  alias以回车符分隔，注意同一个文件内的alias所对应, 的alias_type必须和接口参数alias_type一致。 注意，使用文件播前需要先调用文件上传接口获取file_id, 
	private Object filter;     // 可选 终端用户筛选条件,如用户标签、地域、应用版本以及渠道等,
	private Object payload;   // 必填 消息内容(iOS最大为2012B), 包含参数说明如下(JSON格式):
	private Object policy;   // 可选 发送策略
	private String production_mode ="false";  //"true/false 可选 正式/测试模式。测试模式下，广播/组播只会将消息发给测试设
	private String description ;  // 可选 发送消息描述，建议填写。
	private String thirdparty_id;  // 可选 开发者自定义消息标识ID, 开发者可以为同一批发送的消息提供 同一个thirdparty_id, 便于后期合并统计数据。  
	
	private String appMasterSecret; //秘钥 只做加密使用,不做参数

	/**
	 * 添加消息内容
	 * @param Content 内容
	 * 格式如下
	 *  "aps":                 // 必填 严格按照APNs定义来填写
    {
        "alert": "xx"          // 必填    内容               
        "badge": xx,           // 可选        图标红圈数字
        "sound": "xx",         // 可选         声音
        "content-available":xx // 可选       
        "category": "xx",      // 可选, 注意: ios8才支持该字段。
    },
    "key1":"value1",       // 可选 用户自定义内容, "d","p"为友盟保留字段，
                                  key不可以是"d","p"，可以有多对
	 */
	public void setMsgContent(String Content,JSONObject pushCustomMsg){
		JSONObject msgContent =  new JSONObject();
		//添加推送aps
		msgContent.put("aps",getAps(Content));
		//加入自定义内容
		for(String key : pushCustomMsg.keySet()){
			msgContent.put(key, pushCustomMsg.getString(key));
		}
		this.payload = msgContent;
	}
	/**
	 * 添加推送aps
	 * @param Content
	 */
	public JSONObject getAps(String Content){
		JSONObject apsJson = new JSONObject();
		apsJson.put("alert", Content);
		apsJson.put("sound", "default");
		return apsJson;
	}
	
	/**
	 * 处理deviceTokens
	 * @param deviceTokens
	 */
	public void handleDeviceTokens(String[] deviceTokens) throws Exception{
		if(deviceTokens.length == 1){
			this.type = CommonUtils.PUSH_TASK_TYPE_UMENG_UNICAST;
			this.setDevice_tokens(deviceTokens[0]);
		}else if(deviceTokens.length <=500){
			this.type = CommonUtils.PUSH_TASK_TYPE_UMENG_LISTCAST;
			StringBuffer deviceTokenS = new StringBuffer();
			for(String deviceToken : deviceTokens)
				deviceTokenS.append(deviceToken).append(",");
			this.device_tokens = deviceTokenS.substring(0, deviceTokenS.length()-1);
		}else{
			this.type = CommonUtils.PUSH_TASK_TYPE_UMENG_FILECAST;
			String  fileId = uploadFile(deviceTokens);
			this.file_id = fileId;
		}

	}
	
	/**
	 * 发送策略
	 * 格式如下
	 *  "policy":                // 可选 发送策略
        {
           "start_time":"xx",   // 可选 定时发送时间，默认为立即发送。发送时间不能小于当前时间。
                                        格式: "YYYY-MM-DD HH:mm:ss"。 
                                        注意, start_time只对任务生效。
           "expire_time":"xx",  // 可选 消息过期时间,其值不可小于发送时间,
                                        默认为3天后过期。格式同start_time
           "max_send_num": xx   // 可选 发送限速，每秒发送的最大条数。
                                        开发者发送的消息如果有请求自己服务器的资源，可以考虑此参数。
          },
	 * @param startTime  定时发送时间
	 * @param expire_time 消息过期时间,其值不可小于发送时间,
	 * @param maxSendNum  发送限速，每秒发送的最大条数。
	 */
	public void handlPolicy(Date startTime,Date expireTime,Integer maxSendNum) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(startTime == null)
			throw new Exception("handlPolicy startTime 不能为空!");
		JSONObject policyJson = new JSONObject();
		policyJson.put("start_time", df.format(startTime));
		if(expireTime != null)
			policyJson.put("expire_time", df.format(expireTime));
		if(maxSendNum != null)
			policyJson.put("max_send_num", maxSendNum);
		this.policy = policyJson;
	}
	
	public void handlPolicy(Date startTime)  throws Exception{
		this.handlPolicy(startTime,null,null);
	}
	
	
	/**
	 * 文件上传接口支持两种应用场景:
                a. 发送类型为"filecast"的时候, 开发者批量上传device_token;
                b. 发送类型为"customizedcast"时, 开发者批量上传alias。
                上传文件后获取file_id, 从而可以实现通过文件id来进行消息批量推送的目的。
     josn格式:
     {
        "appkey":"xx",     // 必填
        "timestamp":"xx",  // 必填
        "content":"xx"     // 必填 文件内容,多个device_token/alias请用回车符"\n"分隔。
      }
	 * @param deviceTokens
	 * @return
	 */
	public String uploadFile(String[] deviceTokens) throws Exception{
		//回车拼装 deviceTokenS
		StringBuffer deviceTokenS = new StringBuffer();
		for(String deviceToken : deviceTokens)
			deviceTokenS.append(deviceToken).append("\n");
		JSONObject paramJson = new JSONObject();
		if(this.appkey == null){
			throw new Exception("调用uploadFile前先初始化 this.appkey");
		}
		paramJson.put("appkey", this.appkey);
		paramJson.put("timestamp", new Date().getTime());
		paramJson.put("content", deviceTokenS.toString());
		String data = paramJson.toJSONString();
		System.out.println(data);
		String result = requestWithSign(CommonUtils.PUSH_TASK_UPLOAD_URL,data);
		JSONObject json = JSONObject.parseObject(result);
		if(json.getString("ret").equals(CommonUtils.PUSH_FAIL)){
			throw new UMengeException(json.getJSONObject("data").getIntValue("error_code"));
		}
		return json.getJSONObject("data").getString("file_id");
	}
	
	/**
	 * 发送推送消息请求
	 * @return
	 * @throws Exception
	 */
	public String pushMsg() throws Exception{
		this.timestamp = new Date().getTime()+"";
		String data = JSONObject.toJSONString(this);
		System.out.println(data);
		String result = requestWithSign(CommonUtils.PUSH_TASK_SEND_URL,data);
		JSONObject resultJson = JSONObject.parseObject(result);
		if(!resultJson.getString("ret").equals(CommonUtils.PUSH_SUCCESS))
			throw new UMengeException(resultJson.getJSONObject("data").getIntValue("error_code"));
		return resultJson.getString("data");
		
	}
	/**
	 * 请求参数拼装
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String requestWithSign(String url,String data) throws Exception{
		String sign = Encrypt.MD5("POST"+url+data+this.appMasterSecret).toLowerCase();
		return HttpClientUtil.post(url+"?sign="+sign,data);
	}
	
	/**
	 * 取消任务
	 * 格式:
	   {
  	   "appkey":"xx",     // 必填
  	   "timestamp":"xx",  // 必填
  	   "task_id":"xx"     // 必填 消息发送时，从返回消息中获取的task_id
	   }
	 * 
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public String cancelTask(String taskId) throws Exception{
		JSONObject json = new JSONObject();
		json.put("appkey", this.appkey);
		json.put("timestamp",new Date().getTime());
		json.put("task_id", taskId);
		String result = requestWithSign(CommonUtils.PUSH_TASK_CANCEL_URL,json.toJSONString());
		JSONObject resultJson = JSONObject.parseObject(result);
		if(!resultJson.getString("ret").equals(CommonUtils.PUSH_SUCCESS))
			throw new UMengeException(resultJson.getJSONObject("data").getIntValue("error_code"));
		return resultJson.getJSONObject("data").getString("task_id");
	}
	
	/**
	 * 任务状态
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public TaskStatus taskStatus(String taskId) throws Exception{
		JSONObject json = new JSONObject();
		json.put("appkey", this.appkey);
		json.put("timestamp",new Date().getTime());
		json.put("task_id", taskId);
		String result = requestWithSign(CommonUtils.PUSH_TASK_STATUS_URL,json.toJSONString());
		JSONObject resultJson = JSONObject.parseObject(result);
		if(!resultJson.getString("ret").equals(CommonUtils.PUSH_SUCCESS))
			throw new UMengeException(resultJson.getJSONObject("data").getIntValue("error_code"));
		TaskStatus taskStatus =JSONObject.parseObject(resultJson.getString("data"), TaskStatus.class);
		return taskStatus;
	}
	
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDevice_tokens() {
		return device_tokens;
	}
	public void setDevice_tokens(String device_tokens) {
		this.device_tokens = device_tokens;
	}
	public String getAlias_type() {
		return alias_type;
	}
	public void setAlias_type(String alias_type) {
		this.alias_type = alias_type;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public Object getFilter() {
		return filter;
	}
	public void setFilter(Object filter) {
		this.filter = filter;
	}
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
	public Object getPolicy() {
		return policy;
	}
	public void setPolicy(Object policy) {
		this.policy = policy;
	}
	public String getProduction_mode() {
		return production_mode;
	}
	public void setProduction_mode(String production_mode) {
		this.production_mode = production_mode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getThirdparty_id() {
		return thirdparty_id;
	}
	public void setThirdparty_id(String thirdparty_id) {
		this.thirdparty_id = thirdparty_id;
	}
	
	public void setAppMasterSecret(String appMasterSecret) {
		this.appMasterSecret = appMasterSecret;
	}
	
}
