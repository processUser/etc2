package org.iptime.mpage.user;

import com.google.gson.Gson;
import org.iptime.mpage.Utils;
import org.iptime.mpage.user.model.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
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

        Utils.splitBirthday(dto,dto.getBirthday());
        System.out.println("Birthday : " + dto.getBirthdaydd());
        System.out.println("Birthday : " + dto.getBirthdaymm());

    }
}
