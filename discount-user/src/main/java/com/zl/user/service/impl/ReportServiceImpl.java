package com.zl.user.service.impl;

import com.zl.user.entity.Report;
import com.zl.user.mapper.ReportMapper;
import com.zl.user.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangliang on 2019/5/24
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportMapper reportMapper;


}
