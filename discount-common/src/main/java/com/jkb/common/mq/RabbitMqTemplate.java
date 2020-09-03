package com.jkb.common.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author zhangliang
 * @date 2020/8/6
 */
@Slf4j
@Component
public class RabbitMqTemplate {

    @Autowired
    private Sender sender;

    /**
     * 发送MQ消息
     * @param exchangeName 交换机名称
     * @param routingKey   路由名称
     * @param message      发送消息体
     */
    public void sendMessage(String exchangeName, String routingKey, MyMessage message) {
        Assert.notNull(message, "message 消息体不能为NULL");
        Assert.notNull(exchangeName, "exchangeName 不能为NULL");
        Assert.notNull(routingKey, "routingKey 不能为NULL");

        sender.sendMessage(exchangeName,routingKey,message);
    }

}
