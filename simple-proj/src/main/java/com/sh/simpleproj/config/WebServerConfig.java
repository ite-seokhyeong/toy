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

@Component
public class WebServerConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

        try {
            //json 포맷으로 작성한 프로퍼티 값을 읽어온다.
            Resource resource = configurableApplicationContext.getResource("classpath:json-config/config.json");
            Map readValue = new ObjectMapper().readValue(resource.getInputStream(), Map.class);

            //json parsing
            Set<Map.Entry> set = readValue.entrySet();
            List<MapPropertySource> propertySources = set.stream()
                    .map(entry ->
                            new MapPropertySource(
                                entry.getKey().toString(),
                                Collections.singletonMap(entry.getKey().toString(), entry.getValue()
                                )))
                    .collect(Collectors.toList());

            for (PropertySource propertySource : propertySources) {
                configurableApplicationContext
                        .getEnvironment()
                        .getPropertySources()
                        .addFirst(propertySource);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
