package com.zl.power.dto;

import lombok.Data;

/**
 * Created by zhangliang on 2019/8/5
 */
@Data
public class UserParamDTO {
    private String id;
    private String username;
    private String email;
    private Byte enabled;
    private String roleId;
    private String jobId;
    private String deptId;
    private String phone;
}
