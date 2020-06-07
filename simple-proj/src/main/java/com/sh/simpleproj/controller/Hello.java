package com.sh.simpleproj.controller;

import javax.servlet.*;
import java.io.IOException;

public class Hello implements SimpleServlet {

    @Override
    public void service(HttpRequest req, HttpResponse res) throws IOException {
        System.out.println("ok!!");
        java.io.Writer writer = res.getWriter();
        writer.write("Hello, ");
        System.out.println("name = " + req.getParameter("name"));
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
