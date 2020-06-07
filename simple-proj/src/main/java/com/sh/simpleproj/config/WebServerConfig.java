package com.sh.simpleproj.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

//@PropertySource("classpath:json-config/config.json")
@Component
public class WebServerConfig implements ApplicationContextInitializer<ConfigurableApplicationContext>,
                                                    WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

//    @Autowired
//    private Environment environment;

//    @Autowired
//    private  JsonProperties jsonProperties;

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
//        System.out.println("port = " + jsonProperties.getPort());
//        System.out.println("404 page = " + jsonProperties.getPage404());

//        System.out.println("404 error -> " + environment.getProperty("404-error"));
//        System.out.println("hello");
//        System.out.println("port -> " + environment.getProperty("server-port"));
//
//        System.out.println(port);

        //factory.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("server-port")))); //디폴트 포트 변경

//        System.out.println("404 error -> " + environment.getProperty("404-error"));
//        factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, environment.getProperty("403-error")));
//        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, environment.getProperty("404-error")));
//        factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, environment.getProperty("500-error")));
    }

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

        try {
            Resource resource = configurableApplicationContext.getResource("classpath:json-config/config.json");
            Map readValue = new ObjectMapper().readValue(resource.getInputStream(), Map.class);

            Set<Map.Entry> set = readValue.entrySet();
            List<MapPropertySource> propertySources = set.stream()
                    .map(entry -> new MapPropertySource(
                            entry.getKey().toString(),
                            Collections.singletonMap(
                                    entry.getKey().toString(), entry.getValue()
                            ))).collect(Collectors.toList());

            for (PropertySource propertySource : propertySources) {
                configurableApplicationContext.getEnvironment()
                        .getPropertySources()
                        .addFirst(propertySource);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
