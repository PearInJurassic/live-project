package test;

public class test {

	public static void main(String[] args) {
		OrderInfo orderInfo = new OrderInfo();
		OrderHandle orderHandle = new OrderHandle();
		/*
		orderInfo.setUid("350105199908141515");
		orderInfo.setUtel("13235907290");
		orderInfo.setUname("陈炎");
		orderInfo.setMasknum(2);
		orderHandle.add(orderInfo);
		orderInfo.setUid("11010119900307387X");
		orderInfo.setUtel("15060003014");
		orderInfo.setUname("陈炎");
		orderInfo.setMasknum(2);
		orderHandle.add(orderInfo);
		*/
		//orderOp.add(orderInfo);
		//System.out.println(OrderHandle.checkOrderid("11010119900307467x"));
		//System.out.println(OrderHandle.checkUtel("11111115678"));
		//System.out.println(OrderHandle.checkMasknum("678"));
		//System.out.println(orderHandle.isRegister("1234", "12345"));
		System.out.println(orderHandle.isWin("1233", "1233"));
		//orderHandle.createTable();
		/*
		orderInfo.setOrderid(1);
		orderInfo.setUid("1234");
		orderInfo.setUname("abcd");
		orderInfo.setUtel("12345");
		orderInfo.setMasknum(2);
		orderHandle.add(orderInfo);
		*/
		//DispatcherHandle dispatcherHandle = new DispatcherHandle();
		//dispatcherHandle.draw();
	}

}
