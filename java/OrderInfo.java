package pojo;

public class OrderInfo {
	private int orderid;  //预约编号
	private String uid;   //身份证号
	private String uname; //姓名
	private String utel;  //手机号码
	private int masknum;  //口罩数量
	
	public int getOrderid() {
		return orderid;
	}
	
	public void setOrderid(int id) {
		orderid = id;
	}
	
	public String getUid() {
		return uid;
	}
	
	public void setUid(String id) {
		uid = id;
	}
	
	public String getUname() {
		return uname;
	}
	
	public void setUname(String name) {
		uname = name;
	}
	
	public String getUtel() {
		return utel;
	}
	
	public void setUtel(String tel) {
		utel = tel;
	}
	
	public int getMasknum() {
		return masknum;
	}
	
	public void setMasknum(int num) {
		masknum = num;
	}
}
