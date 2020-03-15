package 团队训练;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class mainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow frame = new mainWindow();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainWindow() {
		//setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u53E3\u7F69\u9884\u7EA6\u7CFB\u7EDF");
		label.setFont(new Font("宋体", Font.BOLD, 25));
		label.setBounds(247, 9, 193, 58);
		contentPane.add(label);
		
		//口罩预约
		JButton bt1 = new JButton("\u53E3\u7F69\u9884\u7EA6");
		bt1.setFont(new Font("宋体", Font.BOLD, 12));
		bt1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
				//预约按钮监听函数
				new order();
		}
		});
		bt1.setBounds(67, 288, 128, 65);
		contentPane.add(bt1);
		
		//查询中签
		JButton button = new JButton("\u67E5\u8BE2\u4E2D\u7B7E");
		button.setFont(new Font("宋体", Font.BOLD, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
					//监听函数
					new search();
			}
			});
		button.setBounds(444, 288, 128, 65);
		contentPane.add(button);
		
		JLabel label_1 = new JLabel("\u8BBE\u7F6E\u53E3\u7F69\u603B\u91CF\uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 15));
		label_1.setBounds(167, 77, 128, 43);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(340, 88, 185, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u529F\u80FD\u6A21\u5757");
		label_2.setFont(new Font("宋体", Font.BOLD, 25));
		label_2.setBounds(265, 218, 133, 35);
		contentPane.add(label_2);
		
		//开始预约
		JButton button_2 = new JButton("\u5F00\u59CB\u9884\u7EA6");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//监听函数
			}
		});
		button_2.setFont(new Font("宋体", Font.BOLD, 12));
		button_2.setBounds(67, 143, 128, 65);
		contentPane.add(button_2);
		
		//结束预约
		JButton button_1 = new JButton("\u7ED3\u675F\u9884\u7EA6");
		button_1.setFont(new Font("宋体", Font.BOLD, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
					//监听函数
			}
			});
		button_1.setBounds(444, 143, 128, 65);
		contentPane.add(button_1);
	}
}
