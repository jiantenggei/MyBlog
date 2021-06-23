package com.work.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MybatisPlusConfig {
    /**
     * MybatisPlus 分页配置
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor () {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
