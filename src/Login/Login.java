package Login;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.border.Border;


import Database.DbUtil;
import Deliverer.DelivererMain;
import Restaurant.RestaurantMain;
import User.UserMain;

public class Login extends JFrame {

	private Toolkit toolkit;
	private Dimension screenD;
	private int width,height,leftWidth,rightWidth;
	private JPanel contentPane;

	public Login() {
		toolkit=Toolkit.getDefaultToolkit();
		screenD=toolkit.getScreenSize();
		width=screenD.width*2/3;
		height=screenD.height*5/7;
		rightWidth=(int)(width/3.5);
		leftWidth=width-rightWidth;
		
		contentPane=new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		Color bgcolor=new Color(240,189,20);
		JPanel pLeft=new JPanel();
		pLeft.setBackground(bgcolor);
		pLeft.setLayout(null);
		contentPane.add(pLeft,BorderLayout.CENTER);
		
		JPanel pRight = new JPanel();
		pRight.setBackground(new Color(0,0,0));
		pRight.setPreferredSize(new Dimension(rightWidth,height));
		pRight.setLayout(new BorderLayout());
		contentPane.add(pRight,BorderLayout.EAST);
		
		
		JPanel pright_north=new JPanel();
		pright_north.setPreferredSize(new Dimension(rightWidth,2*height/7));
		pright_north.setLayout(null);
		
		JPanel pright_center=new JPanel();
		pright_center.setPreferredSize(new Dimension(rightWidth,height*5/7));
		pright_center.setLayout(null);
		
		pRight.add(pright_north,BorderLayout.NORTH);
		pRight.add(pright_center, BorderLayout.CENTER);
		
		JTextPane tx_account= new JTextPane();
		tx_account.setPreferredSize(new Dimension(rightWidth*4/5,height/20));
		tx_account.setFont(new Font("Serif",Font.PLAIN, 20));
		tx_account.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		JPasswordField tx_psd= new JPasswordField();
		tx_psd.setPreferredSize(new Dimension(rightWidth*4/5,height/20));
		tx_psd.setFont(new Font("Serif",Font.PLAIN, 20));
		tx_psd.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		JLabel lb_account=new JLabel("账户:");
		JLabel lb_psd=new JLabel("密码:");
		lb_account.setFont(new Font("Serif", Font.BOLD, 20));
		lb_psd.setFont(new Font("Serif", Font.BOLD, 20));
		
		JButton bt_login=new JButton("登录");
		JButton bt_register=new JButton("注册");
		
		bt_login.setFont(new Font("Italic", Font.BOLD, 20));
		bt_login.setBackground(Color.white);
		bt_login.setPreferredSize(new Dimension(rightWidth*2/7,height/20));
		bt_login.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		bt_register.setFont(new Font("Serif", Font.BOLD, 20));
		bt_register.setBackground(Color.white);
		bt_register.setPreferredSize(new Dimension(rightWidth*2/7,height/20));
		bt_register.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		
		lb_account.setBounds(30, height/10, rightWidth/5, height/20);
		tx_account.setBounds(rightWidth/3-10, height/10, 3*rightWidth/5, height/20);
		
		lb_psd.setBounds(30, 2*height/10, rightWidth/5, height/20);
		tx_psd.setBounds(rightWidth/3-10, 2*height/10, 3*rightWidth/5, height/20);

		
		bt_login.setBounds(30, 3*height/10, 2*rightWidth/5-5, height/20);
		bt_register.setBounds(45+2*rightWidth/5, 3*height/10, 2*rightWidth/5-5, height/20);
		
		pright_center.add(lb_account);
		pright_center.add(tx_account);
		
		pright_center.add(lb_psd);
		pright_center.add(tx_psd);
		
		
		pright_center.add(bt_login);
		pright_center.add(bt_register);
		
		
		Image bg=toolkit.getImage("./image/bg.png");
		ImageIcon bgIcon=new ImageIcon();
		bgIcon.setImage(bg);
		JLabel lb_bg=new JLabel(bgIcon);
		lb_bg.setBounds(120, 180, bgIcon.getIconWidth(), bgIcon.getIconHeight());
		pLeft.add(lb_bg);
		
		Image ic=toolkit.getImage("./image/ic.png");
		ImageIcon icIc=new ImageIcon();
		icIc.setImage(ic.getScaledInstance(rightWidth*2/5, rightWidth*2/5, Image.SCALE_DEFAULT));
		JLabel lb_ic=new JLabel(icIc);
		lb_ic.setBounds(rightWidth*3/10+10,76,rightWidth*2/5, rightWidth*2/5);
		pright_north.add(lb_ic);
		
		
				
		bt_login.addActionListener(new ActionListener() {
			
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String account=tx_account.getText();
				String password=tx_psd.getText();
				String sql="select * from u where account=? and password=?";
				DbUtil dbUtil=new DbUtil();
				Connection connection=dbUtil.getConnection();
				try {
					
					PreparedStatement statement=connection.prepareStatement(sql);
					statement.setString(1, account);
					statement.setString(2, password);			
					ResultSet resultSet=statement.executeQuery();
					if(resultSet.next()) {						
						dispose();
						String identity=resultSet.getString("identity");
						if(identity.equals("customer"))
							new UserMain(account);
						else if(identity.equals("deliverer"))
							new DelivererMain(account);
						else
							new RestaurantMain(account);
					}else {
						JDialog dialog=new JDialog();
						dialog.setLayout(null);
						dialog.setBounds(width/2, height/2, 500, 250);
						JLabel label=new JLabel("您输入的账户或密码错误!");
						label.setBounds(125, 50, 300, 50);
						label.setFont(new Font("Serif",Font.BOLD,20));
						JButton button=new JButton("确认");
						button.setBounds(200, 100, 100, 50);
						button.setFont(new Font("Serif",Font.BOLD,20));
						button.setBackground(Color.white);
						button.setBorder(BorderFactory.createLineBorder(Color.black, 1));
						button.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								dialog.dispose(); 
							}
						});
						dialog.setTitle("提示");
						dialog.add(label);
						dialog.add(button);
						dialog.show();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		bt_register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog dialog=new JDialog();
				dialog.setTitle("注册");
				dialog.setBounds(width/2, height/2, 700, 250);
				dialog.setLayout(null);
				
				JLabel label=new JLabel("请选择想要注册身份:");
				label.setFont(new Font("Serif", Font.BOLD, 20));
				label.setBounds(100, 50, 300, 50);
				
				JRadioButton rb_u=new JRadioButton("用户");
				JRadioButton rb_d=new JRadioButton("外卖员");
				JRadioButton rb_r=new JRadioButton("餐馆");
				rb_u.setFont(new Font("Serif", Font.BOLD, 20));
				rb_d.setFont(new Font("Serif", Font.BOLD, 20));
				rb_r.setFont(new Font("Serif", Font.BOLD, 20));
				rb_u.setBounds(320, 50, 100, 50);
				rb_d.setBounds(420, 50, 100, 50);
				rb_r.setBounds(540, 50, 100, 50);
				
				ButtonGroup buttonGroup=new ButtonGroup();
				buttonGroup.add(rb_u);
				buttonGroup.add(rb_d);
				buttonGroup.add(rb_r);
				dialog.add(label);
				dialog.add(rb_u);
				dialog.add(rb_d);
				dialog.add(rb_r);
				dialog.show();
				
				JButton bt_next=new JButton("下一步");
				bt_next.setFont(new Font("Serif",Font.BOLD,18));
				bt_next.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				bt_next.setBackground(Color.WHITE);
				bt_next.setBounds(300, 120, 90, 40);
				dialog.add(bt_next);
				
				bt_next.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						JFrame register;
						if(rb_u.isSelected())
							register=new URegister();
						else if(rb_d.isSelected())
							register=new DRegister();
						else if(rb_r.isSelected())
							register=new RRegister();
							
						dialog.dispose();
					}
				});
			}
		});
	
		setBounds((screenD.width-width)/2, (screenD.height-height)/2, width, height);

		setIconImage(ic);
		setTitle("外卖送餐管理系统");
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Login login=new Login();
	}
}
