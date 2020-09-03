package com.zl.user.feignClient;

import com.zl.common.config.FeignTokenInterceptor;
import com.zl.user.bo.UserRoleBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangliang on 2019/8/21
 */
@FeignClient(value = "power",configuration = {FeignTokenInterceptor.class})
public interface PowerFeignClient {

    @GetMapping("/authorize/getAuthorizeInfo")
    Object getAuthorizeInfo(@RequestParam("uid") long uid);

    @GetMapping("/authorize/getCityIdByMerchantId")
    int getCityIdByMerchantId(@RequestParam("merchantId") String merchantId);

    @GetMapping("/userRole/getRoleIdByUid")
    Map<Long, UserRoleBO> getRoleIdByUid(@RequestParam("mids") List<Long> mids);

    @GetMapping("/userRole/updateUserRole")
    void updateUserRole(@RequestParam("userId") Long userId,@RequestParam("roleId") Long roleId);

    @PostMapping("/userRole/addUserRole")
    void addUserRole(@RequestParam("userId") Long userId,@RequestParam("roleId") Long roleId);

    @GetMapping("/userRole/deleteUserRole")
    void deleteUserRole(@RequestParam("userId")Long userId);
}
