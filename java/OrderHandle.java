package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import test.DBUtil;

public class OrderHandle {
	
	static int cnt = 1; //计数表名后的数字
	static String tableName = "orders" + cnt; //表名
	
	static public String[] table = {"1", "2", "3"};
	
	public OrderHandle() {
		String t;
		int lastest = 0;
		try (Connection c = DBUtil.getConnection()){
            Statement stmt=c.createStatement();
			//获取全部表名
            String sql="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                t = rs.getString("TABLE_NAME");
                if(t.startsWith("dispatch")){
                    int temp = Integer.parseInt(t.substring(8));
                    if(temp > lastest)
                        lastest = temp;//以dispatch开头的表中后面数字最大的
                }
            }
            for(int i = 0; i < 3 && lastest - i > 0; i++)
            	table[i] = "dispatch" + String.valueOf(lastest - i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 点击开始预约新建预约表
	 */
	public void createTable() {
		String sql;
		try (Connection c = DBUtil.getConnection()){
			while(true) {
				ResultSet rs = c.getMetaData().getTables(null, null, tableName, null);
				if (rs.next()) {
					cnt++;
					tableName = "orders" + cnt;
				} else{
					sql = "create table " + tableName
							+ "(orderid int primary key,"
							+ "uid varchar(18) not null,"
							+ "uname varchar(45) not null,"
							+ "utel varchar(45) not null,"
							+ "masknum int not null)"; 
					break;
				}
			}
			PreparedStatement ps = c.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 点击结束预约这张表信息固定不再更改（即跳过这张表）
	 */
	public void finishTable() {
		cnt++;
		tableName = "orders" + cnt;
		for(int i = 0; i < 3; i++)
        	table[i] = "dispatch" + Integer.parseInt(table[i].substring(8));
	}
	
	/*
	 * 将预约信息添加进数据库
	 * param info
	 */
	public void add(OrderInfo info) {
		String sql = "insert into " + tableName + " values(?, ?, ?, ?, ?)";
		try (Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, info.getOrderid());	//预约表编号
			ps.setString(2, info.getUid());		//身份证号
			ps.setString(3, info.getUname());	//姓名
			ps.setString(4, info.getUtel());	//手机号码
			ps.setInt(5, info.getMasknum());	//口罩数量
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 简单校验身份证号码是否合法
	 * param orderid
	 * return boolean
	 */
	public boolean IsLegalOrderid(String uid) {
		
		// 身份证号码必须为数字(18位的新身份证最后一位可以是x)
		Pattern pt = Pattern.compile("(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)");
		Matcher mt = pt.matcher(uid);
		if (!mt.find())
			return false;
		
		// 验证生日是否合法
		String strYear = uid.substring(6, 10);// 年份
        String strMonth = uid.substring(10, 12);// 月份
        String strDay = uid.substring(12, 14);// 日期
        String birthday = strYear + "-" + strMonth + "-" + strDay;
        //括号内为日期格式，y代表年份，M代表年份中的月份（为避免与小时中的分钟数m冲突，此处用M），d代表月份中的天数
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		try {
			sd.setLenient(false);//此处指定日期/时间解析是否不严格，在true是不严格，false时为严格
			sd.parse(birthday);//从给定字符串的开始解析文本，以生成一个日期
		}
		catch (Exception e) {
			return false;
		}
		
		//判断第18位校验码是否正确
		String[] VarifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3",
        				"7", "9","10", "5", "8", "4", "2" };
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum = sum + Integer.parseInt(String.valueOf(uid.charAt(i)))
            		* Integer.parseInt(Wi[i]);
        }
        int modValue = sum % 11;
        String strVerifyCode = VarifyCode[modValue];
        if(!strVerifyCode.equals(uid.substring(17, 18).toUpperCase()))
        	return false;
		
		return true;
	}
	
	/*
	 * 简单的手机号码验证
	 * param utel
	 * return boolean
	 */
	public boolean checkUtel(String utel) {
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|"
				+ "(17[013678])|(18[0,5-9]))\\d{8}$";
		if(utel.length() != 11)
			return false;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(utel);
		boolean isMatch = m.matches();
		if(isMatch)
			return true;
		else 
			return false;
	}
	
	/*
	 * 验证单个用户最高可预约口罩数量是否超出
	 * param masknum
	 * return boolean
	 */
	public boolean checkMasknum(String masknum) {
		int Max = 3; //如果需要设置总量再改此处
		int num = Integer.parseInt(masknum);
		if(num > Max)
			return false;
		return true;
	}
	
	/*
	 * 检查该用户是否已登记（是：返回false）
	 * String uid, String utel
	 * return boolean
	 */
	public boolean isRegister(String uid, String utel) {
		String sql = "select * from " + tableName + " where orderid = '" + uid + "'"
					+" or utel = '" + utel +"'";
		try (Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			if(rs.next()) { //如果存在说明已登记
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/*
	 * 检查该用户是否再前三次中签过（是：返回false）
	 * String uid, String utel
	 * return boolean
	 */
	public boolean isWin(String uid, String utel){
        PreparedStatement ps;
        String sql;
        ResultSet rs;
        try (Connection c = DBUtil.getConnection()){
            for(int i = 0; i < 3; i++) {
                sql = "select * from " + table[i] + " where dispatchid = '" + uid + "'"
    					+" or utel = '" + utel +"'";
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
    			if(rs.next()) { //如果存在说明已中签
    				return false;
    			}
    			rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
