package bank.managment.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    
    JButton a,b,c,d,e,f,back;
    String pinnumber;
    FastCash(String pinnumber){
        this.pinnumber=pinnumber;
        setLayout(null);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);
        
        JLabel text=new JLabel("SELECT WITHDRAWL AMOUNT");
        text.setBounds(210,300,700,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text);
        
        a=new JButton("Rs 100");
        a.setBounds(170,415,150,30);
        a.addActionListener(this);
        image.add(a);
        
        b=new JButton("Rs 500");
        b.setBounds(355,415,150,30);
        b.addActionListener(this);
        image.add(b);
        
        c=new JButton("Rs 1000");
        c.setBounds(170,450,150,30);
        c.addActionListener(this);
        image.add(c);
        
        d=new JButton("Rs 2000");
        d.setBounds(355,450,150,30);
        d.addActionListener(this);
        image.add(d);
        
        e=new JButton("Rs 5000");
        e.setBounds(170,485,150,30);
        e.addActionListener(this);
        image.add(e);
        
        f=new JButton("Rs 10000");
        f.setBounds(355,485,150,30);
        f.addActionListener(this);
        image.add(f);
        
        back=new JButton("Exit");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);
        
        
        
        

        
        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
       
    }
    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }else{
            String amount=((JButton)ae.getSource()).getText().substring(3);
            Conn c = new Conn();
            try{
               ResultSet rs=c.s.executeQuery("select * from bank where pin='"+pinnumber+"' "); 
               int balance=0;
               while(rs.next()){
                   if(rs.getString("type").equals("Deposit")){
                       balance+=Integer.parseInt(rs.getString("amount"));
                   }else{
                       balance-=Integer.parseInt(rs.getString("amount"));
                   }
               }
               
               if(ae.getSource()!=back&&balance<Integer.parseInt(amount)){
                  JOptionPane.showMessageDialog(null,"Insufficient Balance");
                  return;
               }
             Date date =new Date();
             String query="insert into bank values('"+pinnumber+"', '"+date+"','Withdrawl','"+amount+"')";
            c.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Rs"+amount+"Debited Sucessfully");
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
            }catch(Exception e){
                System.out.println(e);
            }
            
        }
    }
    public static void main(String args[]){
        new Transactions("");
    }

   
}

