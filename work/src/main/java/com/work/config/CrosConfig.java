package com.work.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//解决跨域问题，前后端端口不一样 固定代码
@Configuration
public class CrosConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
              .allowedOrigins("*")
              .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
              .allowCredentials(true)
              .maxAge(3600)
              .allowedHeaders("*");
    }
}
