package com.zl.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "sig")
public class SignatureProperties {

    public static final String WHEN_INTERNET = "internet";
    public static final String WHEN_ALWAYS = "always";
    public static final String WHEN_OFF = "off";

    // internet | always | off
    private String when = WHEN_INTERNET;
    private List<String> appList;

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        if (when != null) {
            this.when = when.trim();
        }
    }


    public List<String> getAppList() {
        return appList;
    }

    public void setAppList(List<String> appList) {
        this.appList = appList;
    }


    public static class Sig {

        private String appId;
        private String appKey;
        private boolean enabled;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
