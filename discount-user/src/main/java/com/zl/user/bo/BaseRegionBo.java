package com.zl.user.bo;

import lombok.Data;

import java.util.List;

/**
 * Created by zhangliang on 2019/10/17
 */
@Data
public class BaseRegionBo {
    private String id;

    private String parentRegionId;

    private String cityCode;

    private String regionName;

    private int depth;

    private List<BaseRegionBo> children;

}
