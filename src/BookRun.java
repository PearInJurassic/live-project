package com.company;

import Util.DBUtil;

import java.sql.*;

public class BookRun {
    BookInfo bookInfo;
    int times;//表名后面的数字
    BookRun(BookInfo bookInfo,int times){
        this.bookInfo=bookInfo;
        this.times=times;
    }
	
	//参数是表名，判断这张表里是否出现过bookInfo的手机和身份证号.
    private boolean check(String table){
        Connection con = null;
        try {
            con=DBUtil.getConnection();
            String sql="select uid utel from "+table+" where uid = ? or utel = ?" ;
            PreparedStatement pStmt=con.prepareStatement(sql);
            pStmt.setString(1,bookInfo.id);
            pStmt.setString(2,bookInfo.phone);
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
	
	//往上面这个函数传入当前的预约表和前三张发放表，不足三张的就全都传进去
    public boolean cheackAvailale(){
        Connection con = null;
        int lastest=0;
        try {
            con=DBUtil.getConnection();
            Statement stmt=con.createStatement();
			//获取全部表名
            String sql="SELECT TABLE_NAME,TABLE_ROWS FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='mask'";
            ResultSet rs = stmt.executeQuery(sql);
            String tableName;
            while (rs.next()) {
                tableName = rs.getString("TABLE_NAME");
                if(tableName.startsWith("dispatch")){
                    int temp=Integer.parseInt(tableName.substring(8));
                    if(temp>lastest)
                        lastest=temp;//以dispatch开头的表中后面数字最大的
                }
            }
            if(!check("orders"+String.valueOf(times)))
                return false;
            for(int i=1;i<4&&lastest-i>0;i++){
                if(!check("dispatch"+String.valueOf(+lastest-i)))
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
