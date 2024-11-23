package com.swu.umcmc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 모든 경로에 대해
                .allowedOrigins("*")     // 모든 origin 허용
                .allowedMethods("*")     // 모든 HTTP 메서드 허용
                .allowedHeaders("*")     // 모든 헤더 허용
                .maxAge(3600);           // preflight 캐시 시간
    }
}