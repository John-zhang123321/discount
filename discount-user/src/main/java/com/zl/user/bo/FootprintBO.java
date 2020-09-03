package com.zl.user.bo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
* @author zhangliang
* @date 2019-12-05
*/
@Data
public class FootprintBO {

    private Long footprintId;

    private Long shopId;

    private String shopName;

    private String url;

    private String detail;

    private Integer rate;

    private Date accessTime;
}