package com.zl.power.dto.update;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
* @author zhangliang
* @date 2019-11-02
*/
@Data
public class DeptUpdateDTO implements Serializable {

    @NotNull(message = "id 不能为空")
    private Long id;

    private String name;

    private String pid;

    private Integer level;
}