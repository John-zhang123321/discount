package com.zl.power.dto.add;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serializable;


/**
* @author zhangliang
* @date 2019-11-02
*/
@Data
public class DeptAddDTO implements Serializable {
    // 名称
    @NotBlank(message = "name 不能为空")
    private String name;

    // 上级部门
    private String pid;

    private Integer level;

}