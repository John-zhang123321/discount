package com.zl.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.constants.NotifyTemplateEnum;
import com.zl.common.utils.*;
import com.zl.user.bo.ManagerBo;
import com.zl.user.bo.UserRoleBO;
import com.zl.user.constant.GlobalContract;
import com.zl.user.dto.add.ManagerAddDTO;
import com.zl.user.dto.query.ManagerLoginQueryDTO;
import com.zl.user.dto.query.ManagerQueryDTO;
import com.zl.user.dto.update.ManagerUpdateDTO;
import com.zl.user.entity.Manager;
import com.zl.user.expection.ManagerException;
import com.zl.user.expection.UserException;
import com.zl.user.feignClient.PowerFeignClient;
import com.zl.user.feignClient.SetupFeignClient;
import com.zl.user.mapper.BaseRegionMapper;
import com.zl.user.mapper.ManagerMapper;
import com.zl.user.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhangliang on 2019/5/24
 */
@Service
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerMapper managerMapper;

    @Autowired
    SetupFeignClient setupFeignClient;

    @Autowired
    BaseRegionMapper baseRegionMapper;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    PowerFeignClient powerFeignClient;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Object login(ManagerLoginQueryDTO managerLoginQueryDTO) {

        Manager manager = managerMapper.getAccount(managerLoginQueryDTO.getPhone());

        if (manager == null) {
            throw new ManagerException(ErrorCodeConstants.CHECK_ACCOUNT_NOTEXIST_ERROR);
        }

        if (!managerLoginQueryDTO.getPassword().equals(manager.getPassword())) {
            throw new ManagerException(ErrorCodeConstants.CHECK_ACCOUNT_PASSWORD_ERROR);
        }

        if(manager.getLocked()){
            throw new ManagerException(ErrorCodeConstants.CHECK_ACCOUNT_LOCKED_ERROR);
        }
        RequestResponseUtil.setToken(JwtTokenUtil.generateToken(manager.getId()));
//        redisUtil.hmset(manager.getUserId(),)
        return powerFeignClient.getAuthorizeInfo(manager.getId());
    }

    @Override
    public List<ManagerBo> getByParam(ManagerQueryDTO managerQueryDTO) {
        PageHelper.startPage(managerQueryDTO.getPageNum(), managerQueryDTO.getPageSize());
        List<ManagerBo> managerBos = managerMapper.getByParam(managerQueryDTO);
        if (CollectionUtil.isEmpty(managerBos)) {
            return managerBos;
        }
        List<Long> mids = managerBos.stream().map(managerBo -> managerBo.getId()).collect(Collectors.toList());
        Map<Long , UserRoleBO> map = powerFeignClient.getRoleIdByUid(mids);
        managerBos.stream().forEach(managerBo -> managerBo.setRoleId(map.get(managerBo.getId()) == null?null:map.get(managerBo.getId()).getRoleId()));
        return managerBos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(ManagerUpdateDTO managerUpdateDTO) {
        Manager dbManager = managerMapper.getAccount(managerUpdateDTO.getPhone());

        if (dbManager != null && dbManager.getId().longValue() != managerUpdateDTO.getId().longValue()) {
            throw new UserException(ErrorCodeConstants.CHECK_MANAGER_PHONE_EXIST_ERROR);
        }

        Manager manager = BeanUtil.copyProperties(managerUpdateDTO, Manager.class,false);
        managerMapper.updateByPrimaryKeySelective(manager);

        powerFeignClient.updateUserRole(managerUpdateDTO.getId(),managerUpdateDTO.getRoleId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ManagerAddDTO managerAddDTO) {

        if(managerMapper.getAccount(managerAddDTO.getPhone()) != null){
            throw new UserException(ErrorCodeConstants.CHECK_MANAGER_PHONE_EXIST_ERROR);
        }

        Manager manager = BeanUtil.copyProperties(managerAddDTO, Manager.class,true);
        manager.setPassword(GlobalContract.initPassword);
        managerMapper.insertSelective(manager);

        powerFeignClient.addUserRole(manager.getId(),managerAddDTO.getRoleId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        managerMapper.deleteByPrimaryKey(id);
        powerFeignClient.deleteUserRole(id);
    }

    @Override
    public void notice(String cityCode,String customerId) {
        List<String> mails = managerMapper.getByCityCode(cityCode);
        if (CollectionUtil.isNotEmpty(mails)) {
            mailUtil.sendTemplate(mails,customerId, NotifyTemplateEnum.TO_MANAGER);
        }
    }

    @Override
    public Object getInfo() {
        return powerFeignClient.getAuthorizeInfo(RequestResponseUtil.getUserId());
    }

}
