package com.zl.user.mapper;

import com.zl.user.dto.query.CommentImgQueryDTO;
import com.zl.user.entity.CommentImg;
import com.zl.user.bo.CommentImgBO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import java.util.List;
/**
* @author zhangliang
* @date 2019-12-05
*/
public interface CommentImgMapper extends BaseMapper<CommentImg> {

    List<CommentImgBO> getByParam(@Param("commentImgQueryDTO") CommentImgQueryDTO commentImgQueryDTO);

    void updateByImgIds(@Param("commentImgIdList")List<Long> commentImgIdList,@Param("commentId") Long commentId);

    List<CommentImgBO> getByIds(@Param("commentImgIdList")List<Long> commentImgIdList);
}