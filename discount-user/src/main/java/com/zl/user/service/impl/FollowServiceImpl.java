package com.zl.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.constants.RedisKeyConstant;
import com.zl.common.entity.MyPageInfo;
import com.zl.common.utils.BeanUtil;
import com.zl.common.utils.RedisUtil;
import com.zl.common.utils.RequestResponseUtil;
import com.zl.user.bo.CouponBO;
import com.zl.user.bo.FollowBO;
import com.zl.user.bo.FollowLittleBO;
import com.zl.user.dto.add.FollowAddDTO;
import com.zl.user.dto.query.FollowQueryDTO;
import com.zl.user.dto.update.FollowUpdateDTO;
import com.zl.user.dto.update.ShopUpdateDTO;
import com.zl.user.entity.Follow;
import com.zl.user.expection.FollowException;
import com.zl.user.feignClient.SetupFeignClient;
import com.zl.user.mapper.FollowMapper;
import com.zl.user.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
* @author zhangliang
* @date 2019-12-01
*/
@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private RedisUtil<String,FollowBO> redisUtil;

    @Autowired
    private SetupFeignClient setupFeignClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void add(FollowAddDTO followAddDTO) {
        if(followMapper.getFollowStatusByShopIdAndUserId(followAddDTO.getShopId(), RequestResponseUtil.getUserId())){
            throw new FollowException(ErrorCodeConstants.CHECK_REPEATED_FOLLOW_ERROR);
        }

        Follow follow = BeanUtil.copyProperties(followAddDTO, Follow.class,true);
        followMapper.insertSelective(follow);

        String key = RedisKeyConstant.customerFollowKeyPrefix + follow.getCreateUser();
        redisUtil.delete(key);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(long shopId) {
        long userId = RequestResponseUtil.getUserId();
        followMapper.deleteByShopIAndUserId(shopId,userId);

        String key = RedisKeyConstant.customerFollowKeyPrefix + userId;
        redisUtil.delete(key);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(FollowUpdateDTO followUpdateDTO) {
        Follow follow = BeanUtil.copyProperties(followUpdateDTO, Follow.class,false);
        followMapper.updateByPrimaryKeySelective(follow);

        String key = RedisKeyConstant.customerFollowKeyPrefix + follow.getUserId();
        redisUtil.delete(key);
    }


    @Override
    public MyPageInfo getByParam(FollowQueryDTO followQueryDTO){
        long userId = RequestResponseUtil.getUserId();
        followQueryDTO.setUserId(userId);
        MyPageInfo pageInfo = new MyPageInfo();

        String key = RedisKeyConstant.customerFollowKeyPrefix + userId;
        int start = (followQueryDTO.getPageNum() - 1) * followQueryDTO.getPageSize();
        int end = followQueryDTO.getPageNum() * followQueryDTO.getPageSize() - 1;

        long size = redisUtil.lGetListSize(key);
        if (size == 0) {
            List<FollowLittleBO> followLittleBOS = followMapper.getByParam(followQueryDTO);
            if (CollectionUtil.isEmpty(followLittleBOS)) {
                pageInfo.setHasNext(false);
                return pageInfo;
            }

            List<FollowBO> followBOS = setupFeignClient.getByShopId(followLittleBOS);
            if (CollectionUtil.isEmpty(followBOS)) {
                pageInfo.setHasNext(false);
                return pageInfo;
            }
            redisUtil.lSet(key, followBOS);
            boolean has = followBOS.size() > end + 1;
            pageInfo.setHasNext(has);
            pageInfo.setList(followBOS.subList(start, has ? end + 1 : followBOS.size()));
            return pageInfo;
        }

        List<FollowBO> followBOS = redisUtil.lGet(key, start, end);
        if (size <= end) {
            pageInfo.setHasNext(false);
            pageInfo.setList(followBOS);
            return pageInfo;
        }
        pageInfo.setHasNext(true);
        pageInfo.setList(followBOS);
        return pageInfo;
    }

    @Override
    public void deleteFollowByShopId(Long shopId) {
        List<String> userIds = followMapper.getUserIdsByShopId(shopId);
        if (CollectionUtil.isNotEmpty(userIds)) {
            //删除用户关注该店铺的缓存
            userIds.forEach(userId -> redisUtil.delete(RedisKeyConstant.customerFollowKeyPrefix + userId));
        }
    }


    @Override
    public boolean getFollowStatusByShopIdAndUserId(long shopId) {
        return followMapper.getFollowStatusByShopIdAndUserId( shopId,  RequestResponseUtil.getUserId());
    }

}