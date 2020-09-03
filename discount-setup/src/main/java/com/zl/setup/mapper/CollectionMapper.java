package com.zl.setup.mapper;

import com.zl.setup.dto.query.CollectionQueryDTO;
import com.zl.setup.entity.Collection;
import com.zl.setup.bo.CollectionBO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import java.util.List;
/**
* @author zhangliang
* @date 2019-11-20
*/
public interface CollectionMapper extends BaseMapper<Collection> {

    List<CollectionBO> getByParam(@Param("collectionQueryDTO") CollectionQueryDTO collectionQueryDTO);
}