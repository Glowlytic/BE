package com.spss.glowlytic.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    // Default url: http://localhost:8080/api/v1/swagger-ui/index.html

    @Value("${glowlytic.openapi.dev-url:http://localhost:8080}")
    private String devUrl;

    @Value("${glowlytic.openapi.prod-url:https://api.glowlytic.com}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Development Environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Production Environment");

        Contact contact = new Contact();
        contact.setEmail("luantnk2907@gmail.com");
        contact.setName("Glowlytic Support");
        contact.setUrl("https://glowlytic.com");

        Info info = new Info()
                .title("Glowlytic API")
                .version("1.0.0")
                .contact(contact)
                .description("API endpoints for Glowlytic Skincare Application.")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("Bearer Authentication")
                .description("JWT Token Authentication");

        Components components = new Components()
                .addSecuritySchemes("bearerAuth", securityScheme);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(components);
    }
}