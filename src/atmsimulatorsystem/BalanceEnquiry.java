package atmsimulatorsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.util.*;


class BalanceEnquiry extends JFrame implements ActionListener {

    JTextField t1, t2;
    JButton b1, b2, b3;
    JLabel l1, l2, l3;
    String pin;

    BalanceEnquiry(String pin) {
        this.pin = pin;

//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ASimulatorSystem/icons/atm.jpg"));
//        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel l3 = new JLabel(i3);
//        l3.setBounds(0, 0, 960, 1080);
//        add(l3);

        
        
		l1 = new JLabel();
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("Raleway", Font.BOLD, 14));
        
        

        b1 = new JButton("BACK");

        setLayout(null);

        l1.setBounds(20, 80, 300, 20);
        add(l1);

        b1.setBounds(120, 170, 80, 30);
        add(b1);
        
        
        
        
        
        int balance = 0;
        
        try{
        	//JOptionPane.showInputDialog("enter your pin");
            Conn c1 = new Conn();
            String q = "select * from bank where pin = '"+pin+"'";
            ResultSet rs = c1.s.executeQuery(q);
            while (rs.next()) {
                if (rs.getString("mode").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
                l1.setText("Your Current Account Balance is Rs "+balance);  
            }
            l1.setText("Your Current Account Balance is Rs "+balance);
        }catch(Exception e){
        	System.out.println(e);
        }
        
        l1.setText("Your Current Account Balance is Rs "+balance);
        
        //JOptionPane.showMessageDialog(null,"Your Current Account Balance is Rs "+balance );

        b1.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);

        setSize(400,600);
        setLocation(20,20);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transaction(pin).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("").setVisible(true);
    }

}
