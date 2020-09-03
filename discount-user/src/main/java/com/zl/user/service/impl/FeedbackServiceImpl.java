package com.zl.user.service.impl;

import com.zl.user.entity.Feedback;
import com.zl.user.service.FeedbackService;
import com.zl.user.dto.add.FeedbackAddDTO;
import com.zl.user.dto.query.FeedbackQueryDTO;
import com.zl.user.dto.update.FeedbackUpdateDTO;
import com.zl.user.mapper.FeedbackMapper;
import com.zl.user.bo.FeedbackBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.zl.common.utils.BeanUtil;
import java.util.List;
import com.github.pagehelper.PageHelper;


/**
* @author zhangliang
* @date 2019-12-01
*/
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public void add(FeedbackAddDTO feedbackAddDTO) {
        Feedback feedback = BeanUtil.copyProperties(feedbackAddDTO, Feedback.class,true);
        feedbackMapper.insertSelective(feedback);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        feedbackMapper.deleteByPrimaryKey(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(FeedbackUpdateDTO feedbackUpdateDTO) {
        Feedback feedback = BeanUtil.copyProperties(feedbackUpdateDTO, Feedback.class,false);
        feedbackMapper.updateByPrimaryKeySelective(feedback);
    }


    @Override
    public List<FeedbackBO> getByParam(FeedbackQueryDTO feedbackQueryDTO){
        PageHelper.startPage(feedbackQueryDTO.getPageNum(), feedbackQueryDTO.getPageSize());
        return feedbackMapper.getByParam(feedbackQueryDTO);
    }

}