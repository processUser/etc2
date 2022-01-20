package org.iptime.mpage.servlet.user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.iptime.mpage.TestJWT;
import org.iptime.mpage.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

@WebServlet(name = "CheckTokenServlet", value = "/checktoken")
public class CheckTokenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = Utils.getJson(request);
        System.out.println("checktoken : " + json);

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String,String>>(){}.getType();

        Map<String,Object> deserializeMap = gson.fromJson(json, type);
        System.out.println("deserializeMap : " + deserializeMap);
        TestJWT testjwt = new TestJWT();
        try {
            Map<String, Object> check = testjwt.verifyJWT((String) deserializeMap.get("token"),"");
            System.out.println("check : " + check);
            System.out.println("deserializeMap.get(\"token\") : " + deserializeMap.get("token"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
