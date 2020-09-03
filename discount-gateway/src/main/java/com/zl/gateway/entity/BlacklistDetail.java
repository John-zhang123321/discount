package com.zl.gateway.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Table(name = "blacklist_detail")
public class BlacklistDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 黑名单id
     */
    @Column(name = "blacklist_id")
    private Long blacklistId;

    /**
     * 访问日期
     */
    @Column(name = "visit_date")
    private String visitDate;

    /**
     * 访问时间 
     */
    @Column(name = "visit_time")
    private String visitTime;

    /**
     * 访问来源
     */
    @Column(name = "visit_source")
    private String visitSource;

    /**
     * 访问路径
     */
    @Column(name = "visit_path")
    private String visitPath;

    /**
     * 状态 0正常 1删除
     */
    private Boolean status;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取黑名单id
     *
     * @return blacklist_id - 黑名单id
     */
    public Long getBlacklistId() {
        return blacklistId;
    }

    /**
     * 设置黑名单id
     *
     * @param blacklistId 黑名单id
     */
    public void setBlacklistId(Long blacklistId) {
        this.blacklistId = blacklistId;
    }

    /**
     * 获取访问日期
     *
     * @return visit_date - 访问日期
     */
    public String getVisitDate() {
        return visitDate;
    }

    /**
     * 设置访问日期
     *
     * @param visitDate 访问日期
     */
    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    /**
     * 获取访问时间 
     *
     * @return visit_time - 访问时间 
     */
    public String getVisitTime() {
        return visitTime;
    }

    /**
     * 设置访问时间 
     *
     * @param visitTime 访问时间 
     */
    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    /**
     * 获取访问来源
     *
     * @return visit_source - 访问来源
     */
    public String getVisitSource() {
        return visitSource;
    }

    /**
     * 设置访问来源
     *
     * @param visitSource 访问来源
     */
    public void setVisitSource(String visitSource) {
        this.visitSource = visitSource;
    }

    /**
     * 获取访问路径
     *
     * @return visit_path - 访问路径
     */
    public String getVisitPath() {
        return visitPath;
    }

    /**
     * 设置访问路径
     *
     * @param visitPath 访问路径
     */
    public void setVisitPath(String visitPath) {
        this.visitPath = visitPath;
    }

    /**
     * 获取状态 0正常 1删除
     *
     * @return status - 状态 0正常 1删除
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置状态 0正常 1删除
     *
     * @param status 状态 0正常 1删除
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }
}