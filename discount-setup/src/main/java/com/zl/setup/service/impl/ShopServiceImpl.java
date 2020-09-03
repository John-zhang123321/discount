package com.zl.setup.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.NumberUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.constants.RedisKeyConstant;
import com.zl.common.entity.MyPageInfo;
import com.zl.common.utils.*;
import com.zl.setup.bo.*;
import com.zl.setup.constant.AuditTypeContract;
import com.zl.setup.constant.ShopStatusContract;
import com.zl.setup.dto.add.MerchantAddDTO;
import com.zl.setup.dto.add.ShopAddDTO;
import com.zl.setup.dto.query.ShopQuery4CategoryDTO;
import com.zl.setup.dto.query.ShopQuery4FollowDTO;
import com.zl.setup.dto.query.ShopQueryDTO;
import com.zl.setup.dto.query.ShopSearchDTO;
import com.zl.setup.dto.update.ShopAuditDTO;
import com.zl.setup.dto.update.ShopUpdateDTO;
import com.zl.setup.entity.Shop;
import com.zl.setup.entity.ShopImg;
import com.zl.setup.expection.ShopException;
import com.zl.setup.feignClient.UserFeignClient;
import com.zl.setup.mapper.GoodsMapper;
import com.zl.setup.mapper.ShopImgMapper;
import com.zl.setup.mapper.ShopMapper;
import com.zl.setup.service.ShopService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Slf4j
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ShopImgMapper shopImgMapper;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private RedisUtil<String ,ShopLittleBO> redisUtil;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ShopAddDTO shopAddDTO) {
        Shop shop = BeanUtil.copyProperties(shopAddDTO, Shop.class,true);

        for (Long imgId : shopAddDTO.getShopImgIds()) {
            ShopImg shopImg = BeanUtil.copyProperties(imgId, ShopImg.class,false);
            shopImg.setId(imgId);
            shopImg.setShopId(shop.getId());
            shopImgMapper.updateByPrimaryKeySelective(shopImg);
        }

        Long createUser = shop.getCreateUser();

        shop.setUserId(createUser);
        shop.setReason(ShopStatusContract.NAUDIT.getResult());
        shopMapper.insertSelective(shop);

        userFeignClient.noticeManager(shopAddDTO.getCityCode(),createUser);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        shopMapper.deleteByPrimaryKey(id);

        ShopLittleBO shopLittleBO = shopMapper.getById(id);

        userFeignClient.deleteFollowByShopId(id);

        redisUtil.delete(RedisKeyConstant.shopKey);

        //删除customer关注的店铺
        userFeignClient.deleteFollowByShopId(id);

        //分类删除
        redisUtil.delete(RedisKeyConstant.categoryShopKeyPrefix + shopLittleBO.getCategoryId());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(ShopUpdateDTO shopUpdateDTO) {
        Shop shop = BeanUtil.copyProperties(shopUpdateDTO, Shop.class,false);

        shopMapper.updateByPrimaryKeySelective(shop);

        //删除店铺
        redisUtil.delete(RedisKeyConstant.shopKey);

        //删除customer关注的店铺
        userFeignClient.deleteFollowByShopId(shop.getId());

        //删除分类
        redisUtil.delete(RedisKeyConstant.categoryShopKeyPrefix + shopUpdateDTO.getCategoryId());
    }


    @Override
    public MyPageInfo getByParam(ShopQueryDTO shopQueryDTO){
        MyPageInfo pageInfo = new MyPageInfo();
        String key = RedisKeyConstant.shopKey;
        int start = (shopQueryDTO.getPageNum() - 1) * shopQueryDTO.getPageSize();
        int end = shopQueryDTO.getPageNum() * shopQueryDTO.getPageSize() - 1;

        long size = redisUtil.lGetListSize(key);
        if (size == 0) {
            List<ShopLittleBO> shopLittleBOS = shopMapper.getByParam(shopQueryDTO);
            if (CollectionUtil.isEmpty(shopLittleBOS)) {
                pageInfo.setHasNext(false);
                return pageInfo;
            }
            redisUtil.lSet(key, shopLittleBOS);
            boolean has = shopLittleBOS.size() > end + 1;
            pageInfo.setHasNext(has);
            shopLittleBOS = getDis(shopLittleBOS, shopQueryDTO.getLonlat());
            pageInfo.setList(shopLittleBOS.subList(start, has ? end + 1 : shopLittleBOS.size()));
            return pageInfo;
        }

        List<ShopLittleBO> shopLittleBOS = redisUtil.lGet(key, start, end);
        if (size <= end) {
            pageInfo.setHasNext(false);
            pageInfo.setList(getDis(shopLittleBOS, shopQueryDTO.getLonlat()));
            return pageInfo;
        }
        pageInfo.setHasNext(true);
        pageInfo.setList(getDis(shopLittleBOS, shopQueryDTO.getLonlat()));
        return pageInfo;
    }

    @Override
    public ShopBO getShopInfo() {
        Long wxToken = (Long) RequestResponseUtil.getTokenFiledValue("id");
        return shopMapper.getShopInfo(wxToken);
    }

    @Override
    public List<Shop4FollowBO> getByShopId(List<ShopQuery4FollowDTO> shopQuery4FollowDTOS) {
        List<Long> sids = shopQuery4FollowDTOS.stream().map(ShopQuery4FollowDTO::getSid).collect(Collectors.toList());
        List<ShopLittleBO> shopLittleBOS = shopMapper.getByIds(sids);

        if (CollectionUtil.isEmpty(shopLittleBOS)) {
            return null;
        }

        Map<Long,Long> map = shopQuery4FollowDTOS.stream().collect(Collectors.toMap(ShopQuery4FollowDTO::getSid, ShopQuery4FollowDTO::getFid));

        List<Shop4FollowBO> shop4FollowBOS = new ArrayList<>();
        shopLittleBOS.forEach(shopLittleBO -> {
            Shop4FollowBO shop4FollowBO = new Shop4FollowBO();
            cn.hutool.core.bean.BeanUtil.copyProperties(shopLittleBO,shop4FollowBO);
            shop4FollowBO.setFid(map.get(shopLittleBO.getShopId()));
            shop4FollowBO.setSid(shopLittleBO.getShopId());
            shop4FollowBOS.add(shop4FollowBO);
        });


        return shop4FollowBOS;
    }

    @Override
    public String getShopStatus() {
        Boolean shopStatus = shopMapper.getStatusByUserId(RequestResponseUtil.getUserId());

        if(BooleanUtil.isFalse(shopStatus)){
            throw new ShopException(ErrorCodeConstants.CHECK_AUTHENTICATION_NOT_PASS_ERROR);
        }
        return "success";
    }

    @Override
    public MyPageInfo getShopsByCategoryId(ShopQuery4CategoryDTO shopQuery4CategoryDTO) {
        MyPageInfo pageInfo = new MyPageInfo();
        String key = RedisKeyConstant.categoryShopKeyPrefix + shopQuery4CategoryDTO.getCategoryId();
        int start = (shopQuery4CategoryDTO.getPageNum() - 1) * shopQuery4CategoryDTO.getPageSize();
        int end = shopQuery4CategoryDTO.getPageNum() * shopQuery4CategoryDTO.getPageSize() - 1;

        long size = redisUtil.lGetListSize(key);
        if (size == 0) {
            List<ShopLittleBO> shopLittleBOS = shopMapper.getShopsByCategoryId(shopQuery4CategoryDTO.getCategoryId());
            if (CollectionUtil.isEmpty(shopLittleBOS)) {
                pageInfo.setHasNext(false);
                return pageInfo;
            }
            redisUtil.lSet(key, shopLittleBOS);
            boolean has = shopLittleBOS.size() > end + 1;
            pageInfo.setHasNext(has);
            shopLittleBOS = getDis(shopLittleBOS, shopQuery4CategoryDTO.getLonlat());
            pageInfo.setList(shopLittleBOS.subList(start, has ? end + 1 : shopLittleBOS.size()));
            return pageInfo;
        }

        List<ShopLittleBO> shopLittleBOS = redisUtil.lGet(key, start, end);
        if (size <= end) {
            pageInfo.setHasNext(false);
            pageInfo.setList(getDis(shopLittleBOS, shopQuery4CategoryDTO.getLonlat()));
            return pageInfo;
        }
        pageInfo.setHasNext(true);
        pageInfo.setList(getDis(shopLittleBOS, shopQuery4CategoryDTO.getLonlat()));
        return pageInfo;
    }

    @Override
    public ShopLittleBO getById(Long shopId) {
        return shopMapper.getById(shopId);
    }

    @Override
    public void audit(ShopAuditDTO shopAuditDTO) {
        boolean status = shopAuditDTO.getStatus() == ShopStatusContract.AUDIT.getStatus();
        if(status){
            shopAuditDTO.setReason(ShopStatusContract.AUDIT.getResult());
        }
        Shop shop = BeanUtil.copyProperties(shopAuditDTO, Shop.class, false);
        shopMapper.updateByPrimaryKeySelective(shop);

        String openid = userFeignClient.getOpenid(shopAuditDTO.getUserId());
        log.info("openid is {}", openid);
        WxUtil.pushOneUser(openid,status?ShopStatusContract.AUDIT.getResult():ShopStatusContract.AUDIT_FAILED.getResult(),
                status?"您的商户入驻审核通过啦,马上去发布商品吧":shopAuditDTO.getReason(), AuditTypeContract.MERCHANT_AUDIT.getType());
        if(status){
            Long shopId = shopAuditDTO.getId();
            ShopLittleBO dbShopLittleBO = shopMapper.getById(shopId);
            //店铺新增
            redisUtil.lSet(RedisKeyConstant.shopKey,dbShopLittleBO);
            //分类新增
            redisUtil.lSet(RedisKeyConstant.categoryShopKeyPrefix + dbShopLittleBO.getCategoryId(),dbShopLittleBO);

            MerchantAddDTO merchantAddDTO = new MerchantAddDTO();
            merchantAddDTO.setCustomerId(shopAuditDTO.getUserId());
            merchantAddDTO.setPhone(shopAuditDTO.getPhone());
            merchantAddDTO.setCityCode(shopAuditDTO.getCityCode());
            userFeignClient.register(merchantAddDTO);
        }
    }

    @Override
    public void reAudit(ShopUpdateDTO shopUpdateDTO) {
        Shop shop = BeanUtil.copyProperties(shopUpdateDTO, Shop.class,false);
        shop.setReason(ShopStatusContract.NAUDIT.getResult());
        shopMapper.updateByPrimaryKeySelective(shop);
    }

    @Override
    public Long getMerchantIdByShopId(Long shopId) {
        return shopMapper.getMerchantIdByShopId(shopId);
    }

    @Override
    public List<ShopLittleBO> getByIds(List<Long> shopIds) {
        return shopMapper.getByIds(shopIds);
    }

    @Override
    public List<ShopBO> getByParam4Web(ShopQueryDTO shopQueryDTO) {
        PageHelper.startPage(shopQueryDTO.getPageNum(), shopQueryDTO.getPageSize());
        List<ShopBO> shopBOS = shopMapper.getByParam4Web(shopQueryDTO);

        if (CollectionUtil.isEmpty(shopBOS)) {
            return shopBOS;
        }

        List<Long> shopIds = shopBOS.stream().map(ShopBO::getId).collect(Collectors.toList());
        List<ShopImgBO> shopImgBOS = shopImgMapper.getByShopIds(shopIds);

        if (CollectionUtil.isNotEmpty(shopImgBOS)) {
            Map<Long,List<ShopImgBO>> map = shopImgBOS.stream().collect(Collectors.groupingBy(ShopImgBO::getShopId));
            shopBOS.forEach(shopBO -> {
                shopBO.setShopImgBOS(map.get(shopBO.getId()));
            });
        }

        return shopBOS;
    }

    @Override
    public void updateShopRateByShopId(Long shopId,double rate) {
        Shop shop = BeanUtil.copyProperties(shopId, Shop.class, false);
        shop.setRate(rate);
        shopMapper.updateByPrimaryKeySelective(shop);

        //删除店铺
        redisUtil.delete(RedisKeyConstant.shopKey);
    }

    @Override
    public List<ShopLittleBO> search(ShopSearchDTO shopSearchDTO) {

        List<Long> shopIds = goodsMapper.search(shopSearchDTO.getGoodsName());

        if (CollectionUtil.isEmpty(shopIds)) {
            return Collections.EMPTY_LIST;
        }

        PageHelper.startPage(shopSearchDTO.getPageNum(), shopSearchDTO.getPageSize());

        return shopMapper.getByIds(shopIds);
    }


    private List<ShopLittleBO> getDis(List<ShopLittleBO> shopLittleBOS,String lonlat) {
        StringBuffer origins = new StringBuffer();
        for (ShopLittleBO shopLittleBO : shopLittleBOS) {
            origins.append(shopLittleBO.getLon()).append(",").append(shopLittleBO.getLat()).append("|");
        }
        origins.deleteCharAt(origins.length() - 1);

        Map<Integer, Integer> distanceMap = AmapUtil.getDistanceList(origins.toString(), lonlat, null, null);

        for (int i = 0; i < shopLittleBOS.size(); i++) {
            //设置距离
            double div = NumberUtil.div(distanceMap.get(i + 1).floatValue(), 1000, 2);
            log.info("{}-->米:{} 公里:{}",shopLittleBOS.get(i).getShopName(),distanceMap.get(i + 1).floatValue(),div);
            shopLittleBOS.get(i).setDistance(div);
        }

        //排序
        shopLittleBOS.sort(Comparator.comparing(ShopLittleBO::getDistance));

        return shopLittleBOS;
    }


}