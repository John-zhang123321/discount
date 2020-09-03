package com.jkb.common.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "msg")
public class Msg {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 发送者
     */
    private Long sender;

    /**
     * 内容
     */
    private String content;

    /**
     * 重试次数
     */
    private Integer retry;

    /**
     * 0 失败 1.成功
     */
    private Boolean status;


}