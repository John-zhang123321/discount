package com.zl.user.service.impl;

import com.zl.user.entity.Customer;
import com.zl.user.service.CustomerService;
import com.zl.user.dto.add.LoginDTO;
import com.zl.user.dto.query.CustomerQueryDTO;
import com.zl.user.dto.update.CustomerUpdateDTO;
import com.zl.user.mapper.CustomerMapper;
import com.zl.user.bo.CustomerBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.zl.common.utils.BeanUtil;
import java.util.List;
import com.github.pagehelper.PageHelper;


/**
* @author zhangliang
* @date 2019-11-18
*/
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        customerMapper.deleteByPrimaryKey(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(CustomerUpdateDTO customerUpdateDTO) {
        Customer customer = BeanUtil.copyProperties(customerUpdateDTO, Customer.class,false);
        customerMapper.updateByPrimaryKeySelective(customer);
    }


    @Override
    public List<CustomerBO> getByParam(CustomerQueryDTO customerQueryDTO){
        PageHelper.startPage(customerQueryDTO.getPageNum(), customerQueryDTO.getPageSize());
        return customerMapper.getByParam(customerQueryDTO);
    }

    @Override
    public String getOpenid(Long customerId) {
        String openid = customerMapper.getOpenid(customerId);
        return openid;
    }

}