package org.iptime.mpage.dao;

import org.iptime.mpage.DbUtils;
import org.iptime.mpage.goods.model.GoodsEntity;
import org.iptime.mpage.goods.model.GoodsVo;

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
        String sql = "SELECT A.goodspk, A.gnum, A.categorypk, A.gnm, A.price, A.quantity, A.rdt, B.categorynm AS category FROM cookit_goods A " +
                "LEFT JOIN cookit_goods_category B ON A.categorypk = B.categorypk ";

        if(entity.getCategorypk() != 0){
            sql += "WHERE A.categorypk = ?";
        }

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            if(entity.getCategorypk() != 0){
                ps.setInt(1, entity.getCategorypk());
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                GoodsVo vo = new GoodsVo();
                vo.setGoodspk(rs.getInt("goodspk"));
                vo.setGnum(rs.getString("gnum"));
                vo.setCategorypk(rs.getInt("categorypk"));
                vo.setGnm(rs.getString("gnm"));
                vo.setPrice(rs.getInt("price"));
                vo.setQuantity(rs.getInt("quantity"));
                vo.setRdt(rs.getString("rdt"));
                vo.setCategory(rs.getString("category"));
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
