package com.zl.user.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
* @author zhangliang
* @date 2019-12-01
*/
@Data
public class FollowBO{
    private Long fid;

    private Long sid;

    private String shopName;

    private String areaName;

    private String detail;

    private Integer rate;

    private String url;

}