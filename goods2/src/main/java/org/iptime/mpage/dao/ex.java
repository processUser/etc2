package org.iptime.mpage.dao;

import org.iptime.mpage.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ex {
    public static void aaaa() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";

        try {

        }catch (Exception e) {

        }finally {
            DbUtils.close(con,ps,rs);
        }
    }
}
