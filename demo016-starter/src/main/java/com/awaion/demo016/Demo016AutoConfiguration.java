package com.awaion.demo016;

import com.awaion.demo016.controller.Demo016AutoController;
import com.awaion.demo016.controller.Demo016Controller;
import com.awaion.demo016.properties.Demo016Properties;
import com.awaion.demo016.service.Demo016Service;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动配置组件
 * 自动加载路径 META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 * 自动加载路径2 META-INF/spring.factories
 */
@Import({Demo016Properties.class}) // 导入别的配置组件
@Configuration
public class Demo016AutoConfiguration {
    @ConditionalOnMissingBean // 条件加载
    @Bean
    public Demo016Service demo016Service() {
        return new Demo016Service();
    }

    @Bean
    public Demo016AutoController demo016AutoController() {
        return new Demo016AutoController();
    }
}
