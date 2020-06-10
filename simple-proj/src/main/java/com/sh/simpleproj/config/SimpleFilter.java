package com.sh.simpleproj.config;

import com.sh.simpleproj.component.ServletContextRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleFilter extends OncePerRequestFilter {

    private static final String[] REQUEST_DOMAINS = new String[] {"/book.com", "/shopping.com"};
    private static final String[] CONTEXT_PATHS = new String[] {"/book", "/shopping"};

    /**
     * 요청 도메인에 따라 컨텍스트 경로 별도 설정, 페이지 이동
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        ServletContextRequestWrapper wrapper = new ServletContextRequestWrapper(httpServletRequest);
        String requestDomain = wrapper.getServerName(); //book.com, shopping.com

        if (requestDomain.equals(REQUEST_DOMAINS[0])) { //book.com으로 요청이 들어오면 -> contextPath: /book
            wrapper.setContextPath(httpServletRequest.getContextPath() + CONTEXT_PATHS[0]);
        } else if (requestDomain.equals(REQUEST_DOMAINS[1])) { //shopping.com으로 요청이 들어오면 -> contextPath: /shopping
            wrapper.setContextPath(httpServletRequest.getContextPath() + CONTEXT_PATHS[1]);
        } else { //localhost 테스트용
            wrapper.setContextPath(httpServletRequest.getContextPath() + CONTEXT_PATHS[0]);
        }

        if (!wrapper.getContextPath().isEmpty()) {
            RequestDispatcher rd = httpServletRequest.getRequestDispatcher(wrapper.getContextPath());
            rd.forward(httpServletRequest, httpServletResponse);
            //httpServletResponse.sendRedirect(wrapper.getContextPath());
        } else {
            filterChain.doFilter(wrapper, httpServletResponse);
        }
    }

}
