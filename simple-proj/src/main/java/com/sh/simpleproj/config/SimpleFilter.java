package com.sh.simpleproj.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("[Header] request protocol: " + request.getScheme());
        System.out.println("[Header] request host name: " + request.getServerName());
        System.out.println("[Header] URI: " + request.getRequestURI()); // /
        System.out.println("[Header] URL: " + request.getRequestURL()); // http://localshot:8000/

        //TODO: serverName, scheme에 따라 적합한 데이터 제공
//        if (request.getServerName().equals("localhost")) {
//            System.out.println("a");
//            response.sendRedirect("http://localhost:8000/loginPageA.html");
//            //response.sendRedirect(request.getRequestURL() + "loginPageA.html");
//        }

        filterChain.doFilter(request, response);
    }

}
