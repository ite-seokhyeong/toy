package com.sh.simpleproj.config;

import com.sh.simpleproj.controller.Hello;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<Hello> getServletRegistrationBean() {
        ServletRegistrationBean<Hello> registrationBean = new ServletRegistrationBean<>(new Hello());
        registrationBean.addUrlMappings("/Hello");
        return registrationBean;
    }

}
