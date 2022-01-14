package org.iptime.mpage.goods;

import com.google.gson.Gson;
import org.iptime.mpage.Json;
import org.iptime.mpage.dao.GoodsDAO;
import org.iptime.mpage.goods.model.GoodsEntity;
import org.iptime.mpage.goods.model.GoodsVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/goodslist")
public class GoodsListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String json = Json.getJson(req);
        System.out.println("json : "+json);
        Gson gosn = new Gson();
        GoodsEntity entity = gosn.fromJson(json, GoodsEntity.class);

        List<GoodsVo> list = GoodsDAO.selGoodsList(entity);

        Map<String, List<GoodsVo>> map = new HashMap<>();
        map.put("list", list);


        res.setContentType("text/plain;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");

        PrintWriter out = res.getWriter();
        out.println(gosn.toJson(map));
        System.out.println("gosn.toJson(map) : "+gosn.toJson(map));
    }
}
