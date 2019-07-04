package com.jin.rabbitmq.demo;

import java.io.Serializable;

public class OrderOperate implements Serializable{

	private static final long serialVersionUID = -8988679901281881577L;
	
	/**
	 * 操作类型
	 * 0:根据appid暂停订单
	 * 1:根据订单编号关闭订单
	 */
	private Integer operateType; 
	
	private Long createTime = System.currentTimeMillis();
	
	private Long appId;
	
	private String orderNo;
	/**
	 * 订单类型
	 *  1：aso
	 *  3:评论
	 */
	private Integer orderType;
	/**
	 * 订单状态
	 */
	private Integer status;
	
	//备注
	private String message;

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}


	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
