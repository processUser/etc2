package org.iptime.mpage.servlet.user;

import org.iptime.mpage.Cookies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/refreshtoken")
public class RefreshTokenCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("refreshtoken");
        Cookie[] cookies = req.getCookies();
        System.out.println(cookies);

//        for (Cookie cookie : cookies){
//            System.out.println(cookie.getName());
//            System.out.println(cookie.getValue());
//        }
    }
}
