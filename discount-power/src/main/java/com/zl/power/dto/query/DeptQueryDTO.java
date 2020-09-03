package com.zl.power.dto.query;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
* @author zhangliang
* @date 2019-11-02
*/
@Data
public class DeptQueryDTO implements Serializable {

    private String name;
    private String pid;

    private int pageNum = 1;

    private int pageSize = 10;
}