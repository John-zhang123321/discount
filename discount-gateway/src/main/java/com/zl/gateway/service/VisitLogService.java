package com.zl.gateway.service;

import com.zl.gateway.entity.Blacklist;
import com.zl.gateway.entity.VisitLog;

import java.util.List;

/**
 * @author zhangliang
 * @date 2020/8/1
 */
public interface VisitLogService {
    void insertLog(String tableName,VisitLog visitLog);
}
