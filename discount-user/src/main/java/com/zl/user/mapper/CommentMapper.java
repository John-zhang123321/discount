package com.zl.user.mapper;

import com.zl.user.dto.query.CommentQueryDTO;
import com.zl.user.entity.Comment;
import com.zl.user.bo.CommentBO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import java.util.List;
/**
* @author zhangliang
* @date 2019-12-05
*/
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentBO> getByParam(@Param("commentQueryDTO") CommentQueryDTO commentQueryDTO);

    List<CommentBO> getByShopId(@Param("shopId")Long shopId);

    int getCountByPid(@Param("pid")Long pid);

    Double getRateByShopId(@Param("shopId")Long shopId);
}