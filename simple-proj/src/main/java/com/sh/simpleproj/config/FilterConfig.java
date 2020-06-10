package com.sh.simpleproj.config;

import com.sh.simpleproj.SimpleProjApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FilterConfig {

    @Autowired
    Environment environment;

    public SimpleFilter simpleFilter(Environment environment) {
        return new SimpleFilter(environment);
    }

    public FilterRegistrationBean wrapRequestFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(simpleFilter(environment));
        registrationBean.setName("simpleFilter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }


//    @Autowired
//    Environment environment;
//
//    @Bean
//    public FilterRegistrationBean getFilterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new SimpleFilter(environment));
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }

}
