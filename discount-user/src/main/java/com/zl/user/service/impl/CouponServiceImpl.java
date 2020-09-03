package com.zl.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.json.JSONUtil;
import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.constants.RedisKeyConstant;
import com.zl.common.entity.MyPageInfo;
import com.zl.common.utils.QcloudCOSUtil;
import com.zl.common.utils.RedisUtil;
import com.zl.common.utils.RequestResponseUtil;
import com.zl.user.bo.CouponGeneratorBO;
import com.zl.user.constant.CouponStatusContract;
import com.zl.user.dto.update.CouponGeneratorDTO;
import com.zl.user.entity.Coupon;
import com.zl.user.expection.CouponException;
import com.zl.user.feignClient.SetupFeignClient;
import com.zl.user.service.CouponService;
import com.zl.user.dto.add.CouponAddDTO;
import com.zl.user.dto.query.CouponQueryDTO;
import com.zl.user.dto.update.CouponUpdateDTO;
import com.zl.user.mapper.CouponMapper;
import com.zl.user.bo.CouponBO;
import com.zl.user.websocket.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zl.common.utils.BeanUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* @author zhangliang
* @date 2019-12-26
*/
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private SetupFeignClient setupFeignClient;

    @Autowired
    private RedisUtil<String ,CouponBO> redisUtil;

    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void add(CouponAddDTO couponAddDTO) {

        if(couponMapper.getExistByGoodsIdAndUserId(couponAddDTO.getGoodsId(), RequestResponseUtil.getUserId())){
            throw new CouponException(ErrorCodeConstants.CHECK_COUPON_EXIST_ERROR);
        }

        Coupon coupon = BeanUtil.copyProperties(couponAddDTO, Coupon.class,true);
        try {
            File file = File.createTempFile(System.currentTimeMillis()+"", ".png");
            Map map = new HashMap();
            map.put("couponId", coupon.getId()+"");
            QrCodeUtil.generate(JSONUtil.toJsonStr(map), 300, 300, file);
            MockMultipartFile mockMultipartFile = new MockMultipartFile(file.getName(),file.getName(),null,new FileInputStream(file));
            coupon.setQrCode(QcloudCOSUtil.upload(mockMultipartFile));
            couponMapper.insertSelective(coupon);

            CouponBO couponBO = new CouponBO();
            cn.hutool.core.bean.BeanUtil.copyProperties(coupon,couponBO);
            couponBO.setProportion(0);
            couponBO.setStatus(CouponStatusContract.NOT_USE);

            redisUtil.delete(RedisKeyConstant.customerCouponKeyPrefix + RequestResponseUtil.getUserId());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        couponMapper.deleteByPrimaryKey(id);
        redisUtil.delete(RedisKeyConstant.customerCouponKeyPrefix + RequestResponseUtil.getUserId());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(CouponUpdateDTO couponUpdateDTO) {
        Coupon coupon = BeanUtil.copyProperties(couponUpdateDTO, Coupon.class,false);
        couponMapper.updateByPrimaryKeySelective(coupon);
    }


    @Override
    public MyPageInfo getByParam(CouponQueryDTO couponQueryDTO){
        long userId = RequestResponseUtil.getUserId();

        MyPageInfo pageInfo = new MyPageInfo();

        couponQueryDTO.setUserId(userId);

        String customerCouponKey = RedisKeyConstant.customerCouponKeyPrefix + userId;
        int start = (couponQueryDTO.getPageNum() - 1) * couponQueryDTO.getPageSize();
        int end = couponQueryDTO.getPageNum() * couponQueryDTO.getPageSize() - 1;

        long size = redisUtil.lGetListSize(customerCouponKey);
        if (size == 0) {
            List<CouponBO> couponBOS = couponMapper.getByParam(couponQueryDTO);
            if (CollectionUtil.isEmpty(couponBOS)) {
                pageInfo.setHasNext(false);
                return pageInfo;
            }
            redisUtil.lSet(customerCouponKey, couponBOS);
            boolean has = couponBOS.size() > end + 1;
            pageInfo.setHasNext(has);
            pageInfo.setList(couponBOS.subList(start, has ? end + 1 : couponBOS.size()));
            return pageInfo;
        }

        List<CouponBO> couponBOS = redisUtil.lGet(customerCouponKey, start, end);
        if (size <= end) {
            pageInfo.setHasNext(false);
            pageInfo.setList(couponBOS);
            return pageInfo;
        }
        pageInfo.setHasNext(true);
        pageInfo.setList(couponBOS);
        return pageInfo;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void expiredCouponByGoodsIds(List<Long> goodsIds) {
        List<CouponBO> couponBOS = couponMapper.getByGoodsIds(goodsIds);
        if(CollectionUtil.isEmpty(couponBOS)){
            return;
        }
        couponMapper.expiredCouponByGoodsIds(goodsIds);
        couponBOS.forEach(couponBO -> redisUtil.delete(RedisKeyConstant.customerCouponKeyPrefix + couponBO.getUserId()));
    }

    @Override
    public List<Long> getCouponByUserIdAndShopId(Long shopId) {
        return couponMapper.getCouponByUserIdAndShopId(RequestResponseUtil.getUserId(),shopId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized CouponGeneratorBO generator(CouponGeneratorDTO couponGeneratorDTO) {
        Coupon db = couponMapper.selectByPrimaryKey(couponGeneratorDTO.getId());

        if (db == null) {
            throw new CouponException(ErrorCodeConstants.CHECK_COUPON_NOT_EXIST_ERROR);
        }

        if (db.getStatus() == CouponStatusContract.USE) {
            throw new CouponException(ErrorCodeConstants.CHECK_COUPON_REPEAT_USE_ERROR);
        }
        if (db.getStatus() == CouponStatusContract.EXPIRE) {
            throw new CouponException(ErrorCodeConstants.CHECK_COUPON_EXPIRE_ERROR);
        }

        long merchantId = setupFeignClient.getMerchantIdByShopId(db.getShopId());

        if(merchantId != RequestResponseUtil.getUserId()){
            throw new CouponException(ErrorCodeConstants.CHECK_COUPON_MERCHANT_ERROR);
        }

        int prop = 100 - RandomUtil.randomInt(setupFeignClient.getProportionByGoodsId(db.getGoodsId()));

        Coupon coupon = BeanUtil.copyProperties(couponGeneratorDTO, Coupon.class,false);
        coupon.setProportion(prop);
        coupon.setStatus(CouponStatusContract.USE);

        couponMapper.updateByPrimaryKeySelective(coupon);

        CouponGeneratorBO couponGeneratorBO = new CouponGeneratorBO();
        couponGeneratorBO.setGoodsName(db.getGoodsName());
        couponGeneratorBO.setGoodsUrl(db.getGoodsUrl());
        couponGeneratorBO.setProportion(prop);
        couponGeneratorBO.setShopName(db.getShopName());

        setupFeignClient.addSale(db.getGoodsId());
        redisUtil.delete(RedisKeyConstant.customerCouponKeyPrefix + db.getUserId());

        myWebSocketHandler.push2Customer(db.getUserId(),prop);
        return couponGeneratorBO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByGoodsId(Long goodsId) {
        List goodsIds = CollectionUtil.newArrayList(goodsId);
        List<CouponBO> couponBOS = couponMapper.getByGoodsIds(goodsIds);

        //todo 用户有该商品优惠券商户是否可以删除该商品
        if(CollectionUtil.isEmpty(couponBOS)){
            return;
        }
        couponBOS.forEach(couponBO -> {
            couponMapper.deleteByPrimaryKey(couponBO.getId());
            redisUtil.delete(RedisKeyConstant.customerCouponKeyPrefix + couponBO.getUserId());
        });
    }
}