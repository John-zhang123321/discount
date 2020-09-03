package com.zl.setup.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.math.MathUtil;
import cn.hutool.core.util.NumberUtil;
import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.constants.RedisKeyConstant;
import com.zl.common.entity.MyPageInfo;
import com.zl.common.utils.BeanUtil;
import com.zl.common.utils.RedisUtil;
import com.zl.common.utils.RequestResponseUtil;
import com.zl.setup.bo.GoodsBO;
import com.zl.setup.dto.add.GoodsAddDTO;
import com.zl.setup.dto.query.GoodsByShopQueryDTO;
import com.zl.setup.dto.query.GoodsQueryDTO;
import com.zl.setup.dto.update.GoodsUpdateDTO;
import com.zl.setup.entity.Goods;
import com.zl.setup.expection.GoodsException;
import com.zl.setup.feignClient.UserFeignClient;
import com.zl.setup.mapper.GoodsImgMapper;
import com.zl.setup.mapper.GoodsMapper;
import com.zl.setup.mapper.ShopMapper;
import com.zl.setup.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsImgMapper goodsImgMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private RedisUtil<String ,GoodsBO> redisUtil;

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(GoodsAddDTO goodsAddDTO) {
        BigDecimal originalPriceMax = goodsAddDTO.getOriginalPriceMax();
        BigDecimal originalPriceMin = goodsAddDTO.getOriginalPriceMin();

        if (originalPriceMax.compareTo(originalPriceMin) < 1) {
            throw new GoodsException(ErrorCodeConstants.CHECK_GOODS_ORIGINALPRICEMAX_ORIGINALPRICEMIN_ERROR);
        }

        BigDecimal presentPriceMax = goodsAddDTO.getPresentPriceMax();
        BigDecimal presentPriceMin = goodsAddDTO.getPresentPriceMin();

        if (presentPriceMax.compareTo(originalPriceMax) > 1) {
            throw new GoodsException(ErrorCodeConstants.CHECK_GOODS_PRESENTPRICEMAX_PRESENTPRICEMIN_ERROR);
        }

        if (presentPriceMax.compareTo(presentPriceMin) < 1) {
            throw new GoodsException(ErrorCodeConstants.CHECK_GOODS_PRESENTPRICEMAX_PRESENTPRICEMIN_ERROR);
        }

        Goods goods = BeanUtil.copyProperties(goodsAddDTO, Goods.class,true);
        Long shopId = shopMapper.getIdByUserId(goods.getCreateUser());
        goods.setShopId(shopId);

        goodsMapper.insertSelective(goods);

        goodsImgMapper.updateGoodsIdByImgIds(goods.getGoodImgIdList(), goods.getId());

        String merchantGoodsKey = RedisKeyConstant.merchantGoodsKeyPrefix + goods.getCreateUser();
        String shopGoodsKey = RedisKeyConstant.shopGoodsKeyPrefix + shopId;

        GoodsBO goodsBO = BeanUtil.copyProperties(goods, GoodsBO.class, false);
        goodsBO.setGoodsImgBOS(goodsImgMapper.getByIds(goods.getGoodImgIdList()));

        //店铺缓存增加商品
        redisUtil.lSet(shopGoodsKey,goodsBO);

        //用户缓存增加商品
        redisUtil.lSet(merchantGoodsKey,goodsBO);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        goodsMapper.deleteByPrimaryKey(id);

        long userId = RequestResponseUtil.getUserId();
        String merchantGoodsKey = RedisKeyConstant.merchantGoodsKeyPrefix + userId;
        String shopGoodsKey = RedisKeyConstant.shopGoodsKeyPrefix + shopMapper.getIdByUserId(userId);

        //店铺缓存删除商品
        redisUtil.delete(shopGoodsKey);

        //用户缓存删除商品
        redisUtil.delete(merchantGoodsKey);

        //todo 是否删除用户该商品的优惠券有待商榷
        userFeignClient.deleteByGoodsId(id);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(GoodsUpdateDTO goodsUpdateDTO) {
        Goods goods = BeanUtil.copyProperties(goodsUpdateDTO, Goods.class,false);

        goodsMapper.updateByPrimaryKeySelective(goods);

        String merchantGoodsKey = RedisKeyConstant.merchantGoodsKeyPrefix + goods.getUpdateUser();
        String shopGoodsKey = RedisKeyConstant.shopGoodsKeyPrefix + goodsUpdateDTO.getShopId();

        GoodsBO goodsBO = redisUtil.hget(merchantGoodsKey,goodsUpdateDTO.getId()+"");
        cn.hutool.core.bean.BeanUtil.copyProperties(goodsUpdateDTO, goodsBO);

        redisUtil.delete(shopGoodsKey);
        redisUtil.delete(merchantGoodsKey);
    }


    @Override
    public MyPageInfo getByParam(GoodsQueryDTO goodsQueryDTO){
        MyPageInfo pageInfo = new MyPageInfo();
        long userId = RequestResponseUtil.getUserId();

        goodsQueryDTO.setUserId(userId);

        String merchantGoodsKey = RedisKeyConstant.merchantGoodsKeyPrefix + userId;
        int start = (goodsQueryDTO.getPageNum() - 1) * goodsQueryDTO.getPageSize();
        int end = goodsQueryDTO.getPageNum() * goodsQueryDTO.getPageSize() - 1;


        long size = redisUtil.lGetListSize(merchantGoodsKey);
        if (size == 0) {
            List<GoodsBO> goodsBOS = goodsMapper.getByParam(goodsQueryDTO);
            if (CollectionUtil.isEmpty(goodsBOS)) {
                pageInfo.setHasNext(false);
                return pageInfo;
            }
            redisUtil.lSet(merchantGoodsKey, goodsBOS);
            boolean has = goodsBOS.size() > end + 1;
            pageInfo.setHasNext(has);
            pageInfo.setList(goodsBOS.subList(start, has ? end + 1 : goodsBOS.size()));
            return pageInfo;
        }

        List<GoodsBO> goodsBOS = redisUtil.lGet(merchantGoodsKey, start, end);
        if (size <= end) {
            pageInfo.setHasNext(false);
            pageInfo.setList(goodsBOS);
            return pageInfo;
        }
        pageInfo.setHasNext(true);
        pageInfo.setList(goodsBOS);
        return pageInfo;

    }

    @Override
    public MyPageInfo getByShopId(GoodsByShopQueryDTO goodsByShopQueryDTO) {
        MyPageInfo pageInfo = new MyPageInfo();

        Long shopId = goodsByShopQueryDTO.getShopId();
        String shopGoodsKey = RedisKeyConstant.shopGoodsKeyPrefix + shopId;
        List<GoodsBO> goodsBOS = redisUtil.lGet(shopGoodsKey,0,-1);
        if (CollectionUtil.isEmpty(goodsBOS)) {
            goodsBOS = goodsMapper.getByShopId(shopId);
            if (CollectionUtil.isEmpty(goodsBOS)) {
                pageInfo.setHasNext(false);
                return pageInfo;
            }
            redisUtil.lSet(shopGoodsKey, goodsBOS);
            pageInfo.setList(goodsBOS);
            return pageInfo;
        }
        Long merchantId = shopMapper.getMerchantIdByShopId(shopId);
        if(RequestResponseUtil.getUserId() == -1){
            goodsBOS.forEach(goodsBO ->goodsBO.setObtain(2));
        }else{
            if(merchantId != null && RequestResponseUtil.getUserId() != merchantId){
                List<Long> goodsIds = userFeignClient.getCouponByUserIdAndShopId(shopId);

                goodsBOS.forEach(goodsBO ->goodsBO.setObtain(goodsIds.contains(goodsBO.getId())?1:2));
            }
        }


        pageInfo.setList(goodsBOS);
        return pageInfo;
    }
    @Override
    public int getProportionByGoodsId(Long goodsId) {
        return goodsMapper.getProportionByGoodsId(goodsId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void addSale(Long goodsId) {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        if (goods == null) {
            return;
        }

        goods.setSale(goods.getSale() + 1);
        goods.setUpdateTime(new Date());
        goods.setUpdateUser(RequestResponseUtil.getUserId());
        goodsMapper.updateByPrimaryKeySelective(goods);

        String merchantGoodsKey = RedisKeyConstant.merchantGoodsKeyPrefix + goods.getCreateUser();
        String shopGoodsKey = RedisKeyConstant.shopGoodsKeyPrefix + goods.getShopId();

        redisUtil.delete(shopGoodsKey);
        redisUtil.delete(merchantGoodsKey);
    }

}