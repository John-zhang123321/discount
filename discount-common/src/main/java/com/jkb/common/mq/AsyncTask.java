package com.jkb.common.mq;

import cn.hutool.json.JSONUtil;
import com.jkb.common.entity.FailureMsg;
import com.jkb.common.entity.Msg;
import com.jkb.common.entity.MsgReceiver;
import com.jkb.common.mapper.FailureMsgMapper;
import com.jkb.common.mapper.MsgMapper;
import com.jkb.common.mapper.MsgReceiverMapper;
import com.jkb.common.utils.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by zhangliang on 2019/11/3
 */
@Slf4j
@Component
public class AsyncTask {
    @Autowired
    private FailureMsgMapper failureMsgMapper;

    @Autowired
    private MsgMapper msgMapper;

    @Autowired
    private MsgReceiverMapper msgReceiverMapper;

    @Async
    public void saveMsg4Success(MyCorrelationData myCorrelationData) {
        log.info("消息发送成功,消息ID:{},当前线程:{}", myCorrelationData.getId(),Thread.currentThread().getName());

        MyMessage myMessage = myCorrelationData.getMyMessage();
        Msg msg = BeanUtil.copyProperties(myMessage, Msg.class, true);
        msgMapper.insertSelective(msg);

        MsgReceiver msgReceiver = BeanUtil.copyProperties(myCorrelationData, MsgReceiver.class, true);
        msgReceiver.setMsgId(msg.getId());
        for (Long receiver : myMessage.getReceivers()) {
            msgReceiver.setReceiver(receiver);
            msgReceiverMapper.insertSelective(msgReceiver);
        }
    }

    @Async
    public void saveMsg4Failure(MyCorrelationData myCorrelationData) {

        String msgId = myCorrelationData.getId();
        String body = JSONUtil.toJsonStr(myCorrelationData.getMyMessage());

        FailureMsg failureMsg = BeanUtil.copyProperties(myCorrelationData, FailureMsg.class, true);
        failureMsg.setBody(body);
        failureMsg.setMsgId(msgId);

        failureMsgMapper.insertSelective(failureMsg);

        log.warn("MQ消息重发失败，消息入库，消息ID：{}，消息体:{} ,当前线程:{}",msgId ,body,Thread.currentThread().getName());
    }

}
