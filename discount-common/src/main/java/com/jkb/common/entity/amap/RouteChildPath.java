package com.jkb.common.entity.amap;

import lombok.Data;

/**
 * @Description  高德封装类
 */
@Data
public class RouteChildPath {
    // 距离 米
    private Integer distance;
    // 时间 秒
    private Integer duration;
    private Integer restriction;
    private String[] steps;
    private String strategy;
    private Integer toll_distance;
    private Integer tolls;
    private Integer traffic_lights;
}
