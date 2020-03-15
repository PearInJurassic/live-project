package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Project Name:live-project2

 * File Name:DispatcherHandle.java
 * Package Name:
 * Date:下午5:25:44
 * Copyright (c) 2020, Doris All Rights Reserved.
 *
*/

/**
 * Description: <br/>
 * Date: 下午5:25:44 <br/>
 * 
 * @author Doris
 * @version
 * @see
 */
public class DispatcherHandle {
    static Set<Integer> hs = new HashSet<Integer>();// hashset存放取到的orderid,不会重复

    static int sum;

    static Statement statement;

    static int cnt = 1; // 计数表名后的数字

    static String tableName = "dispatch" + cnt; // 表名

    static String drawName = "orders" + cnt;// 抽取的名字

    // 点击结束预约后，创建一张dispatcher表
    public void createTable() {
        String sql;
        try (Connection c = DBUtil.getConnection()) {
            while (true) {
                ResultSet rs = c.getMetaData().getTables(null, null, tableName, null);
                if (rs.next()) {
                    cnt++;
                    tableName = "orders" + cnt;
                } else {
                    sql = "create table " + tableName + "(dispatchid int primary key," + "uid varchar(18) not null,"
                            + "uname varchar(45) not null," + "utel varchar(45) not null," + "masknum int not null)";
                    break;
                }
            }
            PreparedStatement ps = c.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 下一次开始预约时，结束该表数据的添加
    public void finishTable() {
        cnt++;
        tableName = "dispatch" + cnt;
        // 清空hashset
        hs.clear();
    }

    public void addToDispatcherTable() {
        // 遍历hashset

        Iterator iterator = hs.iterator();// hs保存被抽到的预约表的id
        ResultSet rs;
        String uid = null, uname = null, utel = null;
        int masknum = 0;

        while (iterator.hasNext()) {

            int i = (int) iterator.next();// i就是预约表的编号
            String sql = "select * " + drawName + " where orderid=" + i;// 依次取出预约表里的数据
            try {
                rs = statement.executeQuery(sql);
                uid = rs.getString("uid");
                uname = rs.getString("uname");
                utel = rs.getString("utel");
                masknum = rs.getInt("masknum");
                DispatcherInfo info = new DispatcherInfo(i, uid, uname, utel, masknum);
                add(info);
            } catch (SQLException e) {

                // Auto-generated catch block
                e.printStackTrace();

            }

        }

    }

    // 存入dispatcher表
    public void add(DispatcherInfo info) {
        String sql = "insert into " + tableName + " values(?, ?, ?, ?, ?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, info.getDispatcherId()); // 预约表编号
            ps.setString(2, info.getUid()); // 身份证号
            ps.setString(3, info.getUname()); // 姓名
            ps.setString(4, info.getuTel()); // 手机号码
            ps.setInt(5, info.getMasknum()); // 口罩数量
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 抽取ordersX里面的数据
    public void draw() {
        // 先查找总数
        String sql = "select count(*) from " + drawName;
        try {
        	Connection c = DBUtil.getConnection();
        	statement = c.createStatement();
        	ResultSet rs = statement.executeQuery(sql);
        	rs.last();
            sum = rs.getRow();// sum保存该次预约的总条数
        } catch (SQLException e) {

            // Auto-generated catch block
            e.printStackTrace();

        }

        // 从总数里面抽取100条
        Random rm = new Random(1);

        while (hs.size() != 10) {
        	
            hs.add(rm.nextInt(sum) + 1);
        }
    }
}
