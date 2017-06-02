package com.dily;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Andra on 5/9/2017.
 */
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/login").allowedMethods("GET","POST","PUT","OPTIONS","DELETE").allowedOrigins("http://localhost:4200/", "http://evil.com/" );
                registry.addMapping("/settings").allowedMethods("GET","POST","PUT","OPTIONS","DELETE").allowedOrigins("http://localhost:4200/", "http://evil.com/" );
                registry.addMapping("/friends/delete").allowedMethods("GET","POST","PUT","OPTIONS","DELETE").allowedOrigins("http://localhost:4200/", "http://evil.com/" );
                registry.addMapping("/upload").allowedMethods("GET","POST","PUT","OPTIONS","DELETE").allowedOrigins("http://localhost:4200/", "http://evil.com/" );
                registry.addMapping("/memories").allowedMethods("GET","POST","PUT","OPTIONS","DELETE").allowedOrigins("http://localhost:4200/", "http://evil.com/" );
                registry.addMapping("/editMemory").allowedMethods("GET","POST","PUT","OPTIONS","DELETE").allowedOrigins("http://localhost:4200/", "http://evil.com/" );
                registry.addMapping("/addDocuments").allowedMethods("GET","POST","PUT","OPTIONS","DELETE").allowedOrigins("http://localhost:4200/", "http://evil.com/" );
                registry.addMapping("/facebook").allowedMethods("GET","POST","PUT","OPTIONS","DELETE").allowedOrigins("http://localhost:4200/", "http://evil.com/" );

            }
        };
    }
}
