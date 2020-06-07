package com.sh.simpleproj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@PropertySource("classpath:json-config/config.json")
@Component
public class WebServerConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Autowired
    private Environment environment;

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        System.out.println("port -> " + environment.getProperty("server-port"));
        factory.setPort(Integer.parseInt(environment.getProperty("server-port"))); //디폴트 포트 변경
    }
}
