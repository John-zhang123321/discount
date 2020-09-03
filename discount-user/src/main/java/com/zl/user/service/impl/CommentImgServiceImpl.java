package com.zl.user.service.impl;

import com.zl.user.entity.CommentImg;
import com.zl.user.service.CommentImgService;
import com.zl.user.dto.add.CommentImgAddDTO;
import com.zl.user.dto.query.CommentImgQueryDTO;
import com.zl.user.dto.update.CommentImgUpdateDTO;
import com.zl.user.mapper.CommentImgMapper;
import com.zl.user.bo.CommentImgBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.zl.common.utils.BeanUtil;
import java.util.List;
import com.github.pagehelper.PageHelper;


/**
* @author zhangliang
* @date 2019-12-05
*/
@Service
public class CommentImgServiceImpl implements CommentImgService {

    @Autowired
    private CommentImgMapper commentImgMapper;

    @Override
    public void add(CommentImgAddDTO commentImgAddDTO) {
        CommentImg commentImg = BeanUtil.copyProperties(commentImgAddDTO, CommentImg.class,true);
        commentImgMapper.insertSelective(commentImg);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        commentImgMapper.deleteByPrimaryKey(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(CommentImgUpdateDTO commentImgUpdateDTO) {
        CommentImg commentImg = BeanUtil.copyProperties(commentImgUpdateDTO, CommentImg.class,false);
        commentImgMapper.updateByPrimaryKeySelective(commentImg);
    }


    @Override
    public List<CommentImgBO> getByParam(CommentImgQueryDTO commentImgQueryDTO){
        PageHelper.startPage(commentImgQueryDTO.getPageNum(), commentImgQueryDTO.getPageSize());
        return commentImgMapper.getByParam(commentImgQueryDTO);
    }

}