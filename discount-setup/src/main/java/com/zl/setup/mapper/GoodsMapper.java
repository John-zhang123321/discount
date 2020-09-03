package com.zl.setup.mapper;

import com.zl.setup.bo.ExpiredGoodsBO;
import com.zl.setup.bo.GoodsBO;
import com.zl.setup.dto.query.GoodsByShopQueryDTO;
import com.zl.setup.dto.query.GoodsQueryDTO;
import com.zl.setup.entity.Goods;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author zhangliang
* @date 2019-11-20
*/
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsBO> getByParam(@Param("goodsQueryDTO") GoodsQueryDTO goodsQueryDTO);

    List<GoodsBO> getByShopId(@Param("shopId") Long shopId);

    int getProportionByGoodsId(@Param("goodsId")Long goodsId);

    void deleteExpired();

    List<ExpiredGoodsBO> getExpired();

    List<Long> search(@Param("goodsName")String goodsName);

}