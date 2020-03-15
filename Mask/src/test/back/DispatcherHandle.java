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
 * Date:����5:25:44
 * Copyright (c) 2020, Doris All Rights Reserved.
 *
*/

/**
 * Description: <br/>
 * Date: ����5:25:44 <br/>
 * 
 * @author Doris
 * @version
 * @see
 */
public class DispatcherHandle {
    static Set<Integer> hs = new HashSet<Integer>();// hashset���ȡ����orderid,�����ظ�

    static int sum;

    static Statement statement;

    static int cnt = 1; // ���������������

    static String tableName = "dispatch" + cnt; // ����

    static String drawName = "orders" + cnt;// ��ȡ������

    // �������ԤԼ�󣬴���һ��dispatcher��
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

    // ��һ�ο�ʼԤԼʱ�������ñ����ݵ����
    public void finishTable() {
        cnt++;
        tableName = "dispatch" + cnt;
        drawName = "orders" + cnt;
        // ���hashset
        hs.clear();
    }

    public void addToDispatcherTable() {
        // ����hashset

        Iterator iterator = hs.iterator();// hs���汻�鵽��ԤԼ���id
        ResultSet rs;
        String uid = null, uname = null, utel = null;
        int masknum = 0;

        while (iterator.hasNext()) {

            int i = (int) iterator.next();// i����ԤԼ��ı��
            String sql = "select * from " + drawName + " where orderid = '" + i + "'";// ����ȡ��ԤԼ���������
            try {
            	Connection c = DBUtil.getConnection();
            	PreparedStatement ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()) {
                	uid = rs.getString("uid");
                    uname = rs.getString("uname");
                    utel = rs.getString("utel");
                    masknum = rs.getInt("masknum");
                }
                DispatcherInfo info = new DispatcherInfo(i, uid, uname, utel, masknum);
                add(info);
            } catch (SQLException e) {

                // Auto-generated catch block
                e.printStackTrace();

            }

        }

    }

    // ����dispatcher��
    public void add(DispatcherInfo info) {
        String sql = "insert into " + tableName + " values(?, ?, ?, ?, ?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, info.getDispatcherId()); // ԤԼ����
            ps.setString(2, info.getUid()); // ���֤��
            ps.setString(3, info.getUname()); // ����
            ps.setString(4, info.getuTel()); // �ֻ�����
            ps.setInt(5, info.getMasknum()); // ��������
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 // 抽取ordersX里面的数据
    public void draw(int num) {
        // 先查找总数
        String sql = "select count(*) from " + drawName;
        try {
            Connection c = DBUtil.getConnection();
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.last();
            sum = rs.getRow();// sum保存该次预约的总条数
            System.out.println(sum);
        } catch (SQLException e) {

            // Auto-generated catch block
            e.printStackTrace();

        }

        // 从总数里面抽取100条
        Random rm = new Random(1);

        while (hs.size() != num) {
	    if(n == 0)
        	break;
            n--;
            hs.add(rm.nextInt(sum) + 1);
        }
    }
    
    // 查询
    public DispatcherInfo isDispatched(String did) {
        String uid = null, uname = null, utel = null;
        String n = did.split("-")[0];
        int id = Integer.parseInt(did.split("-")[1]);
        int masknum = 0;
        String table = "dispatch" + n;
        String sql = "select * from " + table + " where dispatchid=" + id;
        try {
            Connection c = DBUtil.getConnection();
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                uid = rs.getString("uid");
                uname = rs.getString("uname");
                utel = rs.getString("utel");
                masknum = rs.getInt("masknum");
                DispatcherInfo info = new DispatcherInfo(id, uid, uname, utel, masknum);
                return info;
            }

        } catch (SQLException e) {

            // Auto-generated catch block
            e.printStackTrace();

        }
        return null;
    }
}
