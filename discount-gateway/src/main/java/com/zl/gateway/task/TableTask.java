package com.zl.gateway.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.zl.gateway.mapper.VisitLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;

/**
 * @author zhangliang
 * @date 2020/8/2
 */
@Configuration
@EnableScheduling
public class TableTask {

    @Autowired
    private VisitLogMapper visitLogMapper;

    @Scheduled(cron = "0 0 0 28 * ?")
//    @Scheduled(fixedDelay=5000)
    public void configureTasks() {
        DateTime dateTime = DateUtil.nextMonth();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String md = dateTime.toString(dateFormat);
        String tableName = "visit_log_"+md;
        visitLogMapper.createTable(tableName);
    }
}
