package com.jkb.common.mq;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangliang on 2019/11/3
 */
@Data
public class MyMessage implements Serializable {

    private static final long serialVersionUID = -4110182865766089184L;

    private long sender;

    private int receiveType;

    private List<Long> receivers;

    private String content;

}
