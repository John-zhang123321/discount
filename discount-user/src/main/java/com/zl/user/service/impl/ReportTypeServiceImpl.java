package com.zl.user.service.impl;

import com.zl.user.entity.ReportType;
import com.zl.user.mapper.PostMapper;
import com.zl.user.mapper.ReportTypeMapper;
import com.zl.user.service.ReportTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangliang on 2019/5/24
 */
@Service
public class ReportTypeServiceImpl implements ReportTypeService {

    @Autowired
    ReportTypeMapper reportTypeMapper;


}
