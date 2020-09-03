package com.jkb.common.entity.amap;

import lombok.Data;

import java.util.List;

/**
 * @Description
 */
@Data
public class RouteChild {

    // 起始点经纬度
    private String origin;
    private String destination;
    private List<RouteChildPath> paths;

}
