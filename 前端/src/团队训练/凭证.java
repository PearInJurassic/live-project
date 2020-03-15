package 团队训练;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class 凭证 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					凭证 frame = new 凭证();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public 凭证() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 427);
		contentPane = new JPanel();
		contentPane.setToolTipText("\u51ED\u8BC1\u754C\u9762");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//
		JLabel label = new JLabel("\u8D2D\u4E70\u51ED\u8BC1");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(205, 10, 91, 52);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u771F\u5B9E\u59D3\u540D\uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 12));
		label_1.setBounds(130, 77, 70, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		label_2.setFont(new Font("宋体", Font.BOLD, 12));
		label_2.setBounds(130, 131, 70, 18);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u624B\u673A\u53F7\u7801\uFF1A");
		label_3.setFont(new Font("宋体", Font.BOLD, 12));
		label_3.setBounds(130, 187, 70, 18);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u9884\u7EA6\u6570\u91CF\uFF1A");
		label_4.setFont(new Font("宋体", Font.BOLD, 12));
		label_4.setBounds(130, 245, 70, 18);
		contentPane.add(label_4);
		
		//确认
		JButton button = new JButton("\u786E\u8BA4");
		button.setFont(new Font("宋体", Font.BOLD, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
					//预约按钮监听函数
					setVisible(false);
			}
			});
		button.setBounds(181, 310, 142, 42);
		contentPane.add(button);
		
		//返回主界面
		JButton button_1 = new JButton("\u8FD4\u56DE\u4E3B\u754C\u9762");
		button_1.setFont(new Font("宋体", Font.BOLD, 12));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
					//监听函数
					setVisible(false);
			}
			});
		button_1.setBounds(365, 21, 129, 29);
		contentPane.add(button_1);
		
		//真实姓名
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 12));
		lblNewLabel.setBounds(283, 78, 211, 16);
		contentPane.add(lblNewLabel);
		
		//身份证号码
		JLabel label_5 = new JLabel("New label");
		label_5.setFont(new Font("宋体", Font.BOLD, 12));
		label_5.setBounds(283, 132, 211, 16);
		contentPane.add(label_5);
		
		//手机号码
		JLabel label_6 = new JLabel("New label");
		label_6.setFont(new Font("宋体", Font.BOLD, 12));
		label_6.setBounds(283, 188, 211, 16);
		contentPane.add(label_6);
		
		//预约数量
		JLabel label_7 = new JLabel("New label");
		label_7.setFont(new Font("宋体", Font.BOLD, 12));
		label_7.setBounds(283, 246, 211, 16);
		contentPane.add(label_7);
	}
}
