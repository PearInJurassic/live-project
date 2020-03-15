import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Judge {
	public static void main(String[] args) throws SQLException {
		String uid="",utel="";            			//从界面中获得的用户信息
		Connection conn=DBUtil.getConnection();		//建立连接
	    Statement stmt=conn.createStatement();   	//创建SQL容器
	    Boolean isSuccess=false;                    //判断前三次是否有预约成功
	    //表为dispatch1(最近的表)
	    String sql1="select masknum from dispatch1 where uid="+uid+" and utel="+utel+"";
	    ResultSet rs=stmt.executeQuery(sql1);   	//获得结果集
	    if(rs.getInt("id")>0)
	    	isSuccess=true;
	    String sql2="select masknum from dispatch2 where uid="+uid+" and utel="+utel+"";   	
	    rs=stmt.executeQuery(sql2);   				
	    if(rs.getInt("id")>0)
	    	isSuccess=true;
	    String sql3="select masknum from dispatch3 where uid="+uid+" and utel="+utel+"";   	
	    rs=stmt.executeQuery(sql3);   				
	    if(rs.getInt("id")>0)
	    	isSuccess=true;
	    DBUtil.close(rs,stmt,conn);
	}
}
