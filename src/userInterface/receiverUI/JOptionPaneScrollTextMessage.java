package userInterface.receiverUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JOptionPaneScrollTextMessage extends JFrame {
    private String msg;
    
    public JOptionPaneScrollTextMessage(String msg) {
       this.msg = msg;
       setSize(4200, 1000);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       show();
    }
    
    public void show() {
        JTextArea jta = new JTextArea(20, 40);
        jta.setText(msg);
        jta.setEditable(false);
        JScrollPane jsp = new JScrollPane(jta);
        JOptionPane.showMessageDialog(null, jsp, "Email Content", JOptionPane.INFORMATION_MESSAGE , null);
     }
}