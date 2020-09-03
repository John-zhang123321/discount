package com.zl.gateway.mapper;

import com.zl.gateway.entity.ExcludePath;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface ExcludePathMapper extends BaseMapper<ExcludePath> {
    List<ExcludePath> getBySystem(@Param("system") String system);
}