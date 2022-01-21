package org.iptime.mpage.DAO;

import org.iptime.mpage.DbUtils;
import org.iptime.mpage.model.user.AgreeEntity;
import org.iptime.mpage.model.user.UserDTO;
import org.iptime.mpage.model.user.UserVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    // 동의 항목
    public static int insAgree(AgreeEntity entity){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into cookit_agree(userpk, termsOfUse, userInformation, marketing, age, notRealpage) " +
                "values(?, ?, ?, ?, ?, ?)";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, entity.getUserpk());
            ps.setInt(2, entity.getTermsOfUse());
            ps.setInt(3, entity.getUserInformation());
            ps.setInt(4, entity.getMarketing());
            ps.setInt(5, entity.getAge());
            ps.setInt(6, entity.getNotRealpage());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con,ps);
        }
        return 0;
    }
    //회원가입
    public static int insUser(UserDTO dto){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into cookit_user(email, pw, nm, gender, birthdaymm, birthdaydd, joinpath) " +
                "values(?, ?, ?, ?, ?, ?, ?)";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, dto.getEmail());
            ps.setString(2, dto.getPw());
            ps.setString(3, dto.getNm());
            ps.setInt(4, dto.getGender());
            ps.setString(5, dto.getBirthdaymm());
            ps.setString(6, dto.getBirthdaydd());
            ps.setInt(7, dto.getJoinpath());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con,ps);
        }
        return 0;
    }
    // 로그인
    public static UserVo loginUser(UserDTO dto){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select userpk, email, pw, nm, joinpath, ukey from cookit_user where ";
                if(dto.getEmail() != null){
                    sql += "email = ?";
                }else {
                    sql += "userpk = ?";
                }

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            if(dto.getEmail() != null){
                ps.setString(1, dto.getEmail());
            }else {
                ps.setInt(1, dto.getUserpk());
            }

            rs = ps.executeQuery();

            if(rs.next()) {
                UserVo vo = new UserVo();
                vo.setUserpk(rs.getInt("userpk"));
                vo.setEmail(rs.getString("email"));
                vo.setPw(rs.getString("pw"));
                vo.setNm(rs.getString("nm"));
                vo.setJoinpath(rs.getInt("joinpath"));
                vo.setUkey(rs.getString("ukey"));

                return vo;

            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con,ps,rs);
        }
        return null;
    }

    // 이메일 체크
    public static boolean checkEmail(UserDTO dto){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select userpk from cookit_user where email = ?";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, dto.getEmail());
            rs = ps.executeQuery();

            if(rs.next()) {
                // 있으면 false
                return false;

            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con,ps,rs);
        }
        return true;
    }

    public static int updKey(UserVo vo, String key){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "UPDATE cookit_user SET ukey = ? WHERE userpk = ?;";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, key);
            ps.setInt(2, vo.getUserpk());
            return ps.executeUpdate();

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con,ps,rs);
        }
        return 0;
    }
}
