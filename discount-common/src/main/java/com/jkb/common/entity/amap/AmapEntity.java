package com.jkb.common.entity.amap;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @Description 高德封装对象
 */
@Data
public class AmapEntity<T> {
    private int errcode;
    private T data;
    private String errmsg;
    private String errdetail;
    private String ext;
    private int status;
    private String info;
    private T results;

    public static void main(String[] args) {
        //字符串转list<String>
        String str = "asd,fgh,jkl";
        List<String> lis = Arrays.asList(str.split(","));
        for (String string : lis) {
            System.out.println(string);
        }
        //list<String>转字符串
        System.out.println(String.join(",", lis));
    }

}
