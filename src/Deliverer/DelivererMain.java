package Deliverer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import Database.DbUtil;
import Order.Order;
import Restaurant.Food;
import Restaurant.Restaurant;
import Restaurant.RestaurantMain;
import User.User;

public class DelivererMain extends JFrame{
	JPanel contentPane;
	Restaurant restaurant;
	String account;
	Color color=new Color(240,189,20);
	Font font=new Font("Serif",Font.BOLD,20);
	Border border=BorderFactory.createLineBorder(Color.black);

	JButton bt_order0,bt_order1,bt_order2;
	JPanel panelCenter;
	JPanel paneCenterLeft,paneCenterRight;
	JScrollPane scrollPane;
	Deliverer deliverer;
	public DelivererMain(String account) {
		this.account=account;
		deliverer=new Deliverer();
		deliverer.setDaccount(account);
		contentPane=new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(color.white);
		setBounds(320,154,1280,770);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setTitle("骑手系统");
		setContentPane(contentPane);
		setVisible(true);
	
		
		JPanel panelNorth=new JPanel();
		panelCenter=new JPanel();
		
		panelNorth.setLayout(null);
		panelCenter.setLayout(null);
		
		panelNorth.setBackground(color);
		
		panelNorth.setPreferredSize(new Dimension(1280, 160));
		panelCenter.setPreferredSize(new Dimension(1280,610));
		
		contentPane.add(panelCenter,BorderLayout.CENTER);
		contentPane.add(panelNorth,BorderLayout.NORTH);
		
		
		Connection connection=new DbUtil().getConnection();
		String sql="select * from deliverer where daccount=?";
		try {
			Dialog dialog=new Dialog(this);
			dialog.setBounds(100, 100, 200, 200);
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1, account);
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()) {
				deliverer.setDid(resultSet.getInt("did"));
				deliverer.setDname(resultSet.getString("dname"));
				deliverer.setdPhonenum(resultSet.getString("dphonenum"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		paneCenterLeft=new JPanel();
		paneCenterRight=new JPanel();
		paneCenterLeft.setLayout(null);
		paneCenterRight.setLayout(null);
		paneCenterLeft.setBounds(0, 0, 300, 610);
		paneCenterRight.setPreferredSize(new Dimension(940,50));		
		
		scrollPane=new JScrollPane(paneCenterRight);
		scrollPane.setBounds(300, 0, 960, 564);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		

		
		bt_order0=new JButton("新订单");
		bt_order0.setFont(new Font("Serif",Font.BOLD,20));
		bt_order0.setForeground(Color.white);
		bt_order0.setBackground(color);
		bt_order0.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		bt_order0.setBounds(100, 91, 150, 33);
		
		bt_order1=new JButton("配送订单");
		bt_order1.setFont(new Font("Serif",Font.BOLD,20));
		bt_order1.setForeground(Color.white);
		bt_order1.setBackground(color);
		bt_order1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		bt_order1.setBounds(300, 91, 150, 33);
		
		bt_order2=new JButton("已完成订单");
		bt_order2.setFont(new Font("Serif",Font.BOLD,20));
		bt_order2.setForeground(Color.white);
		bt_order2.setBackground(color);
		bt_order2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		bt_order2.setBounds(500, 91, 150, 33);
		
		
		panelNorth.add(bt_order0);
		panelNorth.add(bt_order1);
		panelNorth.add(bt_order2);
		
		action0();
		
		
		bt_order0.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				action0();
			}
		});
		
