package com.sh.simpleproj.component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface SimpleServlet extends Servlet {

    void service(HttpRequest req, HttpResponse res) throws IOException;

    //void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;

}
