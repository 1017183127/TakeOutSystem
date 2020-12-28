package Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Database.DbUtil;

public class URegister extends JFrame {

	private JPanel contentPane;
	
	private String uaccount;
	private String upsd;
	private String uname;
	private String uaddress;
	private String uphonenum;
	

	public URegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420,200, 1080, 700);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(240,189,20));
		setContentPane(contentPane);
		setTitle("用户注册");
		setVisible(true);
		
		JLabel  lb_title=new JLabel("用户注册");
		JLabel lb_account=new JLabel("请输入账号:");
		JLabel lb_password=new JLabel("请输入密码:");
		JLabel lb_password2=new JLabel("请确认密码:");
		JLabel lb_uname=new JLabel("请输入您的称呼:");
		JLabel lb_uaddress=new JLabel("请输入配送地址:");
		JLabel lb_uphonenum=new JLabel("请输入手机号码:");
		
		JTextField tx_account=new JTextField();
		JPasswordField tx_password=new JPasswordField();
		JPasswordField tx_password2=new JPasswordField();
		JTextField tx_uname=new JTextField();
		JTextField tx_uaddress=new JTextField();
		JTextField tx_uphonenum=new JTextField();
		
		JButton bt_ok=new JButton("注册");
		bt_ok.setFont(new Font("Serif",Font.BOLD,20));
		bt_ok.setBackground(Color.WHITE);
		bt_ok.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		bt_ok.setBounds(450, 520, 200, 50);
		
		lb_title.setBounds(450, 50, 200, 50);
		lb_title.setFont(new Font("Serif",Font.BOLD,30));
		
		lb_account.setBounds(250, 125, 200, 50);
		lb_account.setFont(new Font("Serif",Font.BOLD,20));
		
		lb_password.setBounds(250, 185, 200, 50);
		lb_password.setFont(new Font("Serif",Font.BOLD,20));
		
		lb_password2.setBounds(250, 245, 200, 50);
		lb_password2.setFont(new Font("Serif",Font.BOLD,20));
		
		lb_uname.setBounds(250, 305, 200, 50);
		lb_uname.setFont(new Font("Serif",Font.BOLD,20));
		
		lb_uaddress.setBounds(250, 365, 200, 50);
		lb_uaddress.setFont(new Font("Serif",Font.BOLD,20));
		
		lb_uphonenum.setBounds(250,425, 200, 50);
		lb_uphonenum.setFont(new Font("Serif",Font.BOLD,20));
		
		
		tx_account.setBounds(450, 130, 350, 40);
		tx_account.setFont(new Font("Serif",Font.BOLD,20));
		tx_account.setBorder(BorderFactory.createEmptyBorder());
		
		tx_password.setBounds(450, 190, 350, 40);
		tx_password.setFont(new Font("Serif",Font.BOLD,20));
		tx_password.setBorder(BorderFactory.createEmptyBorder());
		
		tx_password2.setBounds(450, 250, 350, 40);
		tx_password2.setFont(new Font("Serif",Font.BOLD,20));
		tx_password2.setBorder(BorderFactory.createEmptyBorder());
		
		tx_uname.setBounds(450, 310, 350, 40);
		tx_uname.setFont(new Font("Serif",Font.BOLD,20));
		tx_uname.setBorder(BorderFactory.createEmptyBorder());
		
		tx_uaddress.setBounds(450, 370, 350, 40);
		tx_uaddress.setFont(new Font("Serif",Font.BOLD,20));
		tx_uaddress.setBorder(BorderFactory.createEmptyBorder());
		
		tx_uphonenum.setBounds(450,430, 350, 40);
		tx_uphonenum.setFont(new Font("Serif",Font.BOLD,20));
		tx_uphonenum.setBorder(BorderFactory.createEmptyBorder());
		
		contentPane.add(lb_title);
		contentPane.add(lb_account);
		contentPane.add(lb_password);
		contentPane.add(lb_password2);
		contentPane.add(lb_uname);
		contentPane.add(lb_uaddress);
		contentPane.add(lb_uphonenum);
		contentPane.add(tx_account);
		contentPane.add(tx_password);
		contentPane.add(tx_password2);
		contentPane.add(tx_uname);
		contentPane.add(tx_uaddress);
		contentPane.add(tx_uphonenum);
		contentPane.add(bt_ok);
		
		bt_ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!tx_password.getText().equals(tx_password2.getText())) {
					JOptionPane.showConfirmDialog(null, "两次输入的密码不一致！");
					return;
				}
				uaccount=tx_account.getText();
				upsd=tx_password.getText();
				uname=tx_uname.getText();
				uaddress=tx_uaddress.getText();
				uphonenum=tx_uphonenum.getText();
				DbUtil util=new DbUtil();
				Connection connection=util.getConnection();
				String sql="insert into user(uaccount,upsd,uname,uaddress,uphonenum) values(?,?,?,?,?)";
				try {
					PreparedStatement statement=connection.prepareStatement(sql);
					statement.setString(1, uaccount);
					statement.setString(2, upsd);
					statement.setString(3, uname);
					statement.setString(4, uaddress);
					statement.setString(5, uphonenum);
					statement.executeUpdate();
					
					String sql2="insert into u values(?,?,?)";
					PreparedStatement statement2=connection.prepareStatement(sql2);
					statement2.setString(1, uaccount);
					statement2.setString(2, upsd);
					statement2.setString(3, "customer");
					statement2.executeUpdate();
					
					JDialog dialog=new JDialog();
					dialog.setTitle("注册");
					dialog.setBounds(600, 420, 700, 250);
					dialog.setLayout(null);
					
					JLabel label=new JLabel("注册成功");
					label.setFont(new Font("Serif", Font.BOLD, 20));
					label.setBounds(300, 50, 300, 50);

					JButton bt_next=new JButton("确认");
					bt_next.setFont(new Font("Serif",Font.BOLD,18));
					bt_next.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
					bt_next.setBackground(Color.WHITE);
					bt_next.setBounds(300, 120, 90, 40);
					
					dialog.add(label);
					dialog.add(bt_next);
					dialog.show();
					
					bt_next.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							dialog.dispose();
							dispose();
						}
					});
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}

}
