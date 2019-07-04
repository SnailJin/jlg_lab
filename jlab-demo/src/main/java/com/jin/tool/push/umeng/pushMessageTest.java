package com.jin.tool.push.umeng;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.jin.tool.push.umeng.util.CommonUtils;

public class pushMessageTest {
	
	public static void fileCast(IOSPushMessage push) throws Exception{
		String[] deviceTokens = new String[]{"2c48502fe05c8daef3b329efa1b121e763fea187caf7dad285e1c4eb364a3a4f"};
		push.setType(CommonUtils.PUSH_TASK_TYPE_UMENG_FILECAST);
		JSONObject pushCustomMsg = new JSONObject();
		pushCustomMsg.put("type", "1");
		pushCustomMsg.put("appId", "20001988");
		pushCustomMsg.put("channelNo", "ziyan");
		pushCustomMsg.put("appSource", "8");
		pushCustomMsg.put("msgNo", "111111111111");
		push.setMsgContent("test", pushCustomMsg);
		push.setDescription("wee");
		push.setProduction_mode("true");
		String taskId = push.uploadFile(deviceTokens);
		push.setFile_id(taskId);
		push.handlPolicy(new Date(new Date().getTime()+1000*60*5));
		System.out.println(push.pushMsg());;
	}
	
	public static void unicastOrlistcast(IOSPushMessage push) throws Exception{
		String[] deviceTokens = new String[]{"2c48502fe05c8daef3b329efa1b121e763fea187caf7dad285e1c4eb364a3a4f"};
		push.handleDeviceTokens(deviceTokens);
		JSONObject pushCustomMsg = new JSONObject();
		pushCustomMsg.put("type", "1");
		pushCustomMsg.put("appId", "20001988");
		pushCustomMsg.put("channelNo", "ziyan");
		pushCustomMsg.put("appSource", "8");
		pushCustomMsg.put("msgNo", "111111111111");
		push.setMsgContent("test", pushCustomMsg);
		push.setDescription("wee");
		push.setProduction_mode("true");
		System.out.println(push.pushMsg());;
	}
	
	public static void unicastOrlistcastByPolicy(IOSPushMessage push) throws Exception{
		String[] deviceTokens = new String[]{"2c48502fe05c8daef3b329efa1b121e763fea187caf7dad285e1c4eb364a3a4f"};
		push.handleDeviceTokens(deviceTokens);
		JSONObject pushCustomMsg = new JSONObject();
		pushCustomMsg.put("type", "1");
		pushCustomMsg.put("appId", "20001988");
		pushCustomMsg.put("channelNo", "ziyan");
		pushCustomMsg.put("appSource", "8");
		pushCustomMsg.put("msgNo", "111111111111");
		push.setMsgContent("test", pushCustomMsg);
		push.setDescription("wee");
		push.setProduction_mode("true");
		//push.handlPolicy(new Date(new Date().getTime()+1000*60*5));
		System.out.println(push.pushMsg());;
	}
	
	public static void tets(IOSPushMessage push) throws Exception{
		String[] deviceTokens = new String[]{"2c48502fe05c8daef3b329efa1b121e763fea187caf7dad285e1c4eb364a3a4f"};
		push.handleDeviceTokens(deviceTokens);
		JSONObject pushCustomMsg = new JSONObject();
		pushCustomMsg.put("type", "4");
		pushCustomMsg.put("sectionId", "200687");
		pushCustomMsg.put("msgNo", "111111111111");
		pushCustomMsg.put("sectionName", "苹果用户必读");
		//pushCustomMsg.put("picUrl", "苹果用户必读");
		push.setMsgContent("test", pushCustomMsg);
		push.setDescription("wee");
		push.setProduction_mode("true");
		System.out.println(push.pushMsg());;
	}
	
	public static void main(String[] args) {
		try{
			IOSPushMessage push = new IOSPushMessage();
			push.setAppkey("56725a07e0f55a97ff001ceb");
			push.setAppMasterSecret("ekpms8jkqocncyjesjepvruf99rwchr8");
			tets(push);
//			System.out.println(push.taskStatus("uf31418147634560744000").getStatus());
//			push.cancelTask("uf31418147634560744000");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
