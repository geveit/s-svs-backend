package com.geveit.s_svs.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("local")
public class LocalWebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all paths
                .allowedOrigins("http://localhost:4020") // Replace with your allowed origin(s)
                .allowedMethods("*") // Specify allowed methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow credentials (if needed)
    }
}