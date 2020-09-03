package com.zl.setup.task;

import cn.hutool.core.collection.CollectionUtil;
import com.zl.common.constants.RedisKeyConstant;
import com.zl.common.utils.QcloudCOSUtil;
import com.zl.common.utils.RedisUtil;
import com.zl.setup.bo.ExpiredGoodsBO;
import com.zl.setup.bo.GoodsBO;
import com.zl.setup.bo.GoodsImgBO;
import com.zl.setup.feignClient.UserFeignClient;
import com.zl.setup.mapper.GoodsImgMapper;
import com.zl.setup.mapper.GoodsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangliang on 2020/1/1
 */
@Component
@Slf4j
public class GoodsTask {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsImgMapper goodsImgMapper;

    @Autowired
    private RedisUtil<String ,GoodsBO> redisUtil;

    @Autowired
    private UserFeignClient userFeignClient;


    @Scheduled(cron = "0 31 23 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void expiredTask() {
        List<ExpiredGoodsBO> expiredGoodsBOS = goodsMapper.getExpired();

        if (CollectionUtil.isEmpty(expiredGoodsBOS)) {
            return;
        }

        List<String> expiredUrls = new ArrayList<>();
        for (ExpiredGoodsBO expiredGoodsBO : expiredGoodsBOS) {
            List<GoodsImgBO> goodsImgBOS = expiredGoodsBO.getGoodsImgBOS();
            List<String> urls = goodsImgBOS.stream().map(GoodsImgBO::getUrl).collect(Collectors.toList());
            expiredUrls.addAll(urls);
        }

        QcloudCOSUtil.delete(expiredUrls);

        List<Long> goodsIds = expiredGoodsBOS.stream().map(ExpiredGoodsBO::getId).collect(Collectors.toList());
        goodsImgMapper.deleteByGoodsIds(goodsIds);

        goodsMapper.deleteExpired();

        //该商品的优惠券标识为过期
        userFeignClient.expiredCouponByGoodsIds(goodsIds);
        //最后删除redis

        for (ExpiredGoodsBO expiredGoodsBO : expiredGoodsBOS) {
            String merchantGoodsKey = RedisKeyConstant.merchantGoodsKeyPrefix + expiredGoodsBO.getUserId();
            String shopGoodsKey = RedisKeyConstant.shopGoodsKeyPrefix + expiredGoodsBO.getShopId();
            //店铺缓存删除商品
            redisUtil.delete(shopGoodsKey);

            //用户缓存删除商品
            redisUtil.delete(merchantGoodsKey);
        }
    }
}
