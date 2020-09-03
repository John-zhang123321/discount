package com.zl.user.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "report")
public class Report {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 举报人id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 被举报人id
     */
    @Column(name = "be_user_id")
    private String beUserId;

    /**
     * 举报类型
     */
    @Column(name = "report_type_id")
    private String reportTypeId;

    /**
     * 状态 0.受理中 1.已反馈
     */
    private Integer status;

    /**
     * 举报结果
     */
    private String result;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取举报人id
     *
     * @return user_id - 举报人id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置举报人id
     *
     * @param userId 举报人id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取被举报人id
     *
     * @return be_user_id - 被举报人id
     */
    public String getBeUserId() {
        return beUserId;
    }

    /**
     * 设置被举报人id
     *
     * @param beUserId 被举报人id
     */
    public void setBeUserId(String beUserId) {
        this.beUserId = beUserId;
    }

    /**
     * 获取举报类型
     *
     * @return report_type_id - 举报类型
     */
    public String getReportTypeId() {
        return reportTypeId;
    }

    /**
     * 设置举报类型
     *
     * @param reportTypeId 举报类型
     */
    public void setReportTypeId(String reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    /**
     * 获取状态 0.受理中 1.已反馈
     *
     * @return status - 状态 0.受理中 1.已反馈
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 0.受理中 1.已反馈
     *
     * @param status 状态 0.受理中 1.已反馈
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取举报结果
     *
     * @return result - 举报结果
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置举报结果
     *
     * @param result 举报结果
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新人
     *
     * @return update_user - 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置更新人
     *
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}