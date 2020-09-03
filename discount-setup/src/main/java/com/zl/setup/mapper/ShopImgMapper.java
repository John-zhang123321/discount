package com.zl.setup.mapper;

import com.zl.setup.dto.query.ShopImgQueryDTO;
import com.zl.setup.entity.ShopImg;
import com.zl.setup.bo.ShopImgBO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import java.util.List;
/**
* @author zhangliang
* @date 2019-11-20
*/
public interface ShopImgMapper extends BaseMapper<ShopImg> {

    List<ShopImgBO> getByParam(@Param("shopImgQueryDTO") ShopImgQueryDTO shopImgQueryDTO);

    String getUrlByShopId(@Param("shopId")Long shopId);

    List<ShopImgBO> getByShopIds(@Param("shopIds")List<Long> shopIds);
}