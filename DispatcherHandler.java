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
 * Date:����1:55:18
 * Copyright (c) 2020, Doris All Rights Reserved.
 *
*/

/**
 * Description: <br/>
 * Date: ����1:55:18 <br/>
 * 
 * @author Doris
 * @version
 * @see
 */
public class DispatcherHandler {
    int num;// ��ȡ������

    int sum;// ԤԼ������

    Connection conn;

    Statement statement;

    Set<Integer> hs = new HashSet<Integer>();// ����hashset�в��ظ�

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
        // ��ȡ���е�ԤԼ��Ϣ������ԤԼ��������
        String sql = "select count(*) from orders";
        try {
            ResultSet rs = statement.executeQuery(sql);
            sum = rs.getInt(1);
        } catch (SQLException e) {

            // Auto-generated catch block
            e.printStackTrace();

        }

        // �����������������ȡ100��

        Random rm = new Random(1);

        while (hs.size() != 100) {

            hs.add(rm.nextInt(sum) + 1);
        }

    }

}
