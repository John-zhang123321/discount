package com.zl.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.constants.RedisKeyConstant;
import com.zl.common.entity.MyPageInfo;
import com.zl.common.utils.RedisUtil;
import com.zl.common.utils.RequestResponseUtil;
import com.zl.user.bo.CommentCustomerBO;
import com.zl.user.constant.CouponStatusContract;
import com.zl.user.dto.add.CommentReplyDTO;
import com.zl.user.entity.Comment;
import com.zl.user.entity.Coupon;
import com.zl.user.expection.CommentException;
import com.zl.user.feignClient.SetupFeignClient;
import com.zl.user.mapper.CommentImgMapper;
import com.zl.user.mapper.CouponMapper;
import com.zl.user.mapper.MerchantMapper;
import com.zl.user.service.CommentService;
import com.zl.user.dto.add.CommentAddDTO;
import com.zl.user.dto.query.CommentQueryDTO;
import com.zl.user.dto.update.CommentUpdateDTO;
import com.zl.user.mapper.CommentMapper;
import com.zl.user.bo.CommentBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zl.common.utils.BeanUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.pagehelper.PageHelper;

import javax.validation.constraints.NotNull;


/**
* @author zhangliang
* @date 2019-12-05
*/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CommentImgMapper commentImgMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private SetupFeignClient setupFeignClient;

    @Autowired
    private RedisUtil<String ,CommentBO> redisUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(CommentAddDTO commentAddDTO) {
        Coupon coupon = BeanUtil.copyProperties(commentAddDTO, Coupon.class, false);
        coupon.setId(commentAddDTO.getCouponId());
        coupon.setStatus(CouponStatusContract.COMMENTED);
        couponMapper.updateByPrimaryKeySelective(coupon);

        Comment comment = BeanUtil.copyProperties(commentAddDTO, Comment.class,true);

        commentMapper.insertSelective(comment);

        if (CollectionUtil.isNotEmpty(commentAddDTO.getCommentImgIdList())) {
            commentImgMapper.updateByImgIds(commentAddDTO.getCommentImgIdList(), comment.getId());
        }

        Long shopId = commentAddDTO.getShopId();
        setupFeignClient.updateShopRateByShopId(shopId,commentMapper.getRateByShopId(shopId));

        String shopCommentKey = RedisKeyConstant.shopCommentKeyPrefix + comment.getShopId();

        redisUtil.delete(shopCommentKey);

        redisUtil.delete(RedisKeyConstant.customerCouponKeyPrefix + RequestResponseUtil.getUserId());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        commentMapper.deleteByPrimaryKey(id);

//        String shopCommentKey = RedisKeyConstant.shopCommentKeyPrefix + comment.getShopId();
//        //店铺缓存增加评论
//        redisUtil.hset(shopCommentKey,id+"",commentBO);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(CommentUpdateDTO commentUpdateDTO) {
        Comment comment = BeanUtil.copyProperties(commentUpdateDTO, Comment.class,false);
        commentMapper.updateByPrimaryKeySelective(comment);
    }


    @Override
    public List<CommentBO> getByParam(CommentQueryDTO commentQueryDTO){
        PageHelper.startPage(commentQueryDTO.getPageNum(), commentQueryDTO.getPageSize());
        return commentMapper.getByParam(commentQueryDTO);
    }

    @Override
    public synchronized MyPageInfo getByShopId(CommentQueryDTO commentQueryDTO) {
        MyPageInfo pageInfo = new MyPageInfo();
        int start = (commentQueryDTO.getPageNum() - 1) * commentQueryDTO.getPageSize();
        int end = commentQueryDTO.getPageNum() * commentQueryDTO.getPageSize() - 1;
        String key = RedisKeyConstant.shopCommentKeyPrefix + commentQueryDTO.getShopId();

        long size = redisUtil.lGetListSize(key);
        if (size == 0) {
            List<CommentBO> commentBOS = commentMapper.getByShopId(commentQueryDTO.getShopId());
            if (CollectionUtil.isEmpty(commentBOS)) {
                pageInfo.setHasNext(false);
                return pageInfo;
            }
            List<Long> createUsers = commentBOS.stream().filter(commentBO -> commentBO.getUserId() == null).map(CommentBO::getCreateUser).collect(Collectors.toList());

            if (CollectionUtil.isNotEmpty(createUsers)) {
                List<CommentCustomerBO> commentCustomerBOS = merchantMapper.getByCreateUsers(createUsers);
                if (CollectionUtil.isNotEmpty(commentCustomerBOS)) {
                    Map<Long, CommentCustomerBO> commentCustomerMap = commentCustomerBOS.stream().collect(Collectors.toMap(CommentCustomerBO::getUserId,CommentCustomerBO-> CommentCustomerBO));

                    commentBOS.forEach(commentBO -> {
                        cn.hutool.core.bean.BeanUtil.copyProperties(commentCustomerMap.get(commentBO.getCreateUser()),commentBO);
                    });
                }
            }

            List<CommentBO> fathers = commentBOS.stream().filter(commentBO->commentBO.getPid() == 0).collect(Collectors.toList());
            Map<Long, CommentBO> childMap = commentBOS.stream().filter(commentBO->commentBO.getPid() != 0).collect(Collectors.toMap(CommentBO::getPid,CommentBO-> CommentBO));
            if (CollectionUtil.isNotEmpty(childMap)) {
                fathers.forEach(father ->{
                    father.setChild(childMap.get(father.getId()));
                });
            }
            redisUtil.lSet(key, fathers);
            boolean has = fathers.size() > end + 1;
            pageInfo.setHasNext(has);
            pageInfo.setList(checkReply(commentQueryDTO.getShopId(),fathers.subList(start, has ? end + 1 : fathers.size())));
            return pageInfo;
        }
        List<CommentBO> commentBOS = redisUtil.lGet(key, start, end);
        if (size <= end) {
            pageInfo.setHasNext(false);
            pageInfo.setList(checkReply(commentQueryDTO.getShopId(),commentBOS));
            return pageInfo;
        }
        pageInfo.setHasNext(true);
        pageInfo.setList(checkReply(commentQueryDTO.getShopId(),commentBOS));
        return pageInfo;
    }

    private List<CommentBO> checkReply(Long shopId,List<CommentBO> commentBOS){
        Long merchantId = setupFeignClient.getMerchantIdByShopId(shopId);

        if(merchantId != null && RequestResponseUtil.getUserId() == merchantId){
            commentBOS.forEach(goodsBO ->goodsBO.setCanReply(true));
        }
        return commentBOS;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void reply(CommentReplyDTO commentReplyDTO) {
        if(commentMapper.getCountByPid(commentReplyDTO.getPid()) >= 1){
            throw new CommentException(ErrorCodeConstants.CHECK_COMMENT_EXIST_ERROR);
        }

        Comment comment = BeanUtil.copyProperties(commentReplyDTO, Comment.class,true);
        commentMapper.insertSelective(comment);

        String shopCommentKey = RedisKeyConstant.shopCommentKeyPrefix + comment.getShopId();
        redisUtil.delete(shopCommentKey);
    }

    @Override
    public double getRateByShopId(Long shopId) {
        Double rate = commentMapper.getRateByShopId(shopId);
        if (rate == null) {
            return 0;
        }
        return rate;
    }

}