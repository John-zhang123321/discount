package com.jkb.common.entity.amap;

import lombok.Data;

/**
 * @Description 高德地图类
 */
@Data
public class DistantResult {

    private Integer origin_id;
    private Integer dest_id;
    private Integer distance;
    private Integer duration;
    private String info;
    private Integer code;
}
