package com.sh.simpleproj.config;

import com.sh.simpleproj.controller.CurrentTime;
import com.sh.simpleproj.controller.Hello;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<Hello> getServletRegistrationBean() { //Hello 서블릿 등록
        ServletRegistrationBean<Hello> registrationBean = new ServletRegistrationBean<>(new Hello());
        registrationBean.addUrlMappings("/Hello");
        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean<CurrentTime> getServletRegistrationBean2() { //현재 시간 출력하는 서블릿 등록
        ServletRegistrationBean<CurrentTime> registrationBean = new ServletRegistrationBean<>(new CurrentTime());
        registrationBean.addUrlMappings("/CurrentTime");
        return registrationBean;
    }

}
