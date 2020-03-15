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

public class 查询界面 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					查询界面 frame = new 查询界面();
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
	public 查询界面() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 271);
		contentPane = new JPanel();
		contentPane.setToolTipText("\u67E5\u8BE2\u754C\u9762");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u67E5\u8BE2\u754C\u9762");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(166, 10, 116, 36);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u9884\u7EA6\u7F16\u53F7\uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 12));
		label_1.setBounds(91, 93, 80, 15);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(190, 90, 162, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//确认查询
		JButton button = new JButton("\u786E\u8BA4\u67E5\u8BE2");
		button.setBounds(145, 159, 137, 44);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
					//预约按钮监听函数
					JFrame vv=new 凭证();
					vv.setVisible(true);
			}
			});
		contentPane.add(button);
		
		//返回主界面
		JButton button_1 = new JButton("\u8FD4\u56DE\u4E3B\u754C\u9762");
		button_1.setBounds(292, 10, 114, 23);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//监听函数
				//返回上一个界面
				setVisible(false);
			}
		});
		contentPane.add(button_1);
	}
}