		bt_order1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				action1();
			}
		});
		
		bt_order2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				action2();
			}
		});
		
	}
	
	public void action0() {
		setColor(0);
		panelCenter.removeAll();
		panelCenter.repaint();
		panelCenter.removeAll();
		panelCenter.repaint();
		panelCenter.setPreferredSize(new Dimension(1200,50));
			
		Connection connection=new DbUtil().getConnection();
		
		String sql_order="select * from forder where status='已接单' order by oid desc";
		try {
			PreparedStatement stmt_order=connection.prepareStatement(sql_order);
			ResultSet res_order=stmt_order.executeQuery();
			
			int startY=0;
			int width=900;
			while(res_order.next()) {
				Order order=new Order();
				order.setOid(res_order.getString("oid"));
				panelCenter.setPreferredSize(new Dimension(1200,panelCenter.getPreferredSize().height+120));
				
				Restaurant restaurant =new Restaurant();
				String sql_res="select * from restaurant where rid=?";
				PreparedStatement stmt_res=connection.prepareStatement(sql_res);
				stmt_res.setInt(1, res_order.getInt("rid"));
				ResultSet res_res=stmt_res.executeQuery();
				while(res_res.next()) {
					restaurant.setImage(res_res.getString("ricon"));
					restaurant.setRname(res_res.getString("rname"));
					restaurant.setRlocation(res_res.getString("rlocation"));
					restaurant.setRphonenum(res_res.getString("rphonenum"));
				}
				
				User user =new User();
				String sql_user="select * from user where uid=?";
				PreparedStatement stmt_user=connection.prepareStatement(sql_user);
				stmt_user.setInt(1, res_order.getInt("uid"));
				ResultSet res_user=stmt_user.executeQuery();
				while(res_user.next()) {
					user.setUname(res_user.getString("uname"));	
					user.setUphonenum(res_user.getString("uphonenum"));
					user.setUaddress(res_user.getString("uaddress"));
				}
					
				Image ig_fpic=Toolkit.getDefaultToolkit().createImage(restaurant.getImage());
				ImageIcon ic_fpic=new ImageIcon();
				ic_fpic.setImage(ig_fpic.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
				
				JLabel lb_fpic=new JLabel(ic_fpic);
				
				JButton bt_u=new JButton("客户:"+user.getUname());
				JButton bt_r=new JButton("餐馆:"+restaurant.getRname());
				JButton bt_accept=new JButton("接单");
				JLabel lb_fprice=new JLabel("总金额:"+res_order.getDouble("price"));
				JLabel lb_fstatus=new JLabel("订单状态:"+res_order.getString("status"));
				JLabel lb_fdate=new JLabel("订单时间:"+res_order.getString("oid"));
				
			
				JButton bt_enter=new JButton("查看详情");
				
				JPanel panelR=new JPanel();
				panelR.setLayout(null);
				panelR.setBounds(180, startY+20, width, 100);
				panelR.add(lb_fpic);
				panelR.add(bt_u);
				panelR.add(bt_r);
				panelR.add(lb_fprice);
				panelR.add(lb_fstatus);
				panelR.add(lb_fdate);
				panelR.add(bt_enter);
				panelR.add(bt_accept);
				
				
				panelR.setBackground(new Color(255,255,255));
				
				startY+=120;
				lb_fpic.setBounds(50,10, 80, 80);
				bt_u.setBounds(130, 20, 150, 30);
				bt_r.setBounds(305, 20, 200, 30);
				bt_enter.setBounds(740,20,120,30);
				lb_fprice.setBounds(150, 60, 120, 30);
				lb_fstatus.setBounds(330,60,180,30);
				lb_fdate.setBounds(580, 60, 290, 30);
				bt_accept.setBounds(580, 20, 120, 30);
				
				bt_enter.setFont(font);
	
				bt_u.setFont(font);
				bt_r.setFont(font);
				bt_u.setBackground(color.white);
				bt_r.setBackground(Color.white);
				bt_accept.setFont(font);
				bt_accept.setBackground(color.white);		
				lb_fprice.setFont(font);
				lb_fstatus.setFont(font);
				lb_fdate.setFont(font);
				
				bt_u.setBorder(BorderFactory.createEmptyBorder());
				bt_r.setBorder(BorderFactory.createEmptyBorder());
				
				bt_enter.setBorder(border);
				bt_enter.setBackground(Color.WHITE);
				bt_accept.setBorder(border);
				bt_accept.setBackground(Color.WHITE);

				panelCenter.add(panelR);
				
				bt_u.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JDialog dialog=new JDialog();
						dialog.setBounds(650, 300, 700, 400);
						dialog.setTitle("用户");
						dialog.setLayout(null);
						dialog.setBackground(color);
						
						JLabel lb_r=new JLabel("用户称呼");
						JLabel lb_d=new JLabel("用户地址");
						JLabel lb_u=new JLabel("用户电话");
						
						lb_r.setBounds(160, 80, 100, 30);
						lb_r.setFont(font);
					
						lb_d.setBounds(160, 140, 100, 30);
						lb_d.setFont(font);
						
						lb_u.setBounds(160, 200, 100, 30);
						lb_u.setFont(font);
						
						JLabel tx_r=new JLabel();
						tx_r.setFont(font);
						tx_r.setBounds(330,80,200,30);
						
						JLabel tx_d=new JLabel();
						tx_d.setFont(font);
						tx_d.setBounds(330,140,200,30);
						
						JLabel tx_u=new JLabel();
						tx_u.setFont(font);
						tx_u.setBounds(330,200,200,30);
						
						tx_r.setText(user.getUname());
						tx_d.setText(user.getUaddress());
						tx_u.setText(user.getUphonenum());
					
						
						JButton bt_eva=new JButton("确定");
						bt_eva.setBackground(Color.white);
						bt_eva.setFont(font);
						bt_eva.setBorder(border);
						bt_eva.setBounds(260, 260, 150, 40);
						
						bt_eva.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								dialog.dispose();
							}
						});
						
						dialog.add(lb_d);
						dialog.add(lb_r);
						dialog.add(lb_u);
						dialog.add(tx_u);
						dialog.add(tx_d);
						dialog.add(tx_r);
						dialog.add(bt_eva);
						
						dialog.show();
					}
				});
				
				bt_r.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JDialog dialog=new JDialog();
						dialog.setBounds(650, 300, 700, 400);
						dialog.setTitle("餐馆");
						dialog.setLayout(null);
						dialog.setBackground(color);
						
						JLabel lb_r=new JLabel("餐馆名");
						JLabel lb_d=new JLabel("餐馆地址");
						JLabel lb_u=new JLabel("餐馆电话");
						
						lb_r.setBounds(160, 80, 100, 30);
						lb_r.setFont(font);
					
						lb_d.setBounds(160, 140, 100, 30);
						lb_d.setFont(font);
						
						lb_u.setBounds(160, 200, 100, 30);
						lb_u.setFont(font);
						
						JLabel tx_r=new JLabel();
						tx_r.setFont(font);
						tx_r.setBounds(330,80,200,30);
						
						JLabel tx_d=new JLabel();
						tx_d.setFont(font);
						tx_d.setBounds(330,140,200,30);
						
						JLabel tx_u=new JLabel();
						tx_u.setFont(font);
						tx_u.setBounds(330,200,200,30);
						
						tx_r.setText(restaurant.getRname());
						tx_d.setText(restaurant.getRlocation());
						tx_u.setText(restaurant.getRphonenum());
					
						
						JButton bt_eva=new JButton("确定");
						bt_eva.setBackground(Color.white);
						bt_eva.setFont(font);
						bt_eva.setBorder(border);
						bt_eva.setBounds(260, 260, 150, 40);
						
						bt_eva.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								dialog.dispose();
							}
						});
						
						dialog.add(lb_d);
						dialog.add(lb_r);
						dialog.add(lb_u);
						dialog.add(tx_u);
						dialog.add(tx_d);
						dialog.add(tx_r);
						dialog.add(bt_eva);
						
						dialog.show();
					}
				});
				
				bt_enter.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
					
						JDialog dialog=new JDialog();
						dialog.setBounds(450, 200, 1000, 600);
						dialog.setLayout(null);;
						
						JPanel panelCart=new JPanel();
						panelCart.setPreferredSize(new Dimension(920, 50));
						panelCart.setLayout(null);					
						
						JScrollPane sPane=new JScrollPane(panelCart);
						sPane.setBounds(0, 0, 980, 600);
						sPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						sPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						dialog.add(sPane);
						
						
						dialog.setTitle("订单详情");
				
						Connection connection=new DbUtil().getConnection();
						String sql_cart="select * from order_food where oid=?";
						int startY2=0;
						try {
							PreparedStatement stmt_cart=connection.prepareStatement(sql_cart);
							stmt_cart.setString(1, order.getOid());
							ResultSet rs_cart=stmt_cart.executeQuery();
							while(rs_cart.next()) {
								String sql_food="select * from food where fid= ?";
								PreparedStatement stmt_food=connection.prepareStatement(sql_food);
								stmt_food.setInt(1, rs_cart.getInt("fid"));
								ResultSet rs_food=stmt_food.executeQuery();
								while(rs_food.next()) {
									Food food=new Food();
									food.setFpicture(rs_food.getString("fpicture"));
									food.setFname(rs_food.getString("fname"));
									food.setFprice(rs_food.getDouble("fprice"));
									food.setFid(rs_food.getInt("fid"));
									food.setRid(rs_food.getInt("rid"));
									Image ig_fpic=Toolkit.getDefaultToolkit().createImage(food.getFpicture());
									ImageIcon ic_fpic=new ImageIcon();
									ic_fpic.setImage(ig_fpic.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
									
									JLabel lb_fpic=new JLabel(ic_fpic);
									JLabel lb_fname=new JLabel("名称:"+food.getFname());
									JLabel lb_fprice=new JLabel("价格:"+food.getFprice());
									JLabel lb_fnum=new JLabel("数量:"+rs_cart.getInt("num"));
									
									JPanel panelFood=new JPanel();
									panelFood.setLayout(null);
									panelFood.setBounds(120, startY2+20, 750, 100);
									panelFood.add(lb_fpic);
									panelFood.add(lb_fname);
									panelFood.add(lb_fprice);
									panelFood.setBackground(new Color(255,255,255));
									panelCart.setPreferredSize(new Dimension(970, panelCart.getPreferredSize().height+120));
									startY2+=120;
									lb_fpic.setBounds(50,10, 80, 80);
									lb_fname.setBounds(200, 0, 300, 30);
									lb_fprice.setBounds(200, 30, 300, 30);
									lb_fnum.setBounds(200,60,300,30);
									
									lb_fname.setFont(font);
									lb_fprice.setFont(font);
									lb_fnum.setFont(font);
									
	
									panelFood.add(lb_fname);
									panelFood.add(lb_fpic);
									panelFood.add(lb_fprice);
									panelFood.add(lb_fnum);
									panelCart.add(panelFood);									
								}
								
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						

						dialog.show();
					}
				});
		
				bt_accept.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String sql_accept="update forder set did=?,status='配送中' where oid=?";
						PreparedStatement stmt_accept;
						try {
							stmt_accept = connection.prepareStatement(sql_accept);
							stmt_accept.setInt(1, deliverer.getDid());
							stmt_accept.setString(2, order.getOid());
							stmt_accept.executeUpdate();
							action0();
							JOptionPane.showMessageDialog(null, "接单成功");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});	
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void action1() {
		setColor(1);
		panelCenter.removeAll();
		panelCenter.repaint();
		panelCenter.removeAll();
		panelCenter.repaint();
		panelCenter.setPreferredSize(new Dimension(1200,50));
			
		Connection connection=new DbUtil().getConnection();
		
		String sql_order="select * from forder where status='配送中' and did=? order by oid desc";
		try {
			PreparedStatement stmt_order=connection.prepareStatement(sql_order);
			stmt_order.setInt(1, deliverer.getDid());
			ResultSet res_order=stmt_order.executeQuery();
			
			int startY=0;
			int width=900;
			while(res_order.next()) {
				Order order=new Order();
				order.setOid(res_order.getString("oid"));
				panelCenter.setPreferredSize(new Dimension(1200,panelCenter.getPreferredSize().height+120));
				
				Restaurant restaurant =new Restaurant();
				String sql_res="select * from restaurant where rid=?";
				PreparedStatement stmt_res=connection.prepareStatement(sql_res);
				stmt_res.setInt(1, res_order.getInt("rid"));
				ResultSet res_res=stmt_res.executeQuery();
				while(res_res.next()) {
					restaurant.setImage(res_res.getString("ricon"));
					restaurant.setRname(res_res.getString("rname"));
					restaurant.setRlocation(res_res.getString("rlocation"));
					restaurant.setRphonenum(res_res.getString("rphonenum"));
				}
				
				User user =new User();
				String sql_user="select * from user where uid=?";
				PreparedStatement stmt_user=connection.prepareStatement(sql_user);
				stmt_user.setInt(1, res_order.getInt("uid"));
				ResultSet res_user=stmt_user.executeQuery();
				while(res_user.next()) {
					user.setUname(res_user.getString("uname"));	
					user.setUphonenum(res_user.getString("uphonenum"));
					user.setUaddress(res_user.getString("uaddress"));
				}
					
				Image ig_fpic=Toolkit.getDefaultToolkit().createImage(restaurant.getImage());
				ImageIcon ic_fpic=new ImageIcon();
				ic_fpic.setImage(ig_fpic.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
				
				JLabel lb_fpic=new JLabel(ic_fpic);
				
				JButton bt_u=new JButton("客户:"+user.getUname());
				JButton bt_r=new JButton("餐馆:"+restaurant.getRname());
				JButton bt_accept=new JButton("已送达");
				JLabel lb_fprice=new JLabel("总金额:"+res_order.getDouble("price"));
				JLabel lb_fstatus=new JLabel("订单状态:"+res_order.getString("status"));
				JLabel lb_fdate=new JLabel("订单时间:"+res_order.getString("oid"));
				
			
				JButton bt_enter=new JButton("查看详情");
				
				JPanel panelR=new JPanel();
				panelR.setLayout(null);
				panelR.setBounds(180, startY+20, width, 100);
				panelR.add(lb_fpic);
				panelR.add(bt_u);
				panelR.add(bt_r);
				panelR.add(lb_fprice);
				panelR.add(lb_fstatus);
				panelR.add(lb_fdate);
				panelR.add(bt_enter);
				panelR.add(bt_accept);
				
				
				panelR.setBackground(new Color(255,255,255));
				
				startY+=120;
				lb_fpic.setBounds(50,10, 80, 80);
				bt_u.setBounds(130, 20, 150, 30);
				bt_r.setBounds(305, 20, 200, 30);
				bt_enter.setBounds(740,20,120,30);
				lb_fprice.setBounds(150, 60, 120, 30);
				lb_fstatus.setBounds(330,60,180,30);
				lb_fdate.setBounds(580, 60, 290, 30);
				bt_accept.setBounds(580, 20, 120, 30);
				
				bt_enter.setFont(font);
	
				bt_u.setFont(font);
				bt_r.setFont(font);
				bt_u.setBackground(color.white);
				bt_r.setBackground(Color.white);
				bt_accept.setFont(font);
				bt_accept.setBackground(color.white);		
				lb_fprice.setFont(font);
				lb_fstatus.setFont(font);
				lb_fdate.setFont(font);
				
				bt_u.setBorder(BorderFactory.createEmptyBorder());
				bt_r.setBorder(BorderFactory.createEmptyBorder());
				
				bt_enter.setBorder(border);
				bt_enter.setBackground(Color.WHITE);
				bt_accept.setBorder(border);
				bt_accept.setBackground(Color.WHITE);

				panelCenter.add(panelR);
				
				bt_u.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JDialog dialog=new JDialog();
						dialog.setBounds(650, 300, 700, 400);
						dialog.setTitle("用户");
						dialog.setLayout(null);
						dialog.setBackground(color);
						
						JLabel lb_r=new JLabel("用户称呼");
						JLabel lb_d=new JLabel("用户地址");
						JLabel lb_u=new JLabel("用户电话");
						
						lb_r.setBounds(160, 80, 100, 30);
						lb_r.setFont(font);
					
						lb_d.setBounds(160, 140, 100, 30);
						lb_d.setFont(font);
						
						lb_u.setBounds(160, 200, 100, 30);
						lb_u.setFont(font);
						
						JLabel tx_r=new JLabel();
						tx_r.setFont(font);
						tx_r.setBounds(330,80,200,30);
						
						JLabel tx_d=new JLabel();
						tx_d.setFont(font);
						tx_d.setBounds(330,140,200,30);
						
						JLabel tx_u=new JLabel();
						tx_u.setFont(font);
						tx_u.setBounds(330,200,200,30);
						
						tx_r.setText(user.getUname());
						tx_d.setText(user.getUaddress());
						tx_u.setText(user.getUphonenum());
					
						
						JButton bt_eva=new JButton("确定");
						bt_eva.setBackground(Color.white);
						bt_eva.setFont(font);
						bt_eva.setBorder(border);
						bt_eva.setBounds(260, 260, 150, 40);
						
						bt_eva.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								dialog.dispose();
							}
						});
						
						dialog.add(lb_d);
						dialog.add(lb_r);
						dialog.add(lb_u);
						dialog.add(tx_u);
						dialog.add(tx_d);
						dialog.add(tx_r);
						dialog.add(bt_eva);
						
						dialog.show();
					}
				});
				
				bt_r.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JDialog dialog=new JDialog();
						dialog.setBounds(650, 300, 700, 400);
						dialog.setTitle("餐馆");
						dialog.setLayout(null);
						dialog.setBackground(color);
						
						JLabel lb_r=new JLabel("餐馆名");
						JLabel lb_d=new JLabel("餐馆地址");
						JLabel lb_u=new JLabel("餐馆电话");
						
						lb_r.setBounds(160, 80, 100, 30);
						lb_r.setFont(font);
					
						lb_d.setBounds(160, 140, 100, 30);
						lb_d.setFont(font);
						
						lb_u.setBounds(160, 200, 100, 30);
						lb_u.setFont(font);
						
						JLabel tx_r=new JLabel();
						tx_r.setFont(font);
						tx_r.setBounds(330,80,200,30);
						
						JLabel tx_d=new JLabel();
						tx_d.setFont(font);
						tx_d.setBounds(330,140,200,30);
						
						JLabel tx_u=new JLabel();
						tx_u.setFont(font);
						tx_u.setBounds(330,200,200,30);
						
						tx_r.setText(restaurant.getRname());
						tx_d.setText(restaurant.getRlocation());
						tx_u.setText(restaurant.getRphonenum());
					
						
						JButton bt_eva=new JButton("确定");
						bt_eva.setBackground(Color.white);
						bt_eva.setFont(font);
						bt_eva.setBorder(border);
						bt_eva.setBounds(260, 260, 150, 40);
						
						bt_eva.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								dialog.dispose();
							}
						});
						
						dialog.add(lb_d);
						dialog.add(lb_r);
						dialog.add(lb_u);
						dialog.add(tx_u);
						dialog.add(tx_d);
						dialog.add(tx_r);
						dialog.add(bt_eva);
						
						dialog.show();
					}
				});
				bt_enter.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
					
						JDialog dialog=new JDialog();
						dialog.setBounds(450, 200, 1000, 600);
						dialog.setLayout(null);;
						
						JPanel panelCart=new JPanel();
						panelCart.setPreferredSize(new Dimension(920, 50));
						panelCart.setLayout(null);					
						
						JScrollPane sPane=new JScrollPane(panelCart);
						sPane.setBounds(0, 0, 980, 600);
						sPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						sPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						dialog.add(sPane);
						
						
						dialog.setTitle("订单详情");
				
						Connection connection=new DbUtil().getConnection();
						String sql_cart="select * from order_food where oid=?";
						int startY2=0;
						try {
							PreparedStatement stmt_cart=connection.prepareStatement(sql_cart);
							stmt_cart.setString(1, order.getOid());
							ResultSet rs_cart=stmt_cart.executeQuery();
							while(rs_cart.next()) {
								String sql_food="select * from food where fid= ?";
								PreparedStatement stmt_food=connection.prepareStatement(sql_food);
								stmt_food.setInt(1, rs_cart.getInt("fid"));
								ResultSet rs_food=stmt_food.executeQuery();
								while(rs_food.next()) {
									Food food=new Food();
									food.setFpicture(rs_food.getString("fpicture"));
									food.setFname(rs_food.getString("fname"));
									food.setFprice(rs_food.getDouble("fprice"));
									food.setFid(rs_food.getInt("fid"));
									food.setRid(rs_food.getInt("rid"));
									Image ig_fpic=Toolkit.getDefaultToolkit().createImage(food.getFpicture());
									ImageIcon ic_fpic=new ImageIcon();
									ic_fpic.setImage(ig_fpic.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
									
									JLabel lb_fpic=new JLabel(ic_fpic);
									JLabel lb_fname=new JLabel("名称:"+food.getFname());
									JLabel lb_fprice=new JLabel("价格:"+food.getFprice());
									JLabel lb_fnum=new JLabel("数量:"+rs_cart.getInt("num"));
									
									JPanel panelFood=new JPanel();
									panelFood.setLayout(null);
									panelFood.setBounds(120, startY2+20, 750, 100);
									panelFood.add(lb_fpic);
									panelFood.add(lb_fname);
									panelFood.add(lb_fprice);
									panelFood.setBackground(new Color(255,255,255));
									panelCart.setPreferredSize(new Dimension(970, panelCart.getPreferredSize().height+120));
									startY2+=120;
									lb_fpic.setBounds(50,10, 80, 80);
									lb_fname.setBounds(200, 0, 300, 30);
									lb_fprice.setBounds(200, 30, 300, 30);
									lb_fnum.setBounds(200,60,300,30);
									
									lb_fname.setFont(font);
									lb_fprice.setFont(font);
									lb_fnum.setFont(font);
									
	
									panelFood.add(lb_fname);
									panelFood.add(lb_fpic);
									panelFood.add(lb_fprice);
									panelFood.add(lb_fnum);
									panelCart.add(panelFood);									
								}
								
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						

						dialog.show();
					}
				});
			
				bt_accept.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String sql_accept="update forder set did=?,status='已完成' where oid=?";
						PreparedStatement stmt_accept;
						try {
							stmt_accept = connection.prepareStatement(sql_accept);
							stmt_accept.setInt(1, deliverer.getDid());
							stmt_accept.setString(2, order.getOid());
							stmt_accept.executeUpdate();
							action1();
							JOptionPane.showMessageDialog(null, "送达成功");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});	
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void action2() {
		setColor(2);
		panelCenter.removeAll();
		panelCenter.repaint();
		panelCenter.removeAll();
		panelCenter.repaint();
		panelCenter.setPreferredSize(new Dimension(1200,50));
			
		Connection connection=new DbUtil().getConnection();
		
		String sql_order="select * from forder where status='已完成' and did=? order by oid desc";
		try {
			PreparedStatement stmt_order=connection.prepareStatement(sql_order);
			stmt_order.setInt(1, deliverer.getDid());
			ResultSet res_order=stmt_order.executeQuery();
			
			int startY=0;
			int width=900;
			while(res_order.next()) {
				Order order=new Order();
				order.setOid(res_order.getString("oid"));
				panelCenter.setPreferredSize(new Dimension(1200,panelCenter.getPreferredSize().height+120));
				
				Restaurant restaurant =new Restaurant();
				String sql_res="select * from restaurant where rid=?";
				PreparedStatement stmt_res=connection.prepareStatement(sql_res);
				stmt_res.setInt(1, res_order.getInt("rid"));
				ResultSet res_res=stmt_res.executeQuery();
				while(res_res.next()) {
					restaurant.setImage(res_res.getString("ricon"));
					restaurant.setRname(res_res.getString("rname"));
					restaurant.setRlocation(res_res.getString("rlocation"));
					restaurant.setRphonenum(res_res.getString("rphonenum"));
				}
				
				User user =new User();
				String sql_user="select * from user where uid=?";
				PreparedStatement stmt_user=connection.prepareStatement(sql_user);
				stmt_user.setInt(1, res_order.getInt("uid"));
				ResultSet res_user=stmt_user.executeQuery();
				while(res_user.next()) {
					user.setUname(res_user.getString("uname"));	
					user.setUphonenum(res_user.getString("uphonenum"));
					user.setUaddress(res_user.getString("uaddress"));
				}
					
				Image ig_fpic=Toolkit.getDefaultToolkit().createImage(restaurant.getImage());
				ImageIcon ic_fpic=new ImageIcon();
				ic_fpic.setImage(ig_fpic.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
				
				JLabel lb_fpic=new JLabel(ic_fpic);
				
				JButton bt_u=new JButton("客户:"+user.getUname());
				JButton bt_r=new JButton("餐馆:"+restaurant.getRname());
				JButton bt_accept=new JButton("查看评价");
				JLabel lb_fprice=new JLabel("总金额:"+res_order.getDouble("price"));
				JLabel lb_fstatus=new JLabel("订单状态:"+res_order.getString("status"));
				JLabel lb_fdate=new JLabel("订单时间:"+res_order.getString("oid"));
				
			
				JButton bt_enter=new JButton("查看详情");
				
				JPanel panelR=new JPanel();
				panelR.setLayout(null);
				panelR.setBounds(180, startY+20, width, 100);
				panelR.add(lb_fpic);
				panelR.add(bt_u);
				panelR.add(bt_r);
				panelR.add(lb_fprice);
				panelR.add(lb_fstatus);
				panelR.add(lb_fdate);
				panelR.add(bt_enter);
				panelR.add(bt_accept);
				
				
				panelR.setBackground(new Color(255,255,255));
				
				startY+=120;
				lb_fpic.setBounds(50,10, 80, 80);
				bt_u.setBounds(130, 20, 150, 30);
				bt_r.setBounds(305, 20, 200, 30);
				bt_enter.setBounds(740,20,120,30);
				lb_fprice.setBounds(150, 60, 120, 30);
				lb_fstatus.setBounds(330,60,180,30);
				lb_fdate.setBounds(580, 60, 290, 30);
				bt_accept.setBounds(580, 20, 120, 30);
				
				bt_enter.setFont(font);
	
				bt_u.setFont(font);
				bt_r.setFont(font);
				bt_u.setBackground(color.white);
				bt_r.setBackground(Color.white);
				bt_accept.setFont(font);
				bt_accept.setBackground(color.white);		
				lb_fprice.setFont(font);
				lb_fstatus.setFont(font);
				lb_fdate.setFont(font);
				
				bt_u.setBorder(BorderFactory.createEmptyBorder());
				bt_r.setBorder(BorderFactory.createEmptyBorder());
				
				bt_enter.setBorder(border);
				bt_enter.setBackground(Color.WHITE);
				bt_accept.setBorder(border);
				bt_accept.setBackground(Color.WHITE);

				panelCenter.add(panelR);
				
				bt_enter.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
					
						JDialog dialog=new JDialog();
						dialog.setBounds(450, 200, 1000, 600);
						dialog.setLayout(null);;
						
						JPanel panelCart=new JPanel();
						panelCart.setPreferredSize(new Dimension(920, 50));
						panelCart.setLayout(null);					
						
						JScrollPane sPane=new JScrollPane(panelCart);
						sPane.setBounds(0, 0, 980, 600);
						sPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						sPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						dialog.add(sPane);
						
						
						dialog.setTitle("订单详情");
				
						Connection connection=new DbUtil().getConnection();
						String sql_cart="select * from order_food where oid=?";
						int startY2=0;
						try {
							PreparedStatement stmt_cart=connection.prepareStatement(sql_cart);
							stmt_cart.setString(1, order.getOid());
							ResultSet rs_cart=stmt_cart.executeQuery();
							while(rs_cart.next()) {
								String sql_food="select * from food where fid= ?";
								PreparedStatement stmt_food=connection.prepareStatement(sql_food);
								stmt_food.setInt(1, rs_cart.getInt("fid"));
								ResultSet rs_food=stmt_food.executeQuery();
								while(rs_food.next()) {
									Food food=new Food();
									food.setFpicture(rs_food.getString("fpicture"));
									food.setFname(rs_food.getString("fname"));
									food.setFprice(rs_food.getDouble("fprice"));
									food.setFid(rs_food.getInt("fid"));
									food.setRid(rs_food.getInt("rid"));
									Image ig_fpic=Toolkit.getDefaultToolkit().createImage(food.getFpicture());
									ImageIcon ic_fpic=new ImageIcon();
									ic_fpic.setImage(ig_fpic.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
									
									JLabel lb_fpic=new JLabel(ic_fpic);
									JLabel lb_fname=new JLabel("名称:"+food.getFname());
									JLabel lb_fprice=new JLabel("价格:"+food.getFprice());
									JLabel lb_fnum=new JLabel("数量:"+rs_cart.getInt("num"));
									
									JPanel panelFood=new JPanel();
									panelFood.setLayout(null);
									panelFood.setBounds(120, startY2+20, 750, 100);
									panelFood.add(lb_fpic);
									panelFood.add(lb_fname);
									panelFood.add(lb_fprice);
									panelFood.setBackground(new Color(255,255,255));
									panelCart.setPreferredSize(new Dimension(970, panelCart.getPreferredSize().height+120));
									startY2+=120;
									lb_fpic.setBounds(50,10, 80, 80);
									lb_fname.setBounds(200, 0, 300, 30);
									lb_fprice.setBounds(200, 30, 300, 30);
									lb_fnum.setBounds(200,60,300,30);
									
									lb_fname.setFont(font);
									lb_fprice.setFont(font);
									lb_fnum.setFont(font);
									
	
									panelFood.add(lb_fname);
									panelFood.add(lb_fpic);
									panelFood.add(lb_fprice);
									panelFood.add(lb_fnum);
									panelCart.add(panelFood);									
								}
								
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						

						dialog.show();
					}
				});
			
				bt_accept.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JDialog dialog=new JDialog();
						dialog.setBounds(650, 300, 700, 400);
						dialog.setTitle("评价");
						dialog.setLayout(null);
						dialog.setBackground(color);
						
						JLabel lb_r=new JLabel("商家评价");
						JLabel lb_d=new JLabel("骑手评价");
						JLabel lb_u=new JLabel("用户评价");
						
						lb_r.setBounds(160, 80, 100, 30);
						lb_r.setFont(font);
					
						lb_d.setBounds(160, 140, 100, 30);
						lb_d.setFont(font);
						
						lb_u.setBounds(160, 200, 100, 30);
						lb_u.setFont(font);
						
						JLabel tx_r=new JLabel("未评价");
						tx_r.setFont(font);
						tx_r.setBounds(330,80,200,30);
						
						JLabel tx_d=new JLabel("未评价");
						tx_d.setFont(font);
						tx_d.setBounds(330,140,200,30);
						
						JLabel tx_u=new JLabel("未评价");
						tx_u.setFont(font);
						tx_u.setBounds(330,200,200,30);
						
						String sql_eva="select * from evalution where oid =?";
						try {
							PreparedStatement stmt_eva=connection.prepareStatement(sql_eva);
							stmt_eva.setString(1, order.getOid());
							ResultSet res_eva=stmt_eva.executeQuery();
							while(res_eva.next()) {
								tx_r.setText(res_eva.getString("reva"));
								tx_d.setText(res_eva.getString("deva"));
								tx_u.setText(res_eva.getString("ueva"));
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
						JButton bt_eva=new JButton("确定");
						bt_eva.setBackground(Color.white);
						bt_eva.setFont(font);
						bt_eva.setBorder(border);
						bt_eva.setBounds(260, 260, 150, 40);
						
						bt_eva.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								dialog.dispose();
							}
						});
						
						dialog.add(lb_d);
						dialog.add(lb_r);
						dialog.add(lb_u);
						dialog.add(tx_u);
						dialog.add(tx_d);
						dialog.add(tx_r);
						dialog.add(bt_eva);
						
						dialog.show();
						
					}
				});	
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setColor(int location) {
		switch (location) {
		
		case 0:
			bt_order0.setForeground(color);
			bt_order0.setBackground(Color.WHITE);
			bt_order0.setBorder(BorderFactory.createLineBorder(color));
			
			
			bt_order1.setForeground(Color.white);
			bt_order1.setBackground(color);
			bt_order1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			
			bt_order2.setForeground(Color.white);
			bt_order2.setBackground(color);
			bt_order2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			
			break;
		
		case 1:
			bt_order1.setForeground(color);
			bt_order1.setBackground(Color.WHITE);
			bt_order1.setBorder(BorderFactory.createLineBorder(color));
			
			bt_order0.setForeground(Color.white);
			bt_order0.setBackground(color);
			bt_order0.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			
			bt_order2.setForeground(Color.white);
			bt_order2.setBackground(color);
			bt_order2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			break;
		case 2:
			bt_order2.setForeground(color);
			bt_order2.setBackground(Color.WHITE);
			bt_order2.setBorder(BorderFactory.createLineBorder(color));
			
			bt_order0.setForeground(Color.white);
			bt_order0.setBackground(color);
			bt_order0.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			
			bt_order1.setForeground(Color.white);
			bt_order1.setBackground(color);
			bt_order1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			break;
	
		default:
			break;
		}
	}
}
