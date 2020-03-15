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
	
	static int cnt = 1; //���������������
	static String tableName = "orders" + cnt; //����
	
	static public String[] table = {"1", "2", "3"};
	
	public OrderHandle() {
		String t;
		int lastest = 0;
		try (Connection c = DBUtil.getConnection()){
            Statement stmt=c.createStatement();
			//��ȡȫ������
            String sql="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                t = rs.getString("TABLE_NAME");
                if(t.startsWith("dispatch")){
                    int temp = Integer.parseInt(t.substring(8));
                    if(temp > lastest)
                        lastest = temp;//��dispatch��ͷ�ı��к�����������
                }
            }
            for(int i = 0; i < 3 && lastest - i > 0; i++)
            	table[i] = "dispatch" + String.valueOf(lastest - i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * �����ʼԤԼ�½�ԤԼ��
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
	 * �������ԤԼ���ű���Ϣ�̶����ٸ��ģ����������ű�
	 */
	public void finishTable() {
		cnt++;
		tableName = "orders" + cnt;
		for(int i = 0; i < 3; i++)
        	table[i] = "dispatch" + Integer.parseInt(table[i].substring(8));
	}
	
	/*
	 * ��ԤԼ��Ϣ��ӽ����ݿ�
	 * param info
	 */
	public void add(OrderInfo info) {
		String sql = "insert into " + tableName + " values(?, ?, ?, ?, ?)";
		try (Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, info.getOrderid());	//ԤԼ����
			ps.setString(2, info.getUid());		//���֤��
			ps.setString(3, info.getUname());	//����
			ps.setString(4, info.getUtel());	//�ֻ�����
			ps.setInt(5, info.getMasknum());	//��������
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * ��У�����֤�����Ƿ�Ϸ�
	 * param orderid
	 * return boolean
	 */
	public boolean IsLegalOrderid(String uid) {
		
		// ���֤�������Ϊ����(18λ�������֤���һλ������x)
		Pattern pt = Pattern.compile("(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)");
		Matcher mt = pt.matcher(uid);
		if (!mt.find())
			return false;
		
		// ��֤�����Ƿ�Ϸ�
		String strYear = uid.substring(6, 10);// ���
        String strMonth = uid.substring(10, 12);// �·�
        String strDay = uid.substring(12, 14);// ����
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
	 * �򵥵��ֻ�������֤
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
	 * ��֤�����û���߿�ԤԼ���������Ƿ񳬳�
	 * param masknum
	 * return boolean
	 */
	public boolean checkMasknum(String masknum) {
		int Max = 3; //�����Ҫ���������ٸĴ˴�
		int num = Integer.parseInt(masknum);
		if(num > Max)
			return false;
		return true;
	}
	
	/*
	 * �����û��Ƿ��ѵǼǣ��ǣ�����false��
	 * String uid, String utel
	 * return boolean
	 */
	public boolean isRegister(String uid, String utel) {
		String sql = "select * from " + tableName + " where orderid = '" + uid + "'"
					+" or utel = '" + utel +"'";
		try (Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			if(rs.next()) { //�������˵���ѵǼ�
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/*
	 * �����û��Ƿ���ǰ������ǩ�����ǣ�����false��
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
    			if(rs.next()) { //�������˵������ǩ
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
