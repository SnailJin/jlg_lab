package com.jin.tool.push.umeng.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 友盟异常
 * @author liguang.jin
 *
 */
public class UMengeException extends Exception {

	private static final long serialVersionUID = -7367194845972447694L;
	private int errorCode;
	private static Map<Integer,String> errorCodeMap = new HashMap<Integer, String>(){{
		put(1000,"请求参数没有appkey");
		put(1001,"请求参数没有payload");
		put(1002,"请求参数payload中没有body");
		put(1003,"display_type为message时，请求参数没有custom");
		put(1004,"请求参数没有display_type");
		put(1005,"img url格式不对，请以https或者http开始");
		put(1006,"sound url格式不对，请以https或者http开始");
		put(1007,"url格式不对，请以https或者http开始");
		put(1008,"display_type为notification时，body中ticker不能为空");
		put(1009,"display_type为notification时，body中title不能为空");
		put(1010,"display_type为notification时，body中text不能为空");
		put(1011,"play_vibrate的值只能为true或者false");
		put(1012,"play_lights的值只能为true或者false");
		put(1013,"play_sound的值只能为true或者false");
		put(1014,"task-id没有找到");
		put(1015,"请求参数中没有device_tokens");
		put(1016,"请求参数没有type");
		put(1017,"production_mode只能为true或者false");
		put(1018,"appkey错误：指定的appkey尚未开通推送服务");
		put(1019,"display_type填写错误");
		put(1020,"应用组中尚未添加应用");
		put(2000,"该应用已被禁用");
		put(2001,"过期时间必须大于当前时间");
		put(2002,"定时发送时间必须大于当前时间");
		put(2003,"过期时间必须大于定时发送时间");
		put(2004,"IP白名单尚未添加, 请到网站后台添加您的服务器IP白名单。");
		put(2005,"该消息不存在");
		put(2006,"validation token错误");
		put(2007,"未对请求进行签名");
		put(2008,"json解析错误");
		put(2009,"请填写alias或者file_id");
		put(2010,"与alias对应的device_tokens为空");
		put(2011,"alias个数已超过50");
		put(2012,"此appkey今天的广播数已超过3次");
		put(2013,"消息还在排队，请稍候再查询");
		put(2014,"消息取消失败，请稍候再试");
		put(2015,"device_tokens个数已超过50");
		put(2016,"请填写filter");
		put(2017,"添加tag失败");
		put(2018,"请填写file_id");
		put(2019,"与此file_id对应的文件不存在");
		put(2020,"服务正在升级中，请稍候再试");
		put(2021,"appkey不存在");
		put(2022,"payload长度过长");
		put(2023,"文件上传失败，请重试");
		put(2024,"限速值必须为正整数");
		put(2025,"aps字段不能为空");
		put(2026,"1分钟内发送任务次数超出3次");
		put(2027,"签名不正确");
		put(2028,"时间戳已过期");
		put(2029,"content内容不能为空");
		put(2030,"launch_from/not_launch_from条件中的日期须小于发送日期");
		put(2031,"filter格式不正确");
		put(2032,"未上传生产证书，请到Web后台上传");
		put(2033,"未上传开发证书，请到Web后台上传");
		put(2034,"证书已过期");
		put(2035,"定时任务证书过期");
		put(2036,"时间戳格式错误");
		put(2038,"文件上传失败");
		put(2039,"时间格式必须是yyyy-MM-dd HH:mm:ss");
		put(2040,"过期时间不能超过7天");
		put(3000,"数据库错误");
		put(3001,"数据库错误");
		put(3002,"数据库错误");
		put(3003,"数据库错误");
		put(3004,"数据库错误");
		put(0,"系统错误");
		put(1,"系统忙");
		put(2,"操作失败");
		put(3,"appkey格式错误");
		put(4,"消息类型格式错误");
		put(5,"msg格式错误");
		put(6,"body格式错误");
		put(7,"deliverPolicy格式错误");
		put(8,"失效时间格式错误");
		put(9,"单个服务器队列已满");
		put(4010,"设备号格式错误");
		put(4011,"消息扩展字段无效");
		put(4012,"没有权限访问");
		put(4013,"异步发送消息失败");
		put(4014,"appkey和device_tokens不对应");
		put(4015,"没有找到应用信息");
		put(4016,"文件编码有误");
		put(4017,"文件类型有误");
		put(4018,"文件远程地址有误");
		put(4019,"文件描述信息有误");
		put(4020,"device_token有误(注意，友盟的device_token是严格的44位字符串)");
		put(4021,"HSF异步服务超时");
		put(4022,"appkey已经注册");
		put(4023,"服务器网络异常");
		put(4024,"非法访问");
		put(4025,"device-token全部失败");
		put(4026,"device-token部分失败");
		put(4027,"拉取文件失败");
		put(5000,"device_token错误");
		put(5001,"证书不存在");
		put(5002,"p,d是umeng保留字段");
		put(5003,"alert字段不能为空");
		put(5004,"alert只能是String类型");
		put(5005,"device_token格式错误");
		put(5006,"创建socket错误");
		put(5007,"certificate_revoked错误");
		put(5008,"certificate_unkown错误");
		put(5009,"handshake_failure错误");
	}};
	
	public UMengeException(){
		super();
	}
	
	public UMengeException(int errorCode) {
		super("erro code:"+errorCode+",描述:"+errorCodeMap.get(errorCode));
		this.errorCode=errorCode;
	}
	
	public static void main(String[] args) {
		UMengeException test = new UMengeException(1);
		test.printStackTrace();
	}
	
}
