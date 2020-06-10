package com.sh.simpleproj.servlet;

import com.sh.simpleproj.component.HttpRequest;
import com.sh.simpleproj.component.HttpResponse;
import com.sh.simpleproj.component.SimpleServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Hello 서블릿 프로그램
 */
public class Hello implements SimpleServlet {

    static final Logger logger = LoggerFactory.getLogger(Hello.class);

    @Override
    public void service(HttpRequest req, HttpResponse res) throws IOException {
        logger.info("custom servlce()");
        java.io.Writer writer = res.getWriter();
        writer.write("Hello, ");
        writer.write(req.getParameter("name"));
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("default service()");
        java.io.Writer writer = servletResponse.getWriter();
        writer.write("Hello, ");
        writer.write(servletRequest.getParameter("name"));
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
