package pojo;

public class OrderInfo {
	private int orderid;  //ԤԼ���
	private String uid;   //���֤��
	private String uname; //����
	private String utel;  //�ֻ�����
	private int masknum;  //��������
	
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
