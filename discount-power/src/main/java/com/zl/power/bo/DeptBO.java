package com.zl.power.bo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
* @author zhangliang
* @date 2019-11-02
*/
@Data
public class DeptBO implements Serializable {

    private Long id;

    private String name;

    private String pid;

    private Integer level;

    private Long createUser;

    private Date createTime;

}