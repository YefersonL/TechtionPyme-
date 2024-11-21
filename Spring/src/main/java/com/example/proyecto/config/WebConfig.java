package com.example.proyecto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @GetMapping("/")
    public String home() {
        return "index"; // Carga index.html desde "src/main/resources/templates/"
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:8080")  // Permitir solo el frontend de React
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTION")
                .allowedHeaders("*")
                .allowCredentials(true);
    }




}