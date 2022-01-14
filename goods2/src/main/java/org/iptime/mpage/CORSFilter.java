package org.iptime.mpage;


import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class CORSFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // https://brunch.co.kr/@adrenalinee31/1
        // https://kaludin.tistory.com/15
        // https://ko.javascript.info/fetch-crossorigin
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        //x-requested-with 는 ajax 요청이라는 의미
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        response.setHeader("Access-Control-Allow-Origin", "*");

        chain.doFilter(req, res);
    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
