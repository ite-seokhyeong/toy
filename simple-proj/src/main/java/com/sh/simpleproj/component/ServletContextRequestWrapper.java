package com.sh.simpleproj.component;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ServletContextRequestWrapper extends HttpServletRequestWrapper {

    private String contextPath;

    public ServletContextRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getScheme() {
        return super.getScheme();
    }

    @Override
    public String getServerName() { //요청 도메인
        return super.getServerName();
    }

    @Override
    public String getContextPath() {
        if (!StringUtils.isEmpty(contextPath)) {
            return contextPath;
        }
        return super.getContextPath();
    }

    @Override
    public String getRequestURI() {
        String requestURI = super.getRequestURI();
        if (requestURI.equals(contextPath)) {
            return contextPath + "/";
        }
        return requestURI;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}
