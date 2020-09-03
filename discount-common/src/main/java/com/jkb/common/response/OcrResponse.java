package com.jkb.common.response;

import lombok.Data;

/**
 * Created by zhangliang on 2019/11/18
 */
@Data
public class OcrResponse {

    private String address;

    private String name;

    private String nationality;

    private String num;

    private String sex;

    private String birth;

    private String start_date;

    private String end_date;

    private String issue;

    private boolean is_fake;

    private boolean success;
}
