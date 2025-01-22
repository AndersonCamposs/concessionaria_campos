package com.example.concessionaria_campos.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        factory.setLocation("/tmp");

        return factory.createMultipartConfig();
    }
}
