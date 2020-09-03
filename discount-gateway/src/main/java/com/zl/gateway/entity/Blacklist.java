package com.zl.gateway.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blacklist")
public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 字符
     */
    private String str;

    /**
     * 类型0.手机号 1.ip
     */
    private String type;

    /**
     * 所属系统 
     */
    private String system;

    /**
     * 访问次数
     */
    @Column(name = "visit_number")
    private Boolean visitNumber;

    /**
     * 状态 0生效 1失效
     */
    private Boolean invalid;

    /**
     * 状态 0正常 1删除
     */
    private Boolean status;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private Long createUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @Column(name = "update_user")
    private Long updateUser;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取字符
     *
     * @return str - 字符
     */
    public String getStr() {
        return str;
    }

    /**
     * 设置字符
     *
     * @param str 字符
     */
    public void setStr(String str) {
        this.str = str;
    }

    /**
     * 获取类型0.手机号 1.ip
     *
     * @return type - 类型0.手机号 1.ip
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型0.手机号 1.ip
     *
     * @param type 类型0.手机号 1.ip
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取所属系统 
     *
     * @return system - 所属系统 
     */
    public String getSystem() {
        return system;
    }

    /**
     * 设置所属系统 
     *
     * @param system 所属系统 
     */
    public void setSystem(String system) {
        this.system = system;
    }

    /**
     * 获取访问次数
     *
     * @return visit_number - 访问次数
     */
    public Boolean getVisitNumber() {
        return visitNumber;
    }

    /**
     * 设置访问次数
     *
     * @param visitNumber 访问次数
     */
    public void setVisitNumber(Boolean visitNumber) {
        this.visitNumber = visitNumber;
    }

    /**
     * 获取状态 0生效 1失效
     *
     * @return invalid - 状态 0生效 1失效
     */
    public Boolean getInvalid() {
        return invalid;
    }

    /**
     * 设置状态 0生效 1失效
     *
     * @param invalid 状态 0生效 1失效
     */
    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
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

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public Long getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(Long createUser) {
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
    public Long getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置更新人
     *
     * @param updateUser 更新人
     */
    public void setUpdateUser(Long updateUser) {
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