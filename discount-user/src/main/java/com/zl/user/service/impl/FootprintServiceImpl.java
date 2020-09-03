package com.zl.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.zl.common.constants.RedisKeyConstant;
import com.zl.common.entity.MyPageInfo;
import com.zl.common.utils.BeanUtil;
import com.zl.common.utils.RedisUtil;
import com.zl.common.utils.RequestResponseUtil;
import com.zl.user.bo.FootprintBO;
import com.zl.user.bo.FootprintLittleBO;
import com.zl.user.bo.ShopLittleBO;
import com.zl.user.dto.add.FootprintAddDTO;
import com.zl.user.dto.query.CommentQueryDTO;
import com.zl.user.dto.query.FootprintQueryDTO;
import com.zl.user.entity.Footprint;
import com.zl.user.feignClient.SetupFeignClient;
import com.zl.user.mapper.FootprintMapper;
import com.zl.user.service.FootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhangliang on 2019/5/24
 */
@Service
public class FootprintServiceImpl implements FootprintService {

    @Autowired
    FootprintMapper footprintMapper;

    @Autowired
    RedisUtil<String , FootprintBO> redisUtil;

    @Autowired
    SetupFeignClient setupFeignClient;

    @Override
    public void add(FootprintAddDTO footprintAddDTO) {
        long userId = RequestResponseUtil.getUserId();
        long shopId = footprintAddDTO.getShopId();

        String customerFootprintKeyPrefix = RedisKeyConstant.customerFootprintKeyPrefix + userId;

        Long id = footprintMapper.getByUserIdAndShopId(userId,shopId);

        Footprint footprint;
        if (id != null) {
            footprint = new Footprint();
            footprint.setId(id);
            footprint.setAccessTime(new Date());
            footprintMapper.updateByPrimaryKeySelective(footprint);

            //删除
            redisUtil.delete(customerFootprintKeyPrefix);

        }else{
            Date accessTime = new Date();
            footprint = BeanUtil.copyProperties(footprintAddDTO, Footprint.class, true);
            footprint.setAccessTime(accessTime);
            footprintMapper.insertSelective(footprint);

            ShopLittleBO shopLittleBO = setupFeignClient.getById(shopId);
            FootprintBO footprintBO = BeanUtil.copyProperties(shopLittleBO, FootprintBO.class);
            footprintBO.setAccessTime(accessTime);

            redisUtil.lSet(customerFootprintKeyPrefix, footprintBO);
        }

    }

    @Override
    public void delete4Expire() {
        List<FootprintLittleBO> footprintLittleBOS = footprintMapper.getByExpired();

        if (CollectionUtil.isEmpty(footprintLittleBOS)) {
            return;
        }
        footprintLittleBOS.forEach(footprintLittleBO -> {
            footprintMapper.deleteByPrimaryKey(footprintLittleBO.getId());

            redisUtil.delete(RedisKeyConstant.customerFootprintKeyPrefix + footprintLittleBO.getUserId());
        });

    }

    @Override
    public MyPageInfo getByParam(FootprintQueryDTO footprintQueryDTO) {
        MyPageInfo pageInfo = new MyPageInfo();
        long userId = RequestResponseUtil.getUserId();
        String key = RedisKeyConstant.customerFootprintKeyPrefix + userId;
        int start = (footprintQueryDTO.getPageNum() - 1) * footprintQueryDTO.getPageSize();
        int end = footprintQueryDTO.getPageNum() * footprintQueryDTO.getPageSize() - 1;

        long size = redisUtil.lGetListSize(key);
        if (size == 0) {
            List<FootprintBO> footprintBOS = footprintMapper.getByParam(userId);
            if (CollectionUtil.isEmpty(footprintBOS)) {
                pageInfo.setHasNext(false);
                return pageInfo;
            }
            ;
            List<ShopLittleBO> shopLittleBOS = setupFeignClient.getByIds(footprintBOS.stream().map(FootprintBO::getShopId).collect(Collectors.toList()));
            Map<Long, ShopLittleBO> shopLittleBOMap = shopLittleBOS.stream().collect(Collectors.toMap(ShopLittleBO::getShopId, shopLittleBO -> shopLittleBO));
            footprintBOS.forEach(footprintBO -> {
                ShopLittleBO shopLittleBO = shopLittleBOMap.get(footprintBO.getShopId());
                cn.hutool.core.bean.BeanUtil.copyProperties(shopLittleBO, footprintBO);
            });

            redisUtil.lSet(key, footprintBOS);
            boolean has = footprintBOS.size() > end + 1;
            pageInfo.setHasNext(has);
            pageInfo.setList(footprintBOS.subList(start, has ? end + 1 : footprintBOS.size()));
            return pageInfo;
        }

        List<FootprintBO> footprintBOS = redisUtil.lGet(key, start, end);
        if (size <= end) {
            pageInfo.setHasNext(false);
            pageInfo.setList(footprintBOS);
            return pageInfo;
        }
        pageInfo.setHasNext(true);
        pageInfo.setList(footprintBOS);
        return pageInfo;
    }

}
