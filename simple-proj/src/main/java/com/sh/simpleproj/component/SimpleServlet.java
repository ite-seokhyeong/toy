package com.sh.simpleproj.component;

import javax.servlet.Servlet;
import java.io.IOException;


public interface SimpleServlet extends Servlet {

    void service(HttpRequest req, HttpResponse res) throws IOException;

    //void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;

}
