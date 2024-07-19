package com.awaion.demo016;

import com.awaion.demo016.controller.Demo016Controller;
import com.awaion.demo016.properties.Demo016Properties;
import com.awaion.demo016.service.Demo016Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置组件
 */
@Import({Demo016Properties.class}) // 导入别的配置组件
@Configuration
public class Demo016Configuration {
    @Bean
    public Demo016Service demo016Service() {
        return new Demo016Service();
    }

    @Bean
    public Demo016Controller demo016Controller() {
        return new Demo016Controller();
    }
}
