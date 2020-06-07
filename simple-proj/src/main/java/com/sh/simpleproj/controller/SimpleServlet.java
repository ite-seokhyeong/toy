package com.sh.simpleproj.controller;

import javax.servlet.Servlet;
import java.io.IOException;


public interface SimpleServlet extends Servlet {

    void service(HttpRequest req, HttpResponse res) throws IOException;

}
