package org.iptime.mpage.DAO;

import org.iptime.mpage.DbUtils;
import org.iptime.mpage.model.goods.GoodsEntity;
import org.iptime.mpage.model.goods.GoodsVo;
import org.iptime.mpage.model.goods.ReviewVo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAO {
    // 상품 전체 조회
    // 상품 카테고리별 전체 조회
    public static List<GoodsVo> selGoodsList (GoodsEntity entity) {
        List<GoodsVo> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT A.goodspk, A.gnum, A.categorypk, A.gnm, A.price, A.quantity, A.rdt, " +
                "B.categorynm AS category , AVG(revscore) AS avgscore, COUNT(revscore) AS countscore " +
                "FROM cookit_goods A " +
                "LEFT JOIN cookit_goods_category B ON A.categorypk = B.categorypk " +
                "LEFT JOIN cookit_review C ON A.goodspk = C.goodspk ";

        if(entity.getCategorypk() != 0){
            sql += "WHERE A.categorypk = ? ";
        }
        sql += "GROUP BY goodspk";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            if(entity.getCategorypk() != 0){
                ps.setInt(1, entity.getCategorypk());
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                GoodsVo vo = new GoodsVo();
                ReviewVo rvo = new ReviewVo();
                vo.setGoodspk(rs.getInt("goodspk"));
                vo.setGnum(rs.getString("gnum"));
                vo.setCategorypk(rs.getInt("categorypk"));
                vo.setGnm(rs.getString("gnm"));
                vo.setPrice(rs.getInt("price"));
                vo.setQuantity(rs.getInt("quantity"));
                vo.setRdt(rs.getString("rdt"));
                vo.setCategory(rs.getString("category"));
                rvo.setAvgscore(rs.getDouble("avgscore"));
                rvo.setCountscore(rs.getInt("countscore"));
                vo.setReviewvo(rvo);
                list.add(vo);
            }
            return list;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con, ps, rs);
        }

        return null;
    }
}
