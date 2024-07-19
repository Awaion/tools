package com.awaion.demo016.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件 demo016.*
 */
@ConfigurationProperties(prefix = "demo016")
@Component
@Data
public class Demo016Properties {
    private String loginName = "admin";
    private String password = "1qaz@wsx";
}
