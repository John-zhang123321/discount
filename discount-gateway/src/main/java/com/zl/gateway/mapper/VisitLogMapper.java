package com.zl.gateway.mapper;

import com.zl.gateway.entity.VisitLog;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

public interface VisitLogMapper extends BaseMapper<VisitLog> {

    void createTable(@Param("tableName") String tableName);

    void insertLog(@Param("tableName")String tableName, @Param("visitLog")VisitLog visitLog);
}