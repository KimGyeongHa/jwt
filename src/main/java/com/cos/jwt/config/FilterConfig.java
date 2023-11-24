package com.cos.jwt.config;

import com.cos.jwt.filter.Filter1;
import com.cos.jwt.filter.Filter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<Filter1> filter1(){
        FilterRegistrationBean<Filter1> bean = new FilterRegistrationBean<>(new Filter1());
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<Filter2> filter2(){
        FilterRegistrationBean<Filter2> bean = new FilterRegistrationBean<>(new Filter2());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }
}
