package com.zl.user.mapper;

import com.zl.user.dto.query.FeedbackQueryDTO;
import com.zl.user.entity.Feedback;
import com.zl.user.bo.FeedbackBO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import java.util.List;
/**
* @author zhangliang
* @date 2019-12-01
*/
public interface FeedbackMapper extends BaseMapper<Feedback> {

    List<FeedbackBO> getByParam(@Param("feedbackQueryDTO") FeedbackQueryDTO feedbackQueryDTO);
}