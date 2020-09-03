package com.zl.gateway.mapper;
import org.apache.ibatis.annotations.Param;
import com.zl.gateway.entity.Blacklist;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface BlacklistMapper extends BaseMapper<Blacklist> {
    List<Blacklist> getBySystem(@Param("system") String system);

    void updateById(@Param("blacklistId")Long blacklistId);
}