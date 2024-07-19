package com.awaion.demo027;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.MySqlDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * 启动 JPA 功能
 */
@EnableR2dbcRepositories
@Configuration
public class R2DbcConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public R2dbcCustomConversions conversions() {
        // 会保留默认提高的数据转换器
        // List<Object> storeConverters = new ArrayList(dialect.getConverters());
        // storeConverters.addAll(STORE_CONVERTERS);
        return R2dbcCustomConversions.of(MySqlDialect.INSTANCE, new BookConverter());
    }
}

