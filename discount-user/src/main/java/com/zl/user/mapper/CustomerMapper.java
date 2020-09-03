package com.zl.user.mapper;

import com.zl.user.bo.CustomerLittleBO;
import com.zl.user.dto.query.CustomerQueryDTO;
import com.zl.user.entity.Customer;
import com.zl.user.bo.CustomerBO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import java.util.List;
/**
* @author zhangliang
* @date 2019-11-18
*/
public interface CustomerMapper extends BaseMapper<Customer> {

    List<CustomerBO> getByParam(@Param("customerQueryDTO") CustomerQueryDTO customerQueryDTO);

    Long getByOpenId(@Param("openId")String openId);

    CustomerLittleBO getCustomerLittleBOByOpenId(@Param("openId")String openId);

    String getOpenid(@Param("customerId")Long customerId);
}