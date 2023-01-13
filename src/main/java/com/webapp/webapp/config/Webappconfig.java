package com.webapp.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Webappconfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                 .allowedOrigins("https://d1ee44sv9up6wo.cloudfront.net/")        
                 .allowedMethods("GET","POST","PUT","DELETE");
    }
    
}


