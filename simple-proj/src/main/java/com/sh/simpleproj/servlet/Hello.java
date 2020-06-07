package com.sh.simpleproj.servlet;

import com.sh.simpleproj.component.HttpRequest;
import com.sh.simpleproj.component.HttpResponse;
import com.sh.simpleproj.component.SimpleServlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * Hello 서블릿 프로그램
 */
public class Hello implements SimpleServlet {

    @Override
    public void service(HttpRequest req, HttpResponse res) throws IOException {
        System.out.println(req.getParameter("name"));
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

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
