package com.cos.jwt.config;

import com.cos.jwt.filter.SpringFilterBeanUsed;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
   @Bean
    public FilterRegistrationBean<SpringFilterBeanUsed> filter1(){
        FilterRegistrationBean<SpringFilterBeanUsed> bean = new FilterRegistrationBean<>(new SpringFilterBeanUsed());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }

}
