package org.iptime.mpage.servlet.user;

import com.google.gson.Gson;
import org.iptime.mpage.Utils;
import org.iptime.mpage.model.user.UserDTO;
import org.iptime.mpage.model.user.UserResult;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "JoinServlet", value = "/join")
public class JoinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = Utils.getJson(request);
        System.out.println("json : " + json);

        Gson gson = new Gson();
        UserDTO dto = gson.fromJson(json, UserDTO.class);
        Utils.hashPw(dto, dto.getPw());

        //int result = UserDAO.insUser(dto);

        int result = 1;

        UserResult us = new UserResult();
        us.setResult(result);

        System.out.println(gson.toJson(us));
        String resjson = gson.toJson(us);

        response.setContentType("text/plain;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println(resjson);
    }
}
