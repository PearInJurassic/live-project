package test;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class order extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					order frame = new order();
//					frame.setVisible(true);
//					frame.setResizable(false);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public order() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 759, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u53E3\u7F69\u9884\u7EA6");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(329, 10, 92, 65);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u4FE1\u606F\u767B\u8BB0\uFF1B");
		label_1.setFont(new Font("宋体", Font.BOLD, 12));
		label_1.setBounds(26, 67, 69, 27);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u771F\u5B9E\u59D3\u540D");
		label_2.setFont(new Font("宋体", Font.BOLD, 12));
		label_2.setBounds(223, 103, 60, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7");
		label_3.setFont(new Font("宋体", Font.BOLD, 12));
		label_3.setBounds(223, 153, 60, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u624B\u673A\u53F7\u7801");
		label_4.setFont(new Font("宋体", Font.BOLD, 12));
		label_4.setBounds(223, 200, 60, 15);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("\u9884\u7EA6\u6570\u91CF");
		label_5.setFont(new Font("宋体", Font.BOLD, 12));
		label_5.setBounds(223, 255, 60, 15);
		contentPane.add(label_5);
		
		textField = new JTextField();
		textField.setBounds(335, 100, 180, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(335, 150, 180, 21);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(335, 197, 180, 21);
		contentPane.add(textField_2);
		
		JComboBox <String>comboBox = new JComboBox<String>();
		comboBox.addItem("1");
		comboBox.addItem("2");
		comboBox.addItem("3");
		comboBox.setBounds(335, 252, 180, 21);
		contentPane.add(comboBox);
		
		//提交
		JButton btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//监听函数
				//返回上一个界面
				OrderInfo orderInfo = new OrderInfo();
				OrderHandle orderHandle = new OrderHandle();
				String uname = textField.getText();
				String uid = textField_1.getText();
				String utel = textField_2.getText();
				String masknum = (String)comboBox.getSelectedItem();
				if(!orderHandle.IsLegalOrderid(uid)) {
					JOptionPane.showMessageDialog(null, "身份证号无效",
							"预约失败", JOptionPane.ERROR_MESSAGE);
				} else if(!orderHandle.checkUtel(utel)) {
					JOptionPane.showMessageDialog(null, "手机号无效",
							"预约失败", JOptionPane.ERROR_MESSAGE);
				} else if(!orderHandle.checkMasknum(masknum)) {
					JOptionPane.showMessageDialog(null, "预约口罩数量最高可预约口罩数量",
							"预约失败", JOptionPane.ERROR_MESSAGE);
				} else if(!orderHandle.isRegister(uid, utel)) {
					JOptionPane.showMessageDialog(null, "该手机号或者身份证号已经在本次摇号登记过了",
							"预约失败", JOptionPane.ERROR_MESSAGE);
				} else if(!orderHandle.isWin(uid, utel)) {
					JOptionPane.showMessageDialog(null, "该手机号或者身份证号在此前三次预约中成功中签",
							"预约失败", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "预约成功,你的预约编号为" + orderHandle.getOrderiD(),
							"预约成功", JOptionPane.DEFAULT_OPTION);
					orderInfo.setUid(uid);
					orderInfo.setUname(uname);
					orderInfo.setUtel(utel);
					orderInfo.setMasknum(Integer.parseInt(masknum));
					orderHandle.add(orderInfo);
					setVisible(false);
				}
				
			}
		});
		btnNewButton.setBounds(305, 336, 143, 43);
		contentPane.add(btnNewButton);
		
		//返回主界面
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE\u4E3B\u754C\u9762");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//监听函数
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(600, 33, 124, 23);
		contentPane.add(btnNewButton_1);
	}
}
