package com.zl.gateway.service.impl;

import com.zl.gateway.entity.ExcludePath;
import com.zl.gateway.entity.VisitLog;
import com.zl.gateway.mapper.ExcludePathMapper;
import com.zl.gateway.mapper.VisitLogMapper;
import com.zl.gateway.service.ExcludePathService;
import com.zl.gateway.service.VisitLogService;
import com.zl.gateway.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangliang
 * @date 2020/8/1
 */
@Service
public class VisitLogServiceImpl implements VisitLogService {
    @Autowired
    private VisitLogMapper visitLogMapper;

    @Override
    public void insertLog(String tableName,VisitLog visitLog) {
        visitLogMapper.insertLog(tableName,visitLog);
    }
}
