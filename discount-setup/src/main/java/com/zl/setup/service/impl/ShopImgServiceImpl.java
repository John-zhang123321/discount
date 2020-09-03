package com.zl.setup.service.impl;

import com.zl.common.utils.QcloudCOSUtil;
import com.zl.setup.entity.ShopImg;
import com.zl.setup.dto.add.ShopImgAddDTO;
import com.zl.setup.dto.query.ShopImgQueryDTO;
import com.zl.setup.dto.update.ShopImgUpdateDTO;
import com.zl.setup.mapper.ShopImgMapper;
import com.zl.setup.bo.ShopImgBO;
import com.zl.setup.service.ShopImgService;
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
public class ShopImgServiceImpl implements ShopImgService {

    @Autowired
    private ShopImgMapper shopImgMapper;

    @Override
    public Long add(ShopImgAddDTO shopImgAddDTO,MultipartFile file) {
        ShopImg shopImg = BeanUtil.copyProperties(shopImgAddDTO, ShopImg.class,true);
        shopImg.setUrl(QcloudCOSUtil.upload(file));
        shopImgMapper.insertSelective(shopImg);
        return shopImg.getId();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        shopImgMapper.deleteByPrimaryKey(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(ShopImgUpdateDTO shopImgUpdateDTO) {
        ShopImg shopImg = BeanUtil.copyProperties(shopImgUpdateDTO, ShopImg.class,false);
        shopImgMapper.updateByPrimaryKeySelective(shopImg);
    }


    @Override
    public List<ShopImgBO> getByParam(ShopImgQueryDTO shopImgQueryDTO){
        PageHelper.startPage(shopImgQueryDTO.getPageNum(), shopImgQueryDTO.getPageSize());
        return shopImgMapper.getByParam(shopImgQueryDTO);
    }

}