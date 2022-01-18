package org.iptime.mpage.servlet.goods;

import com.google.gson.Gson;
import org.iptime.mpage.DAO.GoodsDAO;
import org.iptime.mpage.DAO.ReviewDAO;
import org.iptime.mpage.Utils;
import org.iptime.mpage.model.goods.GoodsEntity;
import org.iptime.mpage.model.goods.GoodsVo;


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
        String json = Utils.getJson(req);
        System.out.println("json : "+json);
        Gson gosn = new Gson();
        GoodsEntity entity = gosn.fromJson(json, GoodsEntity.class);

        List<GoodsVo> list = GoodsDAO.selGoodsList(entity);

        //System.out.println("ReviewDAO.selAvg(list.get(i)" + ReviewDAO.selAvg(list.get(1)));
        // 응답시간이 오래걸림.
//        for(int i = 0; i< list.size(); i++){ // 상품별 리뷰 총 평균, 총 갯수
//            ReviewDAO.selAvg(list.get(i));
//        }

        Map<String, Object> map = new HashMap<>();
        map.put("list", list);

        res.setContentType("text/plain;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");

        PrintWriter out = res.getWriter();
        out.println(gosn.toJson(map));
        System.out.println("gosn.toJson(map) : "+gosn.toJson(map));
    }
}
