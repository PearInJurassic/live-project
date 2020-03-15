import javax.swing.*;

public class dispatch {
    public static void main(String[] args) {
        JFrame frame = new JFrame("dispatch");
        frame.setContentPane(new dispatch().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel panel1;
    private JButton SearchButton;
    private JTextField textField1;
    private JLabel Title;
    private JButton CancelButton;
}
