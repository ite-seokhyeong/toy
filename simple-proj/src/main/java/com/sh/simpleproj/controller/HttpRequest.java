package com.sh.simpleproj.controller;

import javax.servlet.http.*;

public class HttpRequest extends HttpServletRequestWrapper {

    public HttpRequest(HttpServletRequest request) {
        super(request);
    }

}
