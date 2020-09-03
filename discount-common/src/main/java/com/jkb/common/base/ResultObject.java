package com.jkb.common.base;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhangliang on 2018/11/20.
 * 返回对象
 */
@Data
public class ResultObject<T> implements Serializable{
    private static final long serialVersionUID = 1647700418175933120L;
    //响应码
    private int code;
    //数据
    private T data;
    //详情
    private String msg;

    public ResultObject() {
        this.code = 200;
        this.msg = "success";
    }


    public ResultObject(int code, String message) {
        this.code = code;
        this.msg = message;
    }


    @Override
    public String toString() {
        return "ResultObject{" +
                "respCode='" + code + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
