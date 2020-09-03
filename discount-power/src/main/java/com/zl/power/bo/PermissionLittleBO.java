package com.zl.power.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zl.power.base.BasePermission;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangliang on 2019/7/15
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionLittleBO {
    public Long id;

    public String alias;
    @JsonIgnore
    public Long pid;

    public Integer type;

    private List<PermissionLittleBO> children;
}
