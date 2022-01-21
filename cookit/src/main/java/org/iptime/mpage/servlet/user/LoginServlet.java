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
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Utils.disForward(req, res, "user/login");

    }

    /*
        gmail -  보내기 http://www.joshi.co.kr/index.php?mid=board_aCLN93&document_srl=306631
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String json = Utils.getJson(req);
        //System.out.println("json : " + json);
        Gson gson = new Gson();

        //InetAddress ip = Inet4Address.getLocalHost();  // 출력 - ip : DESKTOP-FU2AS1G/192.168.2.13
        String ip2 = Inet4Address.getLocalHost().getHostAddress();  // 출력 - ip2 : 192.168.2.13
        //String ip3 = req.getRemoteAddr();   // 출력 - ip3 : 0:0:0:0:0:0:0:1
        //String uri = req.getRequestURI();   // 출력 - uri : /login
        StringBuffer url = req.getRequestURL();   // 출력 - url : http://localhost:8090/login

        UserDTO dto = gson.fromJson(json, UserDTO.class);
        //dto.setUrl(url.toString());


        UserVo vo = UserDAO.loginUser(dto); //로그인 sql 실행

        String pw = dto.getPw();
        String jwt;
        String rjwt = null;
        int result = 0; // 로그인 실패 (아이디 비밀번호 확인 메세지)

        if(vo != null) {
            if(BCrypt.checkpw(pw, vo.getPw())) {
                // 로그인성공
                result = 1; // 성공 1

                try {
                    TestJWT testjwt = new TestJWT();
                    jwt = testjwt.createAccessToken(vo, dto.getPreUrl()); // 토큰생성
                    res.setHeader("Authorization", "Bearer "+jwt);
                    if(jwt != null){
                        String key = "";
                        if(key.equals(vo.getUkey())){
                            key = SecretKey.makeUuid(); // 키생성
                        } else {
                            key = vo.getUkey();
                        }
                        //TODO key 값 db 저장
                        int updkey = UserDAO.updKey(vo, key);
                        if(updkey == 1){
                            rjwt = testjwt.createRefreshToken(result, ip2, dto.getPreUrl(), vo.getUserpk(), key);
                            //------------------------------------
                            //cookie 저장
                            Cookies.setCookie(res, rjwt);
                            //------------------------------------
                        } else {
                            System.out.println("key : " + "실패!!!!!!!!!!!!");
                        }
                        //System.out.println("rjwt : " + rjwt);
                    }
                    //System.out.println("jwt : "+ jwt);

                    //out.println();
                } catch (Exception e){
                    e.printStackTrace();
                }
                //------------------------------------
            }
            //System.out.println("아ㅣ이디일치");
        }


        UserResult ur = new UserResult();
        ur.setResult(result);
        ur.setRjwt(rjwt);
        if (result == 0) {
            ur.setMsg("아이디 비밀번호를 확인 하세요");
        }



        String resjson = gson.toJson(ur);
        //System.out.println(gson.toJson(ur));

        res.setContentType("text/plain;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();
        out.println(resjson);


    }
}
