package com.sh.simpleproj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Autowired
    Environment environment;

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        //HTTP errorCode 별 에러페이지 설정
        factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, environment.getProperty("page403")));
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, environment.getProperty("page404")));
        factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, environment.getProperty("page500")));
    }

}
