package com.zl.user.dto.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Created by zhangliang on 2019/7/6
 */
@Data
public class BaseRegionQueryDTO {
    private String regionName;
}
