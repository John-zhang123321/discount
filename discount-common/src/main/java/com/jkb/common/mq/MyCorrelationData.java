package com.jkb.common.mq;

import lombok.Data;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * 发送消息的相关数据
 * Created by zhangliang on 2019/11/3
 */
@Data
public class MyCorrelationData extends CorrelationData {
	/**
	 * 消息体
	 */
	private MyMessage myMessage;

	/**
	 * 交换机名称
	 */
	private String exchange;

	/**
	 * 路由key
	 */
	private String routingKey;

	/**
	 * 重试次数
	 */
	private int retry = 0;

	public MyCorrelationData() {
		super();
	}

	public MyCorrelationData(String id) {
		super(id);
	}

	public MyCorrelationData(String id, MyMessage myMessage) {
		this(id);
		this.myMessage = myMessage;
	}
}
