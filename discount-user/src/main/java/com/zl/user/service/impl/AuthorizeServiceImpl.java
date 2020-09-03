package com.zl.user.service.impl;

import com.zl.common.utils.BeanUtil;
import com.zl.common.utils.JwtTokenUtil;
import com.zl.common.utils.RequestResponseUtil;
import com.zl.common.utils.WxUtil;
import com.zl.user.constant.UserType;
import com.zl.user.dto.add.LoginDTO;
import com.zl.user.entity.Customer;
import com.zl.user.entity.Merchant;
import com.zl.user.mapper.CustomerMapper;
import com.zl.user.mapper.MerchantMapper;
import com.zl.user.service.AuthorizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* @author zhangliang
* @date 2019-12-05
*/
@Service
@Slf4j
public class AuthorizeServiceImpl implements AuthorizeService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public int login(LoginDTO loginDTO) {
        int userType;
        String openid = WxUtil.getOpenid(loginDTO.getCode());
        loginDTO.setOpenId(openid);

        Long id = merchantMapper.getByOpenId(openid);

        if (id != null) {
            userType = UserType.MERCHANT;
            loginDTO.setId(id);
            Merchant merchant = BeanUtil.copyProperties(loginDTO, Merchant.class,false);
            merchantMapper.updateByPrimaryKeySelective(merchant);
            log.info("merchant is [{}]",merchant);
        }else{
            userType = UserType.CUSTOMER;
            id = customerMapper.getByOpenId(openid);
            if (id == null) {
                Customer customer = BeanUtil.copyProperties(loginDTO, Customer.class,true);
                log.info("customer is [{}]",customer);
                customerMapper.insertSelective(customer);
                id = customer.getId();
            }else {
                loginDTO.setId(id);
                Customer customer = BeanUtil.copyProperties(loginDTO, Customer.class,false);
                log.info("customer is [{}]",customer);
                customerMapper.updateByPrimaryKeySelective(customer);
            }
        }

        RequestResponseUtil.setToken(JwtTokenUtil.generateToken(id));
        return userType;
    }

    @Override
    public int getUserType() {
        if(merchantMapper.getById(RequestResponseUtil.getUserId())){
            return UserType.MERCHANT;
        }
        return UserType.CUSTOMER;
    }
}