package com.company;

import Util.DBUtil;

import java.sql.*;

public class BookRun {
    BookInfo bookInfo;
    int times;
    BookRun(BookInfo bookInfo,int times){
        this.bookInfo=bookInfo;
        this.times=times;
    }
    public boolean check(){
        Connection con = null;
        try {
            con=DBUtil.getConnection();
            Statement stmt=con.createStatement();
            String sql="select uid utel from orders"+times+" where uid = ? or utel = ?" ;
            PreparedStatement pStmt=con.prepareStatement(sql);
            pStmt.setString(1,bookInfo.id);
            pStmt.setString(2,bookInfo.phone);
            //System.out.println(pStmt.);
            ResultSet rs=pStmt.executeQuery();
            if(rs.next()){
                DBUtil.close(rs,pStmt,con);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
