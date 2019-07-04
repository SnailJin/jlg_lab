package com.jin.tool.push.umeng.entity;
/**
 * 推送状态类
 * @author liguang.jin
 *
 */
public class TaskStatus {
	private String task_id;
	private Integer status; // 消息状态: 0-排队中, 1-发送中，2-发送完成，3-发送失败，4-消息被撤销，
                         // 5-消息过期, 6-筛选结果为空，7-定时任务尚未开始处理
	private Integer total_count;   // 消息总数
	private Integer accept_count;  // 消息受理数
	private Integer sent_count;  // 消息实际发送数
	private Integer open_count;   //打开数
	private Integer dismiss_count;  //忽略数
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTotal_count() {
		return total_count;
	}
	public void setTotal_count(Integer total_count) {
		this.total_count = total_count;
	}
	public Integer getAccept_count() {
		return accept_count;
	}
	public void setAccept_count(Integer accept_count) {
		this.accept_count = accept_count;
	}
	public Integer getSent_count() {
		return sent_count;
	}
	public void setSent_count(Integer sent_count) {
		this.sent_count = sent_count;
	}
	public Integer getOpen_count() {
		return open_count;
	}
	public void setOpen_count(Integer open_count) {
		this.open_count = open_count;
	}
	public Integer getDismiss_count() {
		return dismiss_count;
	}
	public void setDismiss_count(Integer dismiss_count) {
		this.dismiss_count = dismiss_count;
	}
	
	
}
