package com.jkb.common.mq;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Rabbit 发送消息
 * @author zhangliang
 */
@Slf4j
@Component
public class Sender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback, InitializingBean {

    @Value("${mq.retry.count:3}")
    private int mqRetryCount;
    /**
     * Rabbit MQ 客户端
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AsyncTask asyncTask;

    /**
     * 发送MQ消息
     *
     * @param exchangeName 交换机名称
     * @param routingKey   路由名称
     * @param message      发送消息体
     */
    protected void sendMessage(String exchangeName, String routingKey, MyMessage message) {
        // 获取CorrelationData对象
        MyCorrelationData myCorrelationData = createCorrelationData(message);
        myCorrelationData.setExchange(exchangeName);
        myCorrelationData.setRoutingKey(routingKey);
        myCorrelationData.setMyMessage(message);

        log.info("发送MQ消息，消息ID：{}，消息体:{}, exchangeName:{}, routingKey:{}",
                myCorrelationData.getId(), JSONUtil.toJsonStr(message), exchangeName, routingKey);
        // 发送消息
        this.convertAndSend(exchangeName, routingKey, message, myCorrelationData);
    }

    /**
     * 用于实现消息发送到RabbitMQ交换器后接收ack回调。
     * 如果消息发送确认失败就进行重试。
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // 消息回调确认失败处理
        MyCorrelationData myCorrelationData = (MyCorrelationData) correlationData;
        if (!ack) {
            //消息发送失败,就进行重试，重试过后还不能成功就记录到数据库
            if (myCorrelationData.getRetry() < mqRetryCount) {
                log.info("MQ消息发送失败，消息重发，消息ID：{}，重发次数：{}，消息体:{}", myCorrelationData.getId(),
                        myCorrelationData.getRetry(), JSONUtil.toJsonStr(myCorrelationData.getMyMessage()));

                // 将重试次数加一
                myCorrelationData.setRetry(myCorrelationData.getRetry() + 1);

                // 重发发消息
                this.convertAndSend(myCorrelationData.getExchange(), myCorrelationData.getRoutingKey(),
                        myCorrelationData.getMyMessage(), myCorrelationData);
            } else {
                //消息重试发送失败,将消息放到数据库等待补发
                asyncTask.saveMsg4Failure(myCorrelationData);
            }
        } else {
            asyncTask.saveMsg4Success(myCorrelationData);
        }
    }



    /**
     * 用于实现消息发送到RabbitMQ交换器，但无相应队列与交换器绑定时的回调。
     * 在脑裂的情况下会出现这种情况
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("MQ消息发送失败，replyCode:{}, replyText:{}，exchange:{}，routingKey:{}，消息体:{}",
                replyCode, replyText, exchange, routingKey, JSONUtil.toJsonStr(message.getBody()));

        MyCorrelationData myCorrelationData = JSONUtil.toBean(JSONUtil.toJsonStr(message.getBody()), MyCorrelationData.class);
        asyncTask.saveMsg4Failure(myCorrelationData);
    }

    /**
     * 消息相关数据（消息ID）
     *
     * @param message
     * @return
     */
    private MyCorrelationData createCorrelationData(MyMessage message) {
        return new MyCorrelationData(UUID.randomUUID().toString(), message);
    }

    /**
     * 发送消息
     *
     * @param exchange        交换机名称
     * @param routingKey      路由key
     * @param message         消息内容
     * @param myCorrelationData 消息相关数据（消息ID）
     * @throws AmqpException
     */
    private void convertAndSend(String exchange, String routingKey, final Object message, MyCorrelationData myCorrelationData) throws AmqpException {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message, myCorrelationData);
        } catch (Exception e) {
            log.error("MQ消息发送异常，消息ID：{}，消息体:{}, exchangeName:{}, routingKey:{}",
                    myCorrelationData.getId(), JSONUtil.toJsonStr(message), exchange, routingKey, e);

            asyncTask.saveMsg4Failure(myCorrelationData);
        }
    }

    @Override
    public void afterPropertiesSet(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }
}
