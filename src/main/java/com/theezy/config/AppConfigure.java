package com.theezy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfigure implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry){
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:5756")
                    .allowedMethods("GET", "POST", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
        }
}
