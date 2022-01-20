package org.iptime.mpage.servlet.user;

import com.google.gson.Gson;
import org.iptime.mpage.DAO.UserDAO;
import org.iptime.mpage.Utils;
import org.iptime.mpage.model.user.UserDTO;
import org.iptime.mpage.model.user.UserVo;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "SnsLoginServlet", value = "/snslogin")
public class SnsLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = Utils.getJson(request); // json 정보 가져오기
        //System.out.println("json : " + json);

        Gson gson = new Gson();
        UserDTO dto = gson.fromJson(json, UserDTO.class); // json 객체화

        //------------------------------------
        int result = 0;
        // joinpath 이 1보다 크고, 이메일이 없다면(true 리턴) DB 저장
        if(dto.getJoinpath() > 1 && UserDAO.checkEmail(dto)){
            if(dto.getPw() == null){ // sns 로그인은 임의의 비밀번호 추가
                int randomPw = (int)((Math.random()+1)*1000000);
                dto.setPw("@"+randomPw+"!");
            }
            Utils.hashPw(dto, dto.getPw()); // 비밀번호 암호화
            Utils.strTogender(dto,dto.getGenderstr()); // 성별 정수로변경
            Utils.splitBirthday(dto,dto.getBirthday()); // 생일 분리

            if(UserDAO.insUser(dto) == 1) { // Db 저장 성공(1 리턴)
                UserVo vo = UserDAO.loginUser(dto); // 로그인 시도

                if(vo != null) {
                    result = Utils.setSession(request, vo); // 로그인 성공 시.. 1
                }
            }
        } else {
            UserVo vo = UserDAO.loginUser(dto); // 로그인 시도

            if(vo != null) {
                result = Utils.setSession(request, vo); // 로그인 성공 시.. 1
            }
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("result", result);
        //System.out.println(gson.toJson(map));
        String resjson = gson.toJson(map);

        response.setContentType("text/plain;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println(resjson);


    }
}
