package com.zl.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Define OptimusProperties.
 * @author zhangliang
 */
@Configuration
@ConfigurationProperties(prefix = "optimus")
public class OptimusProperties {
    private List<String> excludeUrls = new ArrayList<>();

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls) {
            this.excludeUrls = excludeUrls;
        }

}
