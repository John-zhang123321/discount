package com.jkb.common;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Date;
import java.util.Locale;

@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = {"com.jkb.common.mapper"})
public class CommonApplication {

    public static void main(String[] args) {
        DateTime s = DateUtil.truncate(new Date(), DateField.DAY_OF_WEEK_IN_MONTH);
        System.out.println(s);

    }
}
