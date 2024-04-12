package com.awaken.tool.demo005.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("接口文档标题")
                        .description("接口文档描述")
                        .version("接口文档版本")
                        .contact(new Contact().name("开发者").url("联系方式")))
                .externalDocs(new ExternalDocumentation()
                        .description("说明")
                        .url("链接"));
    }

}

