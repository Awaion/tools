package com.awaion.demo016.annotation;

import com.awaion.demo016.Demo016Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用注解,启用配置 Demo016Configuration
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({Demo016Configuration.class})
public @interface EnableDemo016Api {
}
