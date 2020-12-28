package Restaurant;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.jdbc.StringUtils;

import Database.DbUtil;
import Order.Order;

public class RestaurantMain extends JFrame{
		JPanel contentPane;
		Restaurant restaurant;
		String account;
		Color color=new Color(240,189,20);
		Font font=new Font("Serif",Font.BOLD,20);
		Border border=BorderFactory.createLineBorder(Color.black);
	
		JButton bt_food,bt_order1,bt_order2,bt_order3,bt_order4;
		JPanel panelCenter;
		JPanel paneCenterLeft,paneCenterRight;
		JScrollPane scrollPane,scrollPane2;
		String fpicture0;
		public RestaurantMain(String account) {
			this.account=account;
			contentPane=new JPanel();
			contentPane.setLayout(new BorderLayout());
			contentPane.setBackground(color.white);
			setBounds(320,154,1280,770);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
			setTitle("餐馆系统");
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
			String sql="select * from restaurant where raccount=?";
			try {
				Dialog dialog=new Dialog(this);
				dialog.setBounds(100, 100, 200, 200);
				PreparedStatement statement=connection.prepareStatement(sql);
				statement.setString(1, account);
				ResultSet resultSet=statement.executeQuery();
				while(resultSet.next()) {
					restaurant=new Restaurant();
					restaurant.setRid(resultSet.getInt("rid"));
					restaurant.setRname(resultSet.getString("rname"));
					restaurant.setRlocation(resultSet.getString("rlocation"));
					restaurant.setRphonenum(resultSet.getString("rphonenum"));
					restaurant.setImage(resultSet.getString("ricon"));
				}
				
				Image ig_ic=Toolkit.getDefaultToolkit().createImage(restaurant.getImage());
				ImageIcon ic_icon=new ImageIcon();
				ic_icon.setImage(ig_ic.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
				JLabel jLabel=new JLabel(ic_icon);
				jLabel.setBounds(25, 25, 100, 100);
				panelNorth.add(jLabel);	
				
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
			
			JLabel ib_name=new JLabel("店名: "+restaurant.getRname());
			JLabel ib_location=new JLabel("地址: "+restaurant.getRlocation());
			JLabel ib_phonenum=new JLabel("联系电话: "+restaurant.getRphonenum());
			
			ib_name.setBounds(150, 25, 250, 33);
			ib_location.setBounds(150, 58, 250, 33);
			ib_phonenum.setBounds(150,91, 250, 33);
			
			ib_name.setFont(new Font("Serif",Font.BOLD,20));
			ib_name.setForeground(Color.white);
			ib_location.setFont(new Font("Serif",Font.BOLD,20));
			ib_location.setForeground(Color.white);
			ib_phonenum.setFont(new Font("Serif",Font.BOLD,20));
			ib_phonenum.setForeground(Color.white);
			
			panelNorth.add(ib_name);
			panelNorth.add(ib_location);
			panelNorth.add(ib_phonenum);
			
			bt_food=new JButton("菜品管理");
			bt_food.setFont(new Font("Serif",Font.BOLD,20));
			bt_food.setForeground(Color.white);
			bt_food.setBackground(color);
			bt_food.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			bt_food.setBounds(400, 91, 150, 33);
			
			
			bt_order1=new JButton("新订单");
			bt_order1.setFont(new Font("Serif",Font.BOLD,20));
			bt_order1.setForeground(Color.white);
			bt_order1.setBackground(color);
			bt_order1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			bt_order1.setBounds(570, 91, 150, 33);
			
			bt_order2=new JButton("派送订单");
			bt_order2.setFont(new Font("Serif",Font.BOLD,20));
			bt_order2.setForeground(Color.white);
			bt_order2.setBackground(color);
			bt_order2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			bt_order2.setBounds(910, 91, 150, 33);
			
			bt_order3=new JButton("已完成订单");
			bt_order3.setFont(new Font("Serif",Font.BOLD,20));
			bt_order3.setForeground(Color.white);
			bt_order3.setBackground(color);
			bt_order3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			bt_order3.setBounds(1080, 91, 150, 33);
			
			bt_order4=new JButton("已接单");
			bt_order4.setFont(new Font("Serif",Font.BOLD,20));
			bt_order4.setForeground(Color.white);
			bt_order4.setBackground(color);
			bt_order4.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			bt_order4.setBounds(740, 91, 150, 33);
			
			panelNorth.add(bt_food);
			panelNorth.add(bt_order1);
			panelNorth.add(bt_order2);
			panelNorth.add(bt_order3);
			panelNorth.add(bt_order4);
			
			
		
			
			action0();
			bt_food.addActionListener(new ActionListener() {
				
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
			
			bt_order3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					action3();
				}
			});
			
			bt_order4.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					action4();
				}
			});
		}

		public void action0() {
			setColor(0);
			panelCenter.removeAll();
			panelCenter.repaint();
			
			paneCenterRight.removeAll();
			paneCenterRight.repaint();
			
			ImageIcon ic_addFood=new ImageIcon("./image/add.jpg");
			ic_addFood.setImage(ic_addFood.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
			JButton lb_addFood=new JButton(ic_addFood);
			lb_addFood.setBounds(90, 180, 100, 100);
			lb_addFood.setBorder(BorderFactory.createLineBorder(color.BLACK));
			JLabel lb_addFoodtext=new JLabel("添加菜品");
			lb_addFoodtext.setBounds(96, 300, 100, 20);
			lb_addFoodtext.setFont(new Font("Serif",Font.BOLD,20));
			
			lb_addFood.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					addFood();
				}
			});
			
			
			
			paneCenterLeft.add(lb_addFood);
			paneCenterLeft.add(lb_addFoodtext);
			
			panelCenter.add(paneCenterLeft);
			panelCenter.add(scrollPane);
			
			
			
			
			Connection connection=new DbUtil().getConnection();
			
			String sql="select * from food where rid = ?";
			try {
				
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, restaurant.getRid());
				ResultSet resultSet=preparedStatement.executeQuery();
				
				int startY=0;
				int height=0;
				int width=900;
				while(resultSet.next()) {
					Food food=new Food();
					food.setFpicture(resultSet.getString("fpicture"));
					food.setFname(resultSet.getString("fname"));
					food.setFprice(resultSet.getDouble("fprice"));
					food.setFid(resultSet.getInt("fid"));
					
					Image ig_fpic=Toolkit.getDefaultToolkit().createImage(food.getFpicture());
					ImageIcon ic_fpic=new ImageIcon();
					ic_fpic.setImage(ig_fpic.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
					
					JLabel lb_fpic=new JLabel(ic_fpic);
					JLabel lb_fname=new JLabel("名称:"+food.getFname());
					JLabel lb_fprice=new JLabel("价格:"+food.getFprice());
					
					JPanel panelFood=new JPanel();
					panelFood.setLayout(null);
					panelFood.setBounds(20, startY+20, width, 100);
					panelFood.add(lb_fpic);
					panelFood.add(lb_fname);
					panelFood.add(lb_fprice);
					panelFood.setBackground(new Color(255,255,255));
					
					startY+=120;
					height+=120;
					lb_fpic.setBounds(50,10, 80, 80);
					lb_fname.setBounds(200, 20, 300, 30);
					lb_fprice.setBounds(200, 60, 300, 30);
					
					lb_fname.setFont(font);
					lb_fprice.setFont(font);
					
					JButton bt_delete=new JButton("删除菜品");
					JButton bt_chprice=new JButton("修改价格");
					bt_delete.setFont(font);
					bt_delete.setBorder(border);
					bt_delete.setBounds(600, 30, 100, 50);
					bt_delete.setBackground(Color.WHITE);
					
					bt_chprice.setFont(font);
					bt_chprice.setBorder(border);
					bt_chprice.setBounds(750, 30, 100, 50);
					bt_chprice.setBackground(Color.WHITE);
					
					panelFood.add(bt_chprice);
					panelFood.add(bt_delete);
					
					bt_delete.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String sql_delete="delete from food where fid=?";
							PreparedStatement preparedStatement;
							try {
								preparedStatement = connection.prepareStatement(sql_delete);
								preparedStatement.setInt(1, food.getFid());
								preparedStatement.executeUpdate();
								action0();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					
					bt_chprice.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String price_s=JOptionPane.showInputDialog("请输入修改后的价格");
							if(price_s==null)
								return;
							double price=Double.parseDouble(price_s);
							String sql_chprice="update food set fprice=? where fid=?";
							PreparedStatement preparedStatement;
							try {
								preparedStatement = connection.prepareStatement(sql_chprice);
								preparedStatement.setDouble(1, price);
								preparedStatement.setInt(2, food.getFid());
								preparedStatement.executeUpdate();
								action0();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					
					paneCenterRight.setPreferredSize(new Dimension(width,height));
					paneCenterRight.add(panelFood);
					
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
			panelCenter.setPreferredSize(new Dimension(1200,50));
				
			Connection connection=new DbUtil().getConnection();
			String sql_order="select * from forder where rid=? and status='未接单' order by oid desc";
			try {
				PreparedStatement stmt_order=connection.prepareStatement(sql_order);
				stmt_order.setInt(1, restaurant.getRid());
				ResultSet res_order=stmt_order.executeQuery();
				int startY=0;
				int width=900;
				while(res_order.next()) {
					Order order=new Order();
					order.setOid(res_order.getString("oid"));
					panelCenter.setPreferredSize(new Dimension(1200,panelCenter.getPreferredSize().height+120));
					Image ig_fpic=Toolkit.getDefaultToolkit().createImage(restaurant.getImage());
					ImageIcon ic_fpic=new ImageIcon();
					ic_fpic.setImage(ig_fpic.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
					
					String uaccount="";
					String sql_user="select * from user where uid=?";
					PreparedStatement stmt_user=connection.prepareStatement(sql_user);
					stmt_user.setInt(1, res_order.getInt("uid"));
					ResultSet res_user=stmt_user.executeQuery();
					while(res_user.next()) {
						uaccount=res_user.getString("uaccount");
					}
					
					
					JLabel lb_fpic=new JLabel(ic_fpic);
					JLabel lb_fname=new JLabel("客户:"+uaccount);
					JLabel lb_fprice=new JLabel("总金额:"+res_order.getDouble("price"));
					JLabel lb_fstatus=new JLabel("订单状态:"+res_order.getString("status"));
					JLabel lb_fdate=new JLabel("订单时间:"+res_order.getString("oid"));
					
				
					
					JButton bt_eva=new JButton("接单");
					JButton bt_enter=new JButton("查看详情");
					
					JPanel panelR=new JPanel();
					panelR.setLayout(null);
					panelR.setBounds(180, startY+20, width, 100);
					panelR.add(lb_fpic);
					panelR.add(lb_fname);
					panelR.add(lb_fprice);
					panelR.add(lb_fstatus);
					panelR.add(lb_fdate);
					panelR.add(bt_enter);
					panelR.add(bt_eva);
					
					panelR.setBackground(new Color(255,255,255));
					
					startY+=120;
					lb_fpic.setBounds(50,10, 80, 80);
					lb_fname.setBounds(200, 20, 300, 30);
					bt_enter.setBounds(740,20,120,30);
					bt_eva.setBounds(600,20,120,30);
					lb_fprice.setBounds(200, 60, 120, 30);
					lb_fstatus.setBounds(380,60,180,30);
					lb_fdate.setBounds(580, 60, 290, 30);
					
					bt_enter.setFont(font);
					bt_eva.setFont(font);
					lb_fname.setFont(font);
					lb_fprice.setFont(font);
					lb_fstatus.setFont(font);
					lb_fdate.setFont(font);
					
					bt_enter.setBorder(border);
					bt_eva.setBorder(border);
					bt_enter.setBackground(Color.WHITE);
					bt_eva.setBackground(Color.white);
					panelCenter.add(panelR);
					
					bt_eva.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String sql="update forder set status ='已接单' where oid = ?";
							try {
								PreparedStatement stmt_ch=connection.prepareStatement(sql);
								stmt_ch.setString(1, order.getOid());
								stmt_ch.executeUpdate();
								JOptionPane.showMessageDialog(null, "接单成功");
								action1();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
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
			String sql_order="select * from forder where rid=? and status='配送中' order by oid desc";
			try {
				PreparedStatement stmt_order=connection.prepareStatement(sql_order);
				stmt_order.setInt(1, restaurant.getRid());
				ResultSet res_order=stmt_order.executeQuery();
				int startY=0;
				int width=900;
				while(res_order.next()) {
					Order order=new Order();
					order.setOid(res_order.getString("oid"));
					panelCenter.setPreferredSize(new Dimension(1200,panelCenter.getPreferredSize().height+120));
					Image ig_fpic=Toolkit.getDefaultToolkit().createImage(restaurant.getImage());
					ImageIcon ic_fpic=new ImageIcon();
					ic_fpic.setImage(ig_fpic.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
					
					
					String uaccount="";
					String sql_user="select * from user where uid=?";
					PreparedStatement stmt_user=connection.prepareStatement(sql_user);
					stmt_user.setInt(1, res_order.getInt("uid"));
					ResultSet res_user=stmt_user.executeQuery();
					while(res_user.next()) {
						uaccount=res_user.getString("uaccount");
					}
					
					String dname="";
					String sql_deliever="select * from deliverer where did=?";
					PreparedStatement stmt_deliever=connection.prepareStatement(sql_deliever);
					stmt_deliever.setInt(1, res_order.getInt("did"));
					ResultSet res_deliverer=stmt_deliever.executeQuery();
					while(res_deliverer.next()) {
						dname=res_deliverer.getString("dname");
					}
					
					
					JLabel lb_fpic=new JLabel(ic_fpic);
					JLabel lb_fname=new JLabel("客户:"+uaccount);
					JLabel lb_dname=new JLabel("骑手:"+dname);
					JLabel lb_fprice=new JLabel("总金额:"+res_order.getDouble("price"));
					JLabel lb_fstatus=new JLabel("订单状态:"+res_order.getString("status"));
					JLabel lb_fdate=new JLabel("订单时间:"+res_order.getString("oid"));
					
				
					JButton bt_enter=new JButton("查看详情");
					
					JPanel panelR=new JPanel();
					panelR.setLayout(null);
					panelR.setBounds(180, startY+20, width, 100);
					panelR.add(lb_fpic);
					panelR.add(lb_fname);
					panelR.add(lb_dname);
					panelR.add(lb_fprice);
					panelR.add(lb_fstatus);
					panelR.add(lb_fdate);
					panelR.add(bt_enter);
					
					
					panelR.setBackground(new Color(255,255,255));
					
					startY+=120;
					lb_fpic.setBounds(50,10, 80, 80);
					lb_fname.setBounds(200, 20, 150, 30);
					lb_dname.setBounds(380, 20, 180, 30);
					bt_enter.setBounds(740,20,120,30);
			
					lb_fprice.setBounds(200, 60, 120, 30);
					lb_fstatus.setBounds(380,60,180,30);
					lb_fdate.setBounds(580, 60, 290, 30);
					
					bt_enter.setFont(font);
		
					lb_fname.setFont(font);
					lb_fprice.setFont(font);
					lb_fstatus.setFont(font);
					lb_fdate.setFont(font);
					lb_dname.setFont(font);
					
					bt_enter.setBorder(border);
	
					bt_enter.setBackground(Color.WHITE);
	
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
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void action3() {
			setColor(3);
			panelCenter.removeAll();
			panelCenter.repaint();
			panelCenter.removeAll();
			panelCenter.repaint();
			panelCenter.setPreferredSize(new Dimension(1200,50));
				
			Connection connection=new DbUtil().getConnection();
			String sql_order="select * from forder where rid=? and status='已完成' order by oid desc";
			try {
				PreparedStatement stmt_order=connection.prepareStatement(sql_order);
				stmt_order.setInt(1, restaurant.getRid());
				ResultSet res_order=stmt_order.executeQuery();
				int startY=0;
				int width=900;
				while(res_order.next()) {
					Order order=new Order();
					order.setOid(res_order.getString("oid"));
					
					panelCenter.setPreferredSize(new Dimension(1200,panelCenter.getPreferredSize().height+120));
					Image ig_fpic=Toolkit.getDefaultToolkit().createImage(restaurant.getImage());
					ImageIcon ic_fpic=new ImageIcon();
					ic_fpic.setImage(ig_fpic.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
					
					String uaccount="";
					String sql_user="select * from user where uid=?";
					PreparedStatement stmt_user=connection.prepareStatement(sql_user);
					stmt_user.setInt(1, res_order.getInt("uid"));
					ResultSet res_user=stmt_user.executeQuery();
					while(res_user.next()) {
						uaccount=res_user.getString("uaccount");
					}
					
					String dname="";
					String sql_deliever="select * from deliverer where did=?";
					PreparedStatement stmt_deliever=connection.prepareStatement(sql_deliever);
					stmt_deliever.setInt(1, res_order.getInt("did"));
					ResultSet res_deliverer=stmt_deliever.executeQuery();
					while(res_deliverer.next()) {
						dname=res_deliverer.getString("dname");
					}
					
					
					JLabel lb_fpic=new JLabel(ic_fpic);
					JLabel lb_fname=new JLabel("客户:"+uaccount);
					JLabel lb_dname=new JLabel("骑手:"+dname);
					JLabel lb_fprice=new JLabel("总金额:"+res_order.getDouble("price"));
					JLabel lb_fstatus=new JLabel("订单状态:"+res_order.getString("status"));
					JLabel lb_fdate=new JLabel("订单时间:"+res_order.getString("oid"));
					
					JButton bt_eva=new JButton("查看评价");
					JButton bt_enter=new JButton("查看详情");
					
					JPanel panelR=new JPanel();
					panelR.setLayout(null);
					panelR.setBounds(180, startY+20, width, 100);
					panelR.add(lb_fpic);
					panelR.add(lb_fname);
					panelR.add(lb_dname);
					panelR.add(lb_fprice);
					panelR.add(lb_fstatus);
					panelR.add(lb_fdate);
					panelR.add(bt_enter);
					panelR.add(bt_eva);
					
					panelR.setBackground(new Color(255,255,255));
					
					startY+=120;
					lb_fpic.setBounds(50,10, 80, 80);
					lb_fname.setBounds(200, 20, 150, 30);
					lb_dname.setBounds(380, 20, 180, 30);
					bt_enter.setBounds(740,20,120,30);
					bt_eva.setBounds(600,20,120,30);
					lb_fprice.setBounds(200, 60, 120, 30);
					lb_fstatus.setBounds(380,60,180,30);
					lb_fdate.setBounds(580, 60, 290, 30);
					
					bt_enter.setFont(font);
					bt_eva.setFont(font);
					lb_fname.setFont(font);
					lb_fprice.setFont(font);
					lb_fstatus.setFont(font);
					lb_fdate.setFont(font);
					lb_dname.setFont(font);
					
					bt_enter.setBorder(border);
					bt_eva.setBorder(border);
					bt_enter.setBackground(Color.WHITE);
					bt_eva.setBackground(Color.white);
					panelCenter.add(panelR);
					
					bt_eva.addActionListener(new ActionListener() {
						
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
								System.out.println(order.getOid());
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
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void action4() {
			setColor(4);
			panelCenter.removeAll();
			panelCenter.repaint();
			panelCenter.removeAll();
			panelCenter.repaint();
			panelCenter.setPreferredSize(new Dimension(1200,50));
				
			Connection connection=new DbUtil().getConnection();
			String sql_order="select * from forder where rid=? and status='已接单' order by oid desc";
			try {
				PreparedStatement stmt_order=connection.prepareStatement(sql_order);
				stmt_order.setInt(1, restaurant.getRid());
				ResultSet res_order=stmt_order.executeQuery();
				int startY=0;
				int width=900;
				while(res_order.next()) {
					Order order=new Order();
					order.setOid(res_order.getString("oid"));
					panelCenter.setPreferredSize(new Dimension(1200,panelCenter.getPreferredSize().height+120));
					Image ig_fpic=Toolkit.getDefaultToolkit().createImage(restaurant.getImage());
					ImageIcon ic_fpic=new ImageIcon();
					ic_fpic.setImage(ig_fpic.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
					
					String uaccount="";
					String sql_user="select * from user where uid=?";
					PreparedStatement stmt_user=connection.prepareStatement(sql_user);
					stmt_user.setInt(1, res_order.getInt("uid"));
					ResultSet res_user=stmt_user.executeQuery();
					while(res_user.next()) {
						uaccount=res_user.getString("uaccount");
					}
					
					
					JLabel lb_fpic=new JLabel(ic_fpic);
					JLabel lb_fname=new JLabel("客户:"+uaccount);
					JLabel lb_fprice=new JLabel("总金额:"+res_order.getDouble("price"));
					JLabel lb_fstatus=new JLabel("订单状态:"+res_order.getString("status"));
					JLabel lb_fdate=new JLabel("订单时间:"+res_order.getString("oid"));
					
				
					JButton bt_enter=new JButton("查看详情");
					
					JPanel panelR=new JPanel();
					panelR.setLayout(null);
					panelR.setBounds(180, startY+20, width, 100);
					panelR.add(lb_fpic);
					panelR.add(lb_fname);
					panelR.add(lb_fprice);
					panelR.add(lb_fstatus);
					panelR.add(lb_fdate);
					panelR.add(bt_enter);
				
					
					panelR.setBackground(new Color(255,255,255));
					
					startY+=120;
					lb_fpic.setBounds(50,10, 80, 80);
					lb_fname.setBounds(200, 20, 300, 30);
					bt_enter.setBounds(740,20,120,30);
					
					lb_fprice.setBounds(200, 60, 120, 30);
					lb_fstatus.setBounds(380,60,180,30);
					lb_fdate.setBounds(580, 60, 290, 30);
					
					bt_enter.setFont(font);
			
					lb_fname.setFont(font);
					lb_fprice.setFont(font);
					lb_fstatus.setFont(font);
					lb_fdate.setFont(font);
					
					bt_enter.setBorder(border);
				
					bt_enter.setBackground(Color.WHITE);
					
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
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void setColor(int location) {
			switch (location) {
			case 0:
				bt_food.setForeground(color);
				bt_food.setBackground(Color.WHITE);
				bt_food.setBorder(BorderFactory.createLineBorder(color));
				
				bt_order1.setForeground(Color.white);
				bt_order1.setBackground(color);
				bt_order1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				bt_order2.setForeground(Color.white);
				bt_order2.setBackground(color);
				bt_order2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				bt_order3.setForeground(Color.white);
				bt_order3.setBackground(color);
				bt_order3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				bt_order4.setForeground(Color.white);
				bt_order4.setBackground(color);
				bt_order4.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				break;
			case 1:
				bt_order1.setForeground(color);
				bt_order1.setBackground(Color.WHITE);
				bt_order1.setBorder(BorderFactory.createLineBorder(color));
				
				bt_food.setForeground(Color.white);
				bt_food.setBackground(color);
				bt_food.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				bt_order2.setForeground(Color.white);
				bt_order2.setBackground(color);
				bt_order2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				bt_order3.setForeground(Color.white);
				bt_order3.setBackground(color);
				bt_order3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				bt_order4.setForeground(Color.white);
				bt_order4.setBackground(color);
				bt_order4.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				break;
			case 2:
				bt_order2.setForeground(color);
				bt_order2.setBackground(Color.WHITE);
				bt_order2.setBorder(BorderFactory.createLineBorder(color));
				
				bt_order1.setForeground(Color.white);
				bt_order1.setBackground(color);
				bt_order1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				bt_food.setForeground(Color.white);
				bt_food.setBackground(color);
				bt_food.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				bt_order3.setForeground(Color.white);
				bt_order3.setBackground(color);
				bt_order3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				bt_order4.setForeground(Color.white);
				bt_order4.setBackground(color);
				bt_order4.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				break;
			case 3:
				bt_order3.setForeground(color);
				bt_order3.setBackground(Color.WHITE);
				bt_order3.setBorder(BorderFactory.createLineBorder(color));
				
				bt_order1.setForeground(Color.white);
				bt_order1.setBackground(color);
				bt_order1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				bt_order2.setForeground(Color.white);
				bt_order2.setBackground(color);
				bt_order2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				bt_food.setForeground(Color.white);
				bt_food.setBackground(color);
				bt_food.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				bt_order4.setForeground(Color.white);
				bt_order4.setBackground(color);
				bt_order4.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				break;
			case 4:
				bt_order4.setForeground(color);
				bt_order4.setBackground(Color.WHITE);
				bt_order4.setBorder(BorderFactory.createLineBorder(color));
				
				bt_order1.setForeground(Color.white);
				bt_order1.setBackground(color);
				bt_order1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				bt_order2.setForeground(Color.white);
				bt_order2.setBackground(color);
				bt_order2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				bt_food.setForeground(Color.white);
				bt_food.setBackground(color);
				bt_food.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				bt_order3.setForeground(Color.white);
				bt_order3.setBackground(color);
				bt_order3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				break;
			default:
				break;
			}
		}
		
		public void addFood() {
			JDialog dialog=new JDialog();
			dialog.setLayout(null);
			dialog.setTitle("添加菜品");
			dialog.setBounds(700, 300, 700, 450);
			dialog.getContentPane().setBackground(color);
			
			JLabel lb_title=new JLabel("添加菜品");
			lb_title.setBounds(300, 50, 100, 50);
			lb_title.setFont(new Font("Serif",Font.BOLD,24));
			lb_title.setForeground(Color.white);
			
			JLabel lb_icon=new JLabel("菜品头像");
			lb_icon.setBounds(100, 110, 100, 50);
			lb_icon.setFont(font);
			lb_icon.setForeground(Color.white);
			
			ImageIcon ic_addFood=new ImageIcon("./image/add.jpg");
			ic_addFood.setImage(ic_addFood.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
			JButton lb_addFood=new JButton(ic_addFood);
			lb_addFood.setBounds(320, 110, 60, 60);
			lb_addFood.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					JFileChooser chooser = new JFileChooser();
				    FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "JPG & PNG Images", "jpg", "png");
				    File f =null;
				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showOpenDialog( new  TextField());
				    if(returnVal == JFileChooser.APPROVE_OPTION) { 
				      f= chooser.getSelectedFile(); 
				    fpicture0=f.getParent()+"\\"+f.getName();
				    Image image=Toolkit.getDefaultToolkit().getImage(fpicture0);
				    ic_addFood.setImage(image.getScaledInstance(100,100, Image.SCALE_DEFAULT));
				    }
				}
			});
			
			JLabel lb_name=new JLabel("菜品名称");
			lb_name.setBounds(100, 180, 100, 50);
			lb_name.setFont(font);
			lb_name.setForeground(Color.white);
			
			JTextField tx_name=new JTextField();
			tx_name.setBounds(220,190,300,35);
			tx_name.setFont(font);
			tx_name.setBorder(BorderFactory.createEmptyBorder());
			
			JLabel lb_price=new JLabel("菜品价格");
			lb_price.setBounds(100, 250, 100, 50);
			lb_price.setFont(font);
			lb_price.setForeground(Color.white);
			
			JTextField tx_price=new JTextField();
			tx_price.setBounds(220,260,300,35);
			tx_price.setFont(font);
			tx_price.setBorder(BorderFactory.createEmptyBorder());
		
			JButton bt_sure=new JButton("确认");
			bt_sure.setBounds(270, 320, 150, 30);
			bt_sure.setForeground(Color.white);
			bt_sure.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			bt_sure.setBackground(color);
			bt_sure.setFont(font);
			
			bt_sure.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String fname=tx_name.getText();
					double fprice=Double.parseDouble(tx_price.getText());
					String rname=restaurant.getRname();
					int rid=restaurant.getRid();
					String fpicture="E:\\JAVA\\workspace\\TakeOutSystem\\ficon\\"+fname+"_"+rname+".jpg";
					try {
						File in=new File(fpicture0);
						FileInputStream inputStream=new FileInputStream(in);
						File out=new File(fpicture);
						FileOutputStream outputStream=new FileOutputStream(out);
						int ch;
						ch = inputStream.read();
						while(ch!=-1) {
							outputStream.write(ch);
							ch=inputStream.read();
						}
					}catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String sql="insert into food(fname,fprice,rid,fpicture)values(?,?,?,?)";
					PreparedStatement preparedStatement;
					try {
						preparedStatement = new DbUtil().getConnection().prepareStatement(sql);
						preparedStatement.setString(1, fname);
						preparedStatement.setDouble(2, fprice);
						preparedStatement.setInt(3, rid);
						preparedStatement.setString(4, fpicture);
						preparedStatement.executeUpdate();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					paneCenterRight.removeAll();
					action0();
					paneCenterRight.repaint();
					dialog.dispose();
				}				
			});
			dialog.add(lb_title);
			dialog.add(lb_icon);
			dialog.add(lb_name);
			dialog.add(lb_price);
			dialog.add(bt_sure);
			dialog.add(tx_name);
			dialog.add(tx_price);
			dialog.add(lb_addFood);
			dialog.show();
		}
		
}
