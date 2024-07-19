package com.awaion.cloud.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger3Config {
    @Bean
    public GroupedOpenApi ConsulApi() {
        // http://localhost:10000/swagger-ui/index.html
        return GroupedOpenApi.builder().group("Consul接口").pathsToMatch("/consul/**").build();
    }

    @Bean
    public GroupedOpenApi FeignApi() {
        return GroupedOpenApi.builder().group("Feign接口").pathsToMatch("/feign/**").build();
    }

    @Bean
    public GroupedOpenApi GateWayApi() {
        return GroupedOpenApi.builder().group("GateWay接口").pathsToMatch("/gateway/**").build();
    }

    @Bean
    public GroupedOpenApi MicrometerApi() {
        return GroupedOpenApi.builder().group("Micrometer接口").pathsToMatch("/micrometer/**").build();
    }

    @Bean
    public GroupedOpenApi PayApi() {
        return GroupedOpenApi.builder().group("CRUD接口").pathsToMatch("/pay/**").build();
    }

    @Bean
    public GroupedOpenApi Resilience4jApi() {
        return GroupedOpenApi.builder().group("Resilience4j接口").pathsToMatch("/resilience4j/**").build();
    }

    @Bean
    public OpenAPI docsOpenApi() {
        return new OpenAPI()
                .info(new Info().title("demo029")
                        .description("通用设计rest")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("www.awaion.com")
                        .url("https://yiyan.baidu.com/"));
    }
}

