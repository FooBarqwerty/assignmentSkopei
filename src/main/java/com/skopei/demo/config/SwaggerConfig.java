package com.skopei.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPIConfig() {
        Info info = new Info();
        info
                .title("Skopei API")
                .description("Assignment api")
                .version("v1.0.0");
        return new OpenAPI().info(info);
    }
}
