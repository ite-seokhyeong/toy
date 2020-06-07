package com.sh.simpleproj.servlet;

import com.sh.simpleproj.component.HttpRequest;
import com.sh.simpleproj.component.HttpResponse;
import com.sh.simpleproj.component.SimpleServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * 현재 시간을 출력하는 서블릿 프로그램
 */
public class CurrentTime implements SimpleServlet {

    @Override
    public void service(HttpRequest req, HttpResponse res) throws IOException {

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
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        httpServletResponse.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = httpServletResponse.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<p>");
        pw.println("현재시간: " + hour + "시 " + min + "분 " + second + "초");
        pw.println("</p>");
        pw.println("</body>");
        pw.println("</html>");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
