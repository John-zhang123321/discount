package com.zl.setup;

import com.zl.common.utils.WxUtil;
import com.zl.setup.feignClient.UserFeignClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SetupApplicationTests {

    @Autowired
    private UserFeignClient userFeignClient;
    @Test
    public void contextLoads() {

//        WxUtil.pushOneUser("oN8MZ4zBNtPrd-3faysd5Y9GJyRs","审核不通过","不好");
    }

}
