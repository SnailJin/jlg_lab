package com.jin.rabbitmq.demo;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.core.MessageProperties;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

	public final static String QUEUE_NAME = "rabbitMQ.test";

	public static void main(String[] args) throws IOException, TimeoutException {
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		// 设置RabbitMQ相关信息
		factory.setHost(RabbitMqConfig.host);
		factory.setPort(RabbitMqConfig.port);
		factory.setUsername(RabbitMqConfig.username);
		factory.setPassword(RabbitMqConfig.password);
		// factory.setUsername("lp");
		// factory.setPassword("");
		// factory.setPort(2088);
		// 创建一个新的连接
		Connection connection = factory.newConnection();
		// 创建一个通道
		Channel channel = connection.createChannel();
		// 声明一个队列 channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		OrderOperate tmp = new OrderOperate();
		tmp.setOrderNo("ASO_ORDER_20180425_3");
		tmp.setOperateType(1);
		tmp.setOrderType(1);
		tmp.setStatus(5);
		channel.basicPublish("", "timerQueue",null, JSONObject.toJSONString(tmp).getBytes());
		// 关闭通道和连接
		channel.close();
		connection.close();
		System.out.println("susccess");
	}
}
