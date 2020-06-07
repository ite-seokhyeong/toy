package com.sh.simpleproj.component;

import javax.servlet.http.*;

public class HttpRequest extends HttpServletRequestWrapper { //ServletRequest -> HttpServletRequest -> HTtpServletRequestWrapper

    public HttpRequest(HttpServletRequest request) {
        super(request);
    }

}
