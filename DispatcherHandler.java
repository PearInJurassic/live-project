import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Project Name:live-project

 * File Name:DispatcherHandler.java
 * Package Name:
 * Date:下午1:55:18
 * Copyright (c) 2020, Doris All Rights Reserved.
 *
*/

/**
 * Description: <br/>
 * Date: 下午1:55:18 <br/>
 * 
 * @author Doris
 * @version
 * @see
 */
public class DispatcherHandler {
    int num;// 抽取的数量

    int sum;// 预约的总数

    Connection conn;

    Statement statement;

    Set<Integer> hs = new HashSet<Integer>();// 存入hashset中不重复

    public DispatcherHandler() {
        num = 100;
        try {
            conn = DBUtil.getConnection();
        } catch (SQLException e) {

            // Auto-generated catch block
            e.printStackTrace();

        }

    }

    public void draw() {

        try {
            statement = conn.createStatement();
        } catch (SQLException e) {

            // Auto-generated catch block
            e.printStackTrace();

        }
        // 获取所有的预约信息，返回预约的总数量
        String sql = "select count(*) from orders";
        try {
            ResultSet rs = statement.executeQuery(sql);
            sum = rs.getInt(1);
        } catch (SQLException e) {

            // Auto-generated catch block
            e.printStackTrace();

        }

        // 在总数里面中随机抽取100个

        Random rm = new Random(1);

        while (hs.size() != 100) {

            hs.add(rm.nextInt(sum) + 1);
        }

    }

}
