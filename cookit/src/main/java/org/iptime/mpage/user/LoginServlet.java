package org.iptime.mpage.user;

import com.google.gson.Gson;
import org.iptime.mpage.DAO.UserDAO;
import org.iptime.mpage.Utils;
import org.iptime.mpage.user.model.UserDTO;
import org.iptime.mpage.user.model.UserVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/snslogin")
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

        if(dto.getJoinpath() != 1 && UserDAO.checkEmail(dto)){ // 있으면 false 없으면 /true

            if(dto.getPw() == null){
                int randomPw = (int)((Math.random()+1)*1000000);
                dto.setPw("@"+randomPw+"!");
            }
            Utils.hashPw(dto, dto.getPw());
            Utils.strTogender(dto,dto.getGenderstr());
            Utils.splitBirthday(dto,dto.getBirthday());

            int result = UserDAO.insUser(dto); // 성공 1

            Map<String, Integer> map = new HashMap<>();
            map.put("result", result);
            System.out.println(gson.toJson(map));

            String resjson = gson.toJson(map);

            res.setContentType("text/plain;charset=UTF-8");
            res.setCharacterEncoding("UTF-8");

            PrintWriter out = res.getWriter();
            out.println(resjson);

        }

        UserVo vo = UserDAO.loginUser(dto);
        

    }
}
