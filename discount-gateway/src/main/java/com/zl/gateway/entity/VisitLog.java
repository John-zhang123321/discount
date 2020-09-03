package com.zl.gateway.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;
import javax.persistence.*;

@Table(name = "visit_log")
public class VisitLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * ip
     */
    private String ip;

    /**
     * 访问时间
     */
    @Column(name = "visit_time")
    private Date visitTime;

    /**
     * 访问路径
     */
    @Column(name = "visit_path")
    private String visitPath;

    /**
     * 所属系统
     */
    private String system;

    /**
     * 引擎版本
     */
    @Column(name = "engine_version")
    private String engineVersion;

    /**
     * 引擎类型
     */
    private String engine;

    /**
     * 浏览器版本
     */
    private String version;

    /**
     * 系统类型
     */
    private String os;

    /**
     * 是否为移动平台
     */
    @Column(name = "is_mobile")
    private Boolean mobile;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 平台类型
     */
    private String platform;

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
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取ip
     *
     * @return ip - ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置ip
     *
     * @param ip ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取访问时间
     *
     * @return visit_time - 访问时间
     */
    public Date getVisitTime() {
        return visitTime;
    }

    /**
     * 设置访问时间
     *
     * @param visitTime 访问时间
     */
    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
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
     * 获取引擎版本
     *
     * @return engine_version - 引擎版本
     */
    public String getEngineVersion() {
        return engineVersion;
    }

    /**
     * 设置引擎版本
     *
     * @param engineVersion 引擎版本
     */
    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }

    /**
     * 获取引擎类型
     *
     * @return engine - 引擎类型
     */
    public String getEngine() {
        return engine;
    }

    /**
     * 设置引擎类型
     *
     * @param engine 引擎类型
     */
    public void setEngine(String engine) {
        this.engine = engine;
    }

    /**
     * 获取浏览器版本
     *
     * @return version - 浏览器版本
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置浏览器版本
     *
     * @param version 浏览器版本
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取系统类型
     *
     * @return os - 系统类型
     */
    public String getOs() {
        return os;
    }

    /**
     * 设置系统类型
     *
     * @param os 系统类型
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * 获取是否为移动平台
     *
     * @return is_mobile - 是否为移动平台
     */
    public Boolean getMobile() {
        return mobile;
    }

    /**
     * 设置是否为移动平台
     *
     * @param mobile 是否为移动平台
     */
    public void setMobile(Boolean mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取浏览器类型
     *
     * @return browser - 浏览器类型
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * 设置浏览器类型
     *
     * @param browser 浏览器类型
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * 获取平台类型
     *
     * @return platform - 平台类型
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * 设置平台类型
     *
     * @param platform 平台类型
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }
}