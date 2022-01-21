package org.iptime.mpage;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {  // cors 허용 js로 다른 주소 이용시 막혀있음.(보안관련)
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // https://brunch.co.kr/@adrenalinee31/1
        // https://kaludin.tistory.com/15
        // https://ko.javascript.info/fetch-crossorigin
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        //x-requested-with 는 ajax 요청이라는 의미
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Expose-Headers", "*, Authorization"); // 헤더에 임의의 값을 추가하기위해 사용했음..

        chain.doFilter(req, res);
    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
/*
* 주소가 다른 경우 ajax 통신을 브라우저에서 막는다.
* CORS 이슈 발생.
* */