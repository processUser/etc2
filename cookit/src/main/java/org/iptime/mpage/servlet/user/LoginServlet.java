package org.iptime.mpage.servlet.user;

import com.google.gson.Gson;
import org.iptime.mpage.Cookies;
import org.iptime.mpage.DAO.UserDAO;
import org.iptime.mpage.SecretKey;
import org.iptime.mpage.TestJWT;
import org.iptime.mpage.Utils;
import org.iptime.mpage.model.user.UserDTO;
import org.iptime.mpage.model.user.UserResult;
import org.iptime.mpage.model.user.UserVo;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String json = Utils.getJson(req);
        System.out.println("json : " + json);

        Gson gson = new Gson();
        UserDTO dto = gson.fromJson(json, UserDTO.class);
        String pw = dto.getPw();
        System.out.println("dto.getPreUrl() : " + dto.getPreUrl());
        UserVo vo = UserDAO.loginUser(dto); //로그인 sql 실행
        //System.out.println(dto.getPw() + " : "+ vo.getPw());
        String jwt;
        String rjwt = null;
        int result = 0; // 로그인 실패 (아이디 비밀번호 확인 메세지)
        if(vo != null) {
            if(BCrypt.checkpw(pw, vo.getPw())) {
                // 로그인성공
                result = 1; // 성공 1

                try {
                    TestJWT testjwt = new TestJWT();
                    jwt = testjwt.createAccessToken(vo, dto.getPreUrl());
                    res.setHeader("Authorization", "Bearer "+jwt);
                    if(jwt != null){
                        String key = SecretKey.makeUuid();
                        //TODO key 값 db 저장

                        rjwt = testjwt.createRefreshToken(result, dto.getPreUrl(), key);
                        System.out.println("rjwt : " + rjwt);
                    }
                    System.out.println("jwt : "+ jwt);

                    //------------------------------------
                    //cookie 저장
                    //Cookies.setCookie(res, jwt);
                    //------------------------------------

                    //out.println();
                } catch (Exception e){
                    e.printStackTrace();
                }
                //------------------------------------
            }
            System.out.println("아ㅣ이디일치");
        }


        UserResult ur = new UserResult();
        ur.setResult(result);
        ur.setRjwt(rjwt);
        if (result == 0) {
            ur.setMsg("아이디 비밀번호를 확인 하세요");
        }



        String resjson = gson.toJson(ur);
        System.out.println(gson.toJson(ur));

        res.setContentType("text/plain;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();
        out.println(resjson);


    }
}
