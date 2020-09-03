package com.zl.power.bo;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhangliang on 2019/8/3
 */
@Data
public class JobBO {
    private Long id;
    private String name;

    private Long did;
    private String dname;

    private Long pid;
    private String pname;

    private Integer sort;
    private Date createTime;
}
