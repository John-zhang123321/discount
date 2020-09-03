package com.jkb.common.utils;

import cn.hutool.core.util.StrUtil;
import com.jkb.common.constants.NotifyTemplateEnum;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhangliang on 2019/10/21
 */
@Component
public class MailUtil {

    @Async
    public void sendTemplate(List<String> receivers , String sender, NotifyTemplateEnum mailTemplate) {
        cn.hutool.extra.mail.MailUtil.send(receivers, mailTemplate.getType(), StrUtil.format(mailTemplate.getContent(),sender) , true);
    }

    @Async
    public void sendEverything(List<String> receivers ,String subject, String content) {
        cn.hutool.extra.mail.MailUtil.send(receivers, subject, content , true);
    }
}
