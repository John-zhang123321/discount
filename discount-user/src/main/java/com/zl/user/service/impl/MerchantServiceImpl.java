package com.zl.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.constants.NotifyTemplateEnum;
import com.zl.common.utils.BeanUtil;
import com.zl.common.utils.MailUtil;
import com.zl.common.utils.RequestResponseUtil;
import com.zl.user.bo.MerchantBO;
import com.zl.user.dto.add.MerchantAddDTO;
import com.zl.user.dto.query.MerchantLoginQueryDTO;
import com.zl.user.dto.query.MerchantQueryDTO;
import com.zl.user.dto.update.MerchantUpdateDTO;
import com.zl.user.entity.Customer;
import com.zl.user.entity.Merchant;
import com.zl.user.expection.ManagerException;
import com.zl.user.feignClient.PowerFeignClient;
import com.zl.user.mapper.CustomerMapper;
import com.zl.user.mapper.ManagerMapper;
import com.zl.user.mapper.MerchantMapper;
import com.zl.user.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
* @author zhangliang
* @date 2019-11-15
*/
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private CustomerMapper customerMapper;
    
    @Autowired
    private PowerFeignClient powerFeignClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(MerchantAddDTO merchantAddDTO) {
        Long customerId = merchantAddDTO.getCustomerId();
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        customer.setPhone(merchantAddDTO.getPhone());

        Merchant merchant = new Merchant();
        cn.hutool.core.bean.BeanUtil.copyProperties(customer, merchant);
        merchant.setCreateTime(new Date());
        merchant.setCreateUser(customer.getId());

        merchantMapper.insertSelective(merchant);
        customerMapper.deleteByPrimaryKey(customerId);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        merchantMapper.deleteByPrimaryKey(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(MerchantUpdateDTO merchantUpdateDTO) {
        Merchant merchant = BeanUtil.copyProperties(merchantUpdateDTO, Merchant.class,false);
        merchantMapper.updateByPrimaryKeySelective(merchant);
    }


    @Override
    public List<MerchantBO> getByParam(MerchantQueryDTO merchantQueryDTO){
        PageHelper.startPage(merchantQueryDTO.getPageNum(), merchantQueryDTO.getPageSize());
        return merchantMapper.getByParam(merchantQueryDTO);
    }

    @Override
    public Object login(MerchantLoginQueryDTO merchantLoginQueryDTO) {
        Merchant merchant = merchantMapper.getAccount(merchantLoginQueryDTO.getPhone());

        if (merchant == null) {
            throw new ManagerException(ErrorCodeConstants.CHECK_ACCOUNT_NOTEXIST_ERROR);
        }

        if (!merchantLoginQueryDTO.getPassword().equals(merchant.getPassword())) {
            throw new ManagerException(ErrorCodeConstants.CHECK_ACCOUNT_PASSWORD_ERROR);
        }

        if(merchant.getLocked()){
            throw new ManagerException(ErrorCodeConstants.CHECK_ACCOUNT_LOCKED_ERROR);
        }

//        RequestResponseUtil.setToken(JwtTokenUtil.generateToken(merchant.getId()));
        Object authorizeInfo = powerFeignClient.getAuthorizeInfo(merchant.getId());
//        redisUtil.hmset(manager.getUserId(),)
        return authorizeInfo;
    }

}