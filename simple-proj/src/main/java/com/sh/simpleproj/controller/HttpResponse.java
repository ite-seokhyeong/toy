package com.sh.simpleproj.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class HttpResponse extends HttpServletResponseWrapper {

    public HttpResponse(HttpServletResponse response) {
        super(response);
    }
}
//public class HttpResponse implements HttpServletResponse {
//
//    //getWriter() 구현
//    @Override
//    public PrintWriter getWriter() throws IOException {
//        return new PrintWriter(new ByteArrayOutputStream());
//    }
//}
