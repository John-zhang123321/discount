package com.zl.user.mapper;

import com.zl.user.bo.FollowLittleBO;
import com.zl.user.dto.query.FollowQueryDTO;
import com.zl.user.entity.Follow;
import com.zl.user.bo.FollowBO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import java.util.List;
import java.util.Map;

/**
* @author zhangliang
* @date 2019-12-01
*/
public interface FollowMapper extends BaseMapper<Follow> {

    List<FollowLittleBO> getByParam(@Param("followQueryDTO") FollowQueryDTO followQueryDTO);

    List<String> getUserIdsByShopId(@Param("shopId")Long shopId);

    void deleteByShopIAndUserId(@Param("shopId")Long shopId,@Param("userId")long userId);

    long getByShopIAndUserId(@Param("userId")long userId, @Param("shopId")Long shopId);

    boolean getFollowStatusByShopIdAndUserId(@Param("shopId")long shopId, @Param("userId")long userId);
}