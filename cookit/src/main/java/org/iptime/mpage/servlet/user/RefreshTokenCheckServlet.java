package org.iptime.mpage.servlet.user;

import com.google.gson.Gson;
import org.iptime.mpage.Cookies;
import org.iptime.mpage.DAO.UserDAO;
import org.iptime.mpage.TestJWT;
import org.iptime.mpage.Utils;
import org.iptime.mpage.model.user.UserDTO;
import org.iptime.mpage.model.user.UserResult;
import org.iptime.mpage.model.user.UserVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.util.Map;

@WebServlet("/refreshtoken")
public class RefreshTokenCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String ip2 = Inet4Address.getLocalHost().getHostAddress();  // 출력 - ip2 : 192.168.2.13
        StringBuffer url = req.getRequestURL();   // 출력 - url : http://localhost:8090/login

        //TODO main 페이지 접속시 json 으로 이전 주소값 같이 들고와서, refreshtoken 과 값 을 비교하여 행위 하면됨.
        String json = Utils.getJson(req);
        Gson gson = new Gson();
        UserDTO dto = gson.fromJson(json, UserDTO.class);

        //System.out.println("cookie 1 : " + req.getCookies());
        //System.out.println("refreshtoken");
        Cookie[] cookies = req.getCookies();

        System.out.println(cookies);
        for (Cookie cookie : cookies){
            System.out.println(cookie.getName());
            System.out.println(cookie.getValue());
        }
        TestJWT jwt = new TestJWT();
        try {
            Map<String, Object> map = jwt.verifyJWT(cookies[0].getValue(), "YTA2ZmFhMWEtN2ExYi00MWYyLWFhNTctOTBlNmE2Mzk4N2Yw");
            System.out.println("map ip : "+map.get("ip"));
            System.out.println("map url : "+map.get("url1"));
            System.out.println("ip : "+ip2);
            System.out.println("url : "+dto.getPreUrl());
            if(map.get("ip").equals(ip2) && map.get("url1").equals(dto.getPreUrl())){
                System.out.println("성공 AccessToken 발급!!");
                dto.setUserpk((int)map.get("url2"));
                UserVo vo = UserDAO.loginUser(dto); //로그인 sql 실행
                String ajwt = jwt.createAccessToken(vo, dto.getPreUrl()); // 토큰생성

                res.setHeader("Authorization", "Bearer "+ajwt);

            }else {
                System.out.println("AccessToken 발급 실패!!");

            }
        } catch (Exception e){

        }

        UserResult ur = new UserResult();
        ur.setResult(1);
        res.setContentType("text/plain;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();
        out.println(gson.toJson(ur));

    }
}
