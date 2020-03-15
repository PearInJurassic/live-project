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
	 * ��ԤԼ��Ϣ��ӽ����ݿ�
	 * param info
	 */
	public void add(OrderInfo info) {
		String sql = "insert into orders values(?, ?, ?, ?, ?)";
		try (Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, info.getOrderid());        //ԤԼ���
			ps.setString(2, info.getUid()); //���֤��
			ps.setString(3, info.getUname()); //����
			ps.setString(4, info.getUtel()); //�ֻ�����
			ps.setInt(5, info.getMasknum());        //��������
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * ��У�����֤�����Ƿ���ȷ
	 * param orderid
	 * return boolean
	 */
	public static boolean checkOrderid(String orderid) {
		
		// ���֤�������Ϊ����(18λ�������֤���һλ������x)
		Pattern pt = Pattern.compile("(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)");
		Matcher mt = pt.matcher(orderid);
		if (!mt.find())
			return false;
		
		// ��֤�����Ƿ�Ϸ�
		String strYear = orderid.substring(6, 10);// ���
        String strMonth = orderid.substring(10, 12);// �·�
        String strDay = orderid.substring(12, 14);// ����
        String birthday = strYear + "-" + strMonth + "-" + strDay;
        //������Ϊ���ڸ�ʽ��y������ݣ�M��������е��·ݣ�Ϊ������Сʱ�еķ�����m��ͻ���˴���M����d�����·��е�����
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		try {
			sd.setLenient(false);//�˴�ָ������/ʱ������Ƿ��ϸ���true�ǲ��ϸ�falseʱΪ�ϸ�
			sd.parse(birthday);//�Ӹ����ַ����Ŀ�ʼ�����ı���������һ������
		}
		catch (Exception e) {
			return false;
		}
		
		//�жϵ�18λУ�����Ƿ���ȷ
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
	 * �򵥵��ֻ�������֤
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
	 * ��֤��������
	 * param masknum
	 * return boolean
	 */
	public static boolean checkMasknum(String masknum) {
		int Max = 3; //�����Ҫ���������ٸĴ˴�
		int num = Integer.parseInt(masknum);
		if(num > Max)
			return false;
		return true;
	}
	
	
}
