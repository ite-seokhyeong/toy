package com.sh.simpleproj.config;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleFilter implements Filter {

    private Environment environment;

    SimpleFilter(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("[Header] request protocol: " + request.getScheme());
        System.out.println("[Header] request host name: " + request.getServerName());
        System.out.println("[Header] URI: " + request.getRequestURI()); // /
        System.out.println("[Header] URL: " + request.getRequestURL()); // http://localshot:8001/

        //TODO: serverName, scheme에 따라 적합한 데이터 제공
        if (request.getServerName().equals("book.com")) {
            System.setProperty("server.servlet.context-path", environment.getProperty("server.contextPath.book"));
        } else if (request.getServerName().equals("shopping.com")){
            System.setProperty("server.servlet.context-path", environment.getProperty("server.contextPath.shopping"));
        } else {
            System.setProperty("server.servlet.context-path", "/");
        }

        filterChain.doFilter(request, response);
    }

}
