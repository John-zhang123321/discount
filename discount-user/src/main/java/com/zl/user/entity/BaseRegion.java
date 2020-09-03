package com.zl.user.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "base_region")
@Data
public class BaseRegion {
    /**
     * 地区编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 地区编号
     */
    @Column(name = "parent_region_id")
    private Integer parentRegionId;

    /**
     * 地区名称
     */
    @Column(name = "region_name")
    private String regionName;

    private Integer depth;

    @Column(name = "city_code")
    private String cityCode;
}