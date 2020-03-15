package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import test.DBUtil;

public class OrderHandle {
	
	/*
	 * 将预约信息添加进数据库
	 * param info
	 */
	public void add(OrderInfo info) {
		String sql = "insert into orders values(?, ?, ?, ?, ?)";
		try (Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, info.getOrderid());        //预约编号
			ps.setString(2, info.getUid()); //身份证号
			ps.setString(3, info.getUname()); //姓名
			ps.setString(4, info.getUtel()); //手机号码
			ps.setInt(5, info.getMasknum());        //口罩数量
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 简单校验身份证号码是否正确
	 * param orderid
	 * return boolean
	 */
	public static boolean checkOrderid(String orderid) {
		
		// 身份证号码必须为数字(18位的新身份证最后一位可以是x)
		Pattern pt = Pattern.compile("(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)");
		Matcher mt = pt.matcher(orderid);
		if (!mt.find())
			return false;
		
		// 验证生日是否合法
		String strYear = orderid.substring(6, 10);// 年份
        String strMonth = orderid.substring(10, 12);// 月份
        String strDay = orderid.substring(12, 14);// 日期
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
            sum = sum + Integer.parseInt(String.valueOf(orderid.charAt(i)))
            		* Integer.parseInt(Wi[i]);
        }
        int modValue = sum % 11;
        String strVerifyCode = VarifyCode[modValue];
        if(!strVerifyCode.equals(orderid.substring(17, 18).toUpperCase()))
        	return false;
		
		return true;
	}
	
	/*
	 * 简单的手机号码验证
	 * param utel
	 * return boolean
	 */
	public static boolean checkUtel(String utel) {
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
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
	 * 验证口罩数量
	 * param masknum
	 * return boolean
	 */
	public static boolean checkMasknum(String masknum) {
		int Max = 3; //如果需要设置总量再改此处
		int num = Integer.parseInt(masknum);
		if(num > Max)
			return false;
		return true;
	}
	
	
}
