package com.zl.setup.mapper;

import com.zl.setup.dto.query.GoodsImgQueryDTO;
import com.zl.setup.entity.GoodsImg;
import com.zl.setup.bo.GoodsImgBO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import java.util.List;
/**
* @author zhangliang
* @date 2019-11-20
*/
public interface GoodsImgMapper extends BaseMapper<GoodsImg> {

    List<GoodsImgBO> getByParam(@Param("goodsImgQueryDTO") GoodsImgQueryDTO goodsImgQueryDTO);

    void updateGoodsIdByImgIds(@Param("goodImgIdList")List<Long> goodImgIdList, @Param("goodsId") Long goodsId);

    List<GoodsImgBO> getByIds(@Param("goodImgIdList")List<Long> goodImgIdList);

    void deleteByGoodsIds(@Param("goodsIds")List<Long> goodsIds);
}