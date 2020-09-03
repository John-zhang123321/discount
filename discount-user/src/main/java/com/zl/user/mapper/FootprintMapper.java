package com.zl.user.mapper;

import com.zl.user.bo.FootprintBO;
import com.zl.user.bo.FootprintLittleBO;
import com.zl.user.dto.query.FootprintQueryDTO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import com.zl.user.entity.Footprint;

import java.util.List;

public interface FootprintMapper extends BaseMapper<Footprint> {
    Long getByUserIdAndShopId(@Param("userId") long userId,@Param("shopId") Long shopId);

    List<FootprintLittleBO> getByExpired();

    List<FootprintBO> getByParam(@Param("userId") long userId);
}