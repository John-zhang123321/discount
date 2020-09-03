package com.zl.setup.service.impl;

import com.zl.common.utils.QcloudCOSUtil;
import com.zl.setup.entity.GoodsImg;
import com.zl.setup.service.GoodsImgService;
import com.zl.setup.dto.add.GoodsImgAddDTO;
import com.zl.setup.dto.query.GoodsImgQueryDTO;
import com.zl.setup.dto.update.GoodsImgUpdateDTO;
import com.zl.setup.mapper.GoodsImgMapper;
import com.zl.setup.bo.GoodsImgBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.zl.common.utils.BeanUtil;

import java.util.List;
import com.github.pagehelper.PageHelper;
import org.springframework.web.multipart.MultipartFile;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Service
public class GoodsImgServiceImpl implements GoodsImgService {

    @Autowired
    private GoodsImgMapper goodsImgMapper;

    @Override
    public Long add(GoodsImgAddDTO goodsImgAddDTO,MultipartFile file) {
        GoodsImg goodsImg = BeanUtil.copyProperties(goodsImgAddDTO, GoodsImg.class,true);
        goodsImg.setUrl(QcloudCOSUtil.upload(file));
        goodsImgMapper.insertSelective(goodsImg);
        return goodsImg.getId();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        goodsImgMapper.deleteByPrimaryKey(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(GoodsImgUpdateDTO goodsImgUpdateDTO) {
        GoodsImg goodsImg = BeanUtil.copyProperties(goodsImgUpdateDTO, GoodsImg.class,false);
        goodsImgMapper.updateByPrimaryKeySelective(goodsImg);
    }


    @Override
    public List<GoodsImgBO> getByParam(GoodsImgQueryDTO goodsImgQueryDTO){
        PageHelper.startPage(goodsImgQueryDTO.getPageNum(), goodsImgQueryDTO.getPageSize());
        return goodsImgMapper.getByParam(goodsImgQueryDTO);
    }

}