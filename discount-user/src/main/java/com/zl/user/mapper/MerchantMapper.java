package com.zl.user.mapper;

import com.zl.user.bo.CommentCustomerBO;
import com.zl.user.dto.query.MerchantQueryDTO;
import com.zl.user.entity.Merchant;
import com.zl.user.bo.MerchantBO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import java.util.List;
/**
* @author zhangliang
* @date 2019-11-15
*/
public interface MerchantMapper extends BaseMapper<Merchant> {

    List<MerchantBO> getByParam(@Param("merchantQueryDTO") MerchantQueryDTO merchantQueryDTO);

    Merchant getAccount(@Param("phone")String phone);

    Long getByOpenId(@Param("openId")String openId);

    boolean getById(@Param("userId")long userId);

    List<CommentCustomerBO> getByCreateUsers(@Param("createUsers") List<Long> createUsers);
}