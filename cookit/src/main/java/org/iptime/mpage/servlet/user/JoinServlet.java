package org.iptime.mpage.servlet.user;

import com.google.gson.Gson;
import org.iptime.mpage.DAO.UserDAO;
import org.iptime.mpage.Utils;
import org.iptime.mpage.model.user.UserDTO;
import org.iptime.mpage.model.user.UserResult;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "JoinServlet", value = "/join")
public class JoinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utils.disForward(request, response, "user/join");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = Utils.getJson(request);
        System.out.println("json : " + json);

        Gson gson = new Gson();
        UserDTO dto = gson.fromJson(json, UserDTO.class);
        String pw = dto.getPw();

        dto.setPw(BCrypt.hashpw(pw, BCrypt.gensalt())); // 비밀번호 암호화

        UserResult us = new UserResult();

        int result = UserDAO.insAgree(dto.agree);

        if(result == 1) {
            result = UserDAO.insUser(dto);
            if(result != 1) {
                us.setMsg("회원가입에 실패하였습니다.");
            }
        } else {
            result = 2; // 동의 실패.
            us.setMsg("동의 절차에 문제가 발생했습니다.");
        }


        us.setResult(result);

        System.out.println(gson.toJson(us));
        String resjson = gson.toJson(us);

        response.setContentType("text/plain;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println(resjson);
    }
}
