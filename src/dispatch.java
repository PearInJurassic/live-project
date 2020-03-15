import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dispatch {
    public dispatch() {
        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //在数据库中搜索是否中签
                //if中签，弹出对话框确定信息
                mssage dialog = new mssage();
                dialog.pack();
                dialog.setVisible(true);
                System.exit(0);
                //inputTextField.setText("done");
                //else
                JOptionPane.showMessageDialog(null,"抱歉您并未中签");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("dispatch");
        frame.setContentPane(new dispatch().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel panel1;
    private JTextField inputTextField;
    private JButton SearchButton;
}
