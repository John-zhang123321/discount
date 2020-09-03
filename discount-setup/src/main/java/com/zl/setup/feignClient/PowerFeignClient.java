package com.zl.setup.feignClient;

import com.zl.common.base.ResultObject;
import com.zl.common.config.FeignTokenInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhangliang on 2019/8/21
 */
@FeignClient(value = "power",configuration = {FeignTokenInterceptor.class})
public interface PowerFeignClient {

    @GetMapping("/authorize/getAuthorizeInfo")
    ResultObject getAuthorizeInfo(@RequestParam("uid") String uid);

    @GetMapping("/authorize/getCityIdByMerchantId")
    int getCityIdByMerchantId(@RequestParam("merchantId") String merchantId);
}
