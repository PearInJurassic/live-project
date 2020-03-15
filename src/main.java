import Util.DBUtil;
import java.sql.*;

public class main {

    public static void main(String[] args) {
        /*
        try{
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");
        }catch(ClassNotFoundException e1){
            System.out.println("找不到MySQL驱动!");
            e1.printStackTrace();
        }

        String url="jdbc:mysql://localhost:3306/tmall?useSSL=false&serverTimezone=UTC";    //JDBC的URL
        //调用DriverManager对象的getConnection()方法，获得一个Connection对象
        Connection conn;
        try {
            conn = DriverManager.getConnection(url,    "root","ubuntu16");
            //创建一个Statement对象
            Statement stmt = conn.createStatement();
            System.out.println("成功连接到数据库！");
            String sql = "select * from user;";
            ResultSet resultSet = stmt.executeQuery(sql);
            String name;
            while (resultSet.next()) {
                name = resultSet.getString("name");
                System.out.println("姓名：" + name);
            }
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }*/
        try {
            Connection con = DBUtil.getConnection();
            Statement stmt = con.createStatement();
            System.out.println("成功连接到数据库！");
            String sql = "select * from user;";
            ResultSet resultSet = stmt.executeQuery(sql);
            String name;
            while (resultSet.next()) {
                name = resultSet.getString("name");
                System.out.println("姓名：" + name);
            }
            DBUtil.close(resultSet,stmt,con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
