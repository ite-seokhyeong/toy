package com.sh.simpleproj.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:json-config/config.json")
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean getFilterRegistrationBean() {
        System.out.println("필터등록");
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new SimpleFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        System.out.println("필터등록완료");
        return registrationBean;
    }

}
