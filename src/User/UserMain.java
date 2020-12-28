package User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.mysql.fabric.xmlrpc.base.Data;
import com.mysql.jdbc.interceptors.ResultSetScannerInterceptor;

import Database.DbUtil;
import Order.Order;
import Restaurant.Food;
import Restaurant.Restaurant;
import Restaurant.RestaurantMain;

public class UserMain extends JFrame {
	JPanel contentPane,panelNorth,panelCenter;
	String account;
	Color color=new Color(240,189,20);
	Font font=new Font("Serif",Font.BOLD,20);
	Border border=BorderFactory.createLineBorder(Color.black);
	JButton bt_order1,bt_order2,bt_order3,bt_order4;
	
	User user;
	double price;
	public UserMain(String account) {
		this.account=account;
		user=new User();
		String sql_user="select * from user where uaccount =?";
		Connection connection=new DbUtil().getConnection();
		PreparedStatement stmt_user;
		try {
			stmt_user = connection.prepareStatement(sql_user);
			stmt_user.setString(1, account);
			ResultSet rs_user=stmt_user.executeQuery();
			while(rs_user.next()) {
				user.setUname(rs_user.getString("uname"));
				user.setUaddress(rs_user.getString("uaddress"));
				user.setUphonenum(rs_user.getString("uphonenum"));
				user.setUid(rs_user.getInt("uid"));
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		contentPane=new JPanel();
		contentPane.setLayout(new BorderLayout());
		setBounds(320,154,1280,770);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("用户系统");
		setContentPane(contentPane);
	
		
		panelNorth=new JPanel();
		panelCenter=new JPanel();
		
		panelNorth.setBackground(color);
		panelNorth.setPreferredSize(new Dimension(1200, 130));
		panelCenter.setPreferredSize(new Dimension(1200,50));
		panelNorth.setLayout(null);
		panelCenter.setLayout(null);
		contentPane.add(panelCenter,BorderLayout.CENTER);
		contentPane.add(panelNorth,BorderLayout.NORTH);
		
		bt_order1=new JButton("购买食品");
		bt_order1.setFont(new Font("Serif",Font.BOLD,20));
		bt_order1.setForeground(Color.white);
		bt_order1.setBackground(color);
		bt_order1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		bt_order1.setBounds(100, 50, 150, 33);
		
		bt_order3=new JButton("订单记录");
		bt_order3.setFont(new Font("Serif",Font.BOLD,20));
		bt_order3.setForeground(Color.white);
		bt_order3.setBackground(color);
		bt_order3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		bt_order3.setBounds(300, 50, 150, 33);
		
		bt_order4=new JButton("个人信息");
		bt_order4.setFont(new Font("Serif",Font.BOLD,20));
		bt_order4.setForeground(Color.white);
		bt_order4.setBackground(color);
		bt_order4.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		bt_order4.setBounds(500, 50, 150, 33);
		
		panelNorth.add(bt_order1);
		panelNorth.add(bt_order3);
		panelNorth.add(bt_order4);
			
		action1();
		bt_order1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				action1();
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
		
		JScrollPane scrollPane=new JScrollPane(panelCenter);
		scrollPane.setBounds(0, 0, 1300, 770);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane);
		JLabel lb_icon=new JLabel();
	}
	
	public void action1() {
		setColor(1);
		panelCenter.removeAll();
		panelCenter.repaint();
		panelCenter.setPreferredSize(new Dimension(1200,50));
		Connection connection=new DbUtil().getConnection();
		
		String sql="select * from restaurant";
		try {
			
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(sql);
			
			int startY=0;
			int height=0;
			int width=900;
			while(resultSet.next()) {
				Restaurant restaurant=new Restaurant();
				restaurant.setImage(resultSet.getString("ricon"));
				restaurant.setRname(resultSet.getString("rname"));
				restaurant.setRphonenum(resultSet.getString("rphonenum"));
				restaurant.setRid(resultSet.getInt("rid"));
				
				panelCenter.setPreferredSize(new Dimension(1200,panelCenter.getPreferredSize().height+120));
				Image ig_fpic=Toolkit.getDefaultToolkit().createImage(restaurant.getImage());
				ImageIcon ic_fpic=new ImageIcon();
				ic_fpic.setImage(ig_fpic.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
				
				JLabel lb_fpic=new JLabel(ic_fpic);
				JLabel lb_fname=new JLabel("店家名称:"+restaurant.getRname());
				JLabel lb_fprice=new JLabel("联系电话:"+restaurant.getRphonenum());
				
				
				JPanel panelFood=new JPanel();
				panelFood.setLayout(null);
				panelFood.setBounds(180, startY+20, width, 100);
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
				
		
				JButton bt_enter=new JButton("进店查看");
				bt_enter.setFont(font);
				bt_enter.setBorder(border);
				bt_enter.setBounds(750, 30, 100, 50);
				bt_enter.setBackground(Color.WHITE);
				
				panelFood.add(bt_enter);
				
				panelCenter.add(panelFood);
				
				
				bt_enter.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						panelCenter.removeAll();
						panelCenter.repaint();
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
								food.setRid(resultSet.getInt("rid"));
								Image ig_fpic=Toolkit.getDefaultToolkit().createImage(food.getFpicture());
								ImageIcon ic_fpic=new ImageIcon();
								ic_fpic.setImage(ig_fpic.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
								
								JLabel lb_fpic=new JLabel(ic_fpic);
								JLabel lb_fname=new JLabel("名称:"+food.getFname());
								JLabel lb_fprice=new JLabel("价格:"+food.getFprice());
								
								JPanel panelFood=new JPanel();
								panelFood.setLayout(null);
								panelFood.setBounds(120, startY+20, width, 100);
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
								
								JButton bt_add=new JButton("加入购物车");
								bt_add.setFont(font);
								bt_add.setBorder(border);
								bt_add.setBounds(700, 30, 150, 50);
								bt_add.setBackground(Color.WHITE);
								
								JButton bt_m=new JButton("-");
								bt_m.setFont(font);
								bt_m.setBorder(border);
								bt_m.setBounds(460, 30, 50, 50);
								bt_m.setBackground(Color.WHITE);
								
								int num=1;
								JLabel lb_num=new JLabel(num+"");
								
								lb_num.setFont(font);
								lb_num.setBorder(BorderFactory.createEmptyBorder());
								lb_num.setBounds(550, 30, 50, 50);
								lb_num.setBackground(Color.WHITE);
								
								JButton bt_a=new JButton("+");
								bt_a.setFont(font);
								bt_a.setBorder(border);
								bt_a.setBounds(600, 30, 50, 50);
								bt_a.setBackground(Color.WHITE);
								
								bt_m.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										lb_num.setText((Integer.parseInt(lb_num.getText()))-1+"");
									}
								});
								
								bt_a.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										lb_num.setText((Integer.parseInt(lb_num.getText()))+1+"");
									}
								});
								
								panelFood.add(bt_a);
								panelFood.add(lb_num);
								panelFood.add(bt_m);
								panelFood.add(bt_add);
								
								bt_add.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										String uaccount=account;
										int fid=food.getFid();
										int rid=food.getRid();
										int num=Integer.parseInt(lb_num.getText());
										Connection connection=new DbUtil().getConnection();
										
										String sql_add="insert into shoppingcart values(?,?,?,?)";
										try {
											PreparedStatement stm1=connection.prepareStatement(sql_add);
											stm1.setInt(1, rid);
											stm1.setInt(2, fid);
											stm1.setString(3, uaccount);
											stm1.setInt(4, num);
											stm1.executeUpdate();
											JOptionPane.showMessageDialog(null, "加入成功");
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										
									}
								});
								
								JButton bt_cart=new JButton("查看购物车");
								bt_cart.setFont(font);
								bt_cart.setBorder(border);
								bt_cart.setBounds(1070, 52, 130, 50);
								bt_cart.setBackground(Color.WHITE);
								
								bt_cart.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										getCart(restaurant);
									}
								});
								
								panelCenter.add(bt_cart);
								panelCenter.add(panelFood);
								
							}
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
	
	public void action3() {
		setColor(3);
		panelCenter.removeAll();
		panelCenter.repaint();
		panelCenter.setPreferredSize(new Dimension(1200,50));
		Connection connection=new DbUtil().getConnection();
		String sql_order="select * from forder where uid=? order by oid desc";
		try {
			PreparedStatement stmt_order=connection.prepareStatement(sql_order);
			stmt_order.setInt(1, user.getUid());
			ResultSet res_order=stmt_order.executeQuery();
			int startY=0;
			int width=900;
			while(res_order.next()) {
				Order order=new Order();
				order.setOid(res_order.getString("oid"));
				Restaurant restaurant=new Restaurant();
				restaurant.setRid(res_order.getInt("rid"));
				String sql_restaurant="select * from restaurant where rid=?";
				PreparedStatement stmt_restaurant=connection.prepareStatement(sql_restaurant);
				stmt_restaurant.setInt(1, restaurant.getRid());
				ResultSet res_restaurant=stmt_restaurant.executeQuery();
				while(res_restaurant.next()) {
					restaurant.setImage(res_restaurant.getString("ricon"));
					restaurant.setRname(res_restaurant.getString("rname"));
					restaurant.setRphonenum(res_restaurant.getString("rphonenum"));				
				}
				
				panelCenter.setPreferredSize(new Dimension(1200,panelCenter.getPreferredSize().height+120));
				Image ig_fpic=Toolkit.getDefaultToolkit().createImage(restaurant.getImage());
				ImageIcon ic_fpic=new ImageIcon();
				ic_fpic.setImage(ig_fpic.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
				
				JLabel lb_fpic=new JLabel(ic_fpic);
				JLabel lb_fname=new JLabel("店家名称:"+restaurant.getRname());
				JLabel lb_fprice=new JLabel("总金额:"+res_order.getDouble("price"));
				JLabel lb_fstatus=new JLabel("订单状态:"+res_order.getString("status"));
				JLabel lb_fdate=new JLabel("订单时间:"+res_order.getString("oid"));
				
				JButton bt_enter=new JButton("查看详情");
				JButton bt_eva=new JButton("评价");
				
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
				bt_enter.setBounds(660,20,120,30);
				bt_eva.setBounds(520,20,120,30);
				lb_fprice.setBounds(200, 60, 120, 30);
				lb_fstatus.setBounds(340,60,180,30);
				lb_fdate.setBounds(530, 60, 290, 30);
				
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
				
				bt_eva.setVisible(false);
				if(res_order.getString("status").equals("已完成")) {
					bt_eva.setVisible(true);
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
							
							TextField tx_r=new TextField();
							tx_r.setFont(font);
							tx_r.setBounds(280,80,200,30);
							
							TextField tx_d=new TextField();
							tx_d.setFont(font);
							tx_d.setBounds(280,140,200,30);
							
							TextField tx_u=new TextField();
							tx_u.setFont(font);
							tx_u.setBounds(280,200,200,30);
							
							JButton bt_eva=new JButton("评价");
							bt_eva.setBackground(Color.white);
							bt_eva.setFont(font);
							bt_eva.setBorder(border);
							bt_eva.setBounds(260, 260, 150, 40);
							
							bt_eva.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									String sql_eva="insert into evalution(oid,reva,deva,ueva) values(?,?,?,?) ";
									PreparedStatement stmt_eva;
									try {
										stmt_eva = connection.prepareStatement(sql_eva);
										stmt_eva.setString(1,order.getOid());
										stmt_eva.setString(2,tx_r.getText());
										stmt_eva.setString(3,tx_d.getText());
										stmt_eva.setString(4,tx_u.getText());
										stmt_eva.executeUpdate();
										dialog.dispose();
									
										action3();
										JOptionPane.showMessageDialog(null, "评价成功");		
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
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
				
				
				
				String oid=res_order.getString("oid");
				bt_enter.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						price=0;
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
							stmt_cart.setString(1, oid);
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
									price+=food.getFprice()*rs_cart.getInt("num");
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
				panelCenter.add(panelR);
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
	
		JLabel lb_uaccount=new JLabel("账号:");
		JLabel lb_uname=new JLabel("称呼:");
		JLabel lb_uaddress=new JLabel("配送地址:");
		JLabel lb_uphonenum=new JLabel("手机号码:");
		
		JLabel lb_uaccount2=new JLabel(account);
		JTextField tx_uname=new JTextField();
		JTextField tx_uaddress=new JTextField();
		JTextField tx_uphonenum=new JTextField();
		
		JButton bt_ok=new JButton("修改");
		bt_ok.setFont(new Font("Serif",Font.BOLD,20));
		bt_ok.setBackground(Color.WHITE);
		bt_ok.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		bt_ok.setBounds(550, 460, 200, 50);
		
		
		lb_uaccount.setBounds(350, 65, 200, 50);
		lb_uaccount.setFont(new Font("Serif",Font.BOLD,20));
		
		lb_uname.setBounds(350, 145, 200, 50);
		lb_uname.setFont(new Font("Serif",Font.BOLD,20));
		
		lb_uaddress.setBounds(350, 225, 200, 50);
		lb_uaddress.setFont(new Font("Serif",Font.BOLD,20));
		
		lb_uphonenum.setBounds(350,305, 200, 50);
		lb_uphonenum.setFont(new Font("Serif",Font.BOLD,20));
	
		lb_uaccount2.setBounds(550, 70, 350, 40);
		lb_uaccount2.setFont(new Font("Serif",Font.BOLD,20));
		lb_uaccount2.setBorder(BorderFactory.createEmptyBorder());
		lb_uaccount2.setText(account);
		
		tx_uname.setBounds(550, 150, 350, 40);
		tx_uname.setFont(new Font("Serif",Font.BOLD,20));
		tx_uname.setBorder(BorderFactory.createEmptyBorder());
		
		
		tx_uaddress.setBounds(550, 230, 350, 40);
		tx_uaddress.setFont(new Font("Serif",Font.BOLD,20));
		tx_uaddress.setBorder(BorderFactory.createEmptyBorder());
		
		
		tx_uphonenum.setBounds(550,310, 350, 40);
		tx_uphonenum.setFont(new Font("Serif",Font.BOLD,20));
		tx_uphonenum.setBorder(BorderFactory.createEmptyBorder());
		
		
		panelCenter.add(lb_uname);
		panelCenter.add(lb_uaddress);
		panelCenter.add(lb_uphonenum);
		panelCenter.add(lb_uaccount);
		panelCenter.add(lb_uaccount2);
		panelCenter.add(tx_uname);
		panelCenter.add(tx_uaddress);
		panelCenter.add(tx_uphonenum);
		panelCenter.add(bt_ok);
		
	
		
		tx_uname.setText(user.getUname());
		tx_uaddress.setText(user.getUaddress());
		tx_uphonenum.setText(user.getUphonenum());
		
		bt_ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String uname=tx_uname.getText();
				String uaddress=tx_uaddress.getText();
				String uphonenum=tx_uphonenum.getText();
				DbUtil util=new DbUtil();
				Connection connection=util.getConnection();
				String sql_change="update user set uname=?,uaddress=?,uphonenum=? where uaccount =?";
				try {
					PreparedStatement stmt_change = connection.prepareStatement(sql_change);
					stmt_change.setString(1, uname);
					stmt_change.setString(2, uaddress);
					stmt_change.setString(3, uphonenum);
					stmt_change.setString(4, account);
					stmt_change.executeUpdate();
					action4();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
		
	public void setColor(int location) {
			switch (location) {
			
			case 1:
				bt_order1.setForeground(color);
				bt_order1.setBackground(Color.WHITE);
				bt_order1.setBorder(BorderFactory.createLineBorder(color));
				
				
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
				
				
				bt_order3.setForeground(Color.white);
				bt_order3.setBackground(color);
				bt_order3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				break;
				
			default:
				break;
			}
		}

	
	public void getCart(Restaurant restaurant) {
		// TODO Auto-generated method stub
		price=0;
		JDialog dialog=new JDialog();
		dialog.setBounds(450, 200, 1000, 600);
		dialog.setLayout(null);;
		
		JPanel panelCart=new JPanel();
		panelCart.setPreferredSize(new Dimension(920, 50));
		panelCart.setLayout(null);					
		
		JScrollPane sPane=new JScrollPane(panelCart);
		sPane.setBounds(0, 0, 980, 400);
		sPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		dialog.add(sPane);
		
		JLabel lb_price=new JLabel("总金额:");
		JButton bt_pay=new JButton("付款");
		
		
		lb_price.setFont(font);
		lb_price.setBounds(300, 450, 180, 50);
		
		bt_pay.setFont(font);
		bt_pay.setBorder(border);
		bt_pay.setBackground(Color.white);
		bt_pay.setBounds(500, 450, 150, 50);
		
		dialog.setTitle("购物车");
		dialog.add(lb_price);
		dialog.add(bt_pay);
		//dialog.add(panel);
		Connection connection=new DbUtil().getConnection();
		String sql_cart="select * from shoppingcart where rid = ? and uaccount= ?";
		int startX2=0;
		int startY2=0;
		try {
			
			PreparedStatement stmt_cart=connection.prepareStatement(sql_cart);
			stmt_cart.setInt(1, restaurant.getRid());
			stmt_cart.setString(2, account);
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
					price+=food.getFprice()*rs_cart.getInt("num");
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
					
					JButton bt_del=new JButton("移除");
					bt_del.setFont(font);
					bt_del.setBorder(border);
					bt_del.setBackground(Color.white);
					bt_del.setBounds(500, 30, 100, 40);
				
					
					panelFood.add(bt_del);
					panelFood.add(lb_fname);
					panelFood.add(lb_fpic);
					panelFood.add(lb_fprice);
					panelFood.add(lb_fnum);
					panelCart.add(panelFood);
					
					bt_del.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String sql_del="delete from shoppingcart where uaccount=? and fid=?";
							try {
								PreparedStatement stmt_del=connection.prepareStatement(sql_del);
								stmt_del.setString(1, account);
								stmt_del.setInt(2, food.getFid());
								stmt_del.executeUpdate();
								dialog.dispose();
								getCart(restaurant);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					
				}
				lb_price.setText("总金额: "+price+" 元");
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		bt_pay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			    java.util.Date date=new java.util.Date();
			    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String dateStr = format.format(date);
				
				String sql_pay="insert into forder(uid,rid,oid,status,price) values(?,?,?,?,?)";
				PreparedStatement stmt_pay;
				try {
					stmt_pay = connection.prepareStatement(sql_pay);
					stmt_pay.setInt(1, user.getUid());
					stmt_pay.setInt(2, restaurant.getRid());
					stmt_pay.setString(3, dateStr);
					stmt_pay.setString(4, "未接单");
					stmt_pay.setDouble(5,price);
					stmt_pay.executeUpdate();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
				
				String sql_pay_1="select * from shoppingcart where uaccount=? and rid=?";
				PreparedStatement stmt_pay_1;
				try {
					stmt_pay_1 = connection.prepareStatement(sql_pay_1);
					stmt_pay_1.setString(1, account);
					stmt_pay_1.setInt(2, restaurant.getRid());
					ResultSet res_pay1=stmt_pay_1.executeQuery();
					while(res_pay1.next()) {
						String sql_pay2="insert into order_food(oid,fid,num) values(?,?,?)";
						PreparedStatement stmt_pay2=connection.prepareStatement(sql_pay2);
						stmt_pay2.setString(1, dateStr);
						stmt_pay2.setInt(2, res_pay1.getInt("fid"));
						stmt_pay2.setInt(3, res_pay1.getInt("num"));
						stmt_pay2.executeUpdate();
						
						String sql_pay3="delete from shoppingcart where uaccount=? and fid=?";
						PreparedStatement stmt_pay3=connection.prepareStatement(sql_pay3);
						stmt_pay3.setString(1,account);
						stmt_pay3.setInt(2, res_pay1.getInt("fid"));
						stmt_pay3.executeUpdate();
						dialog.dispose();
						JOptionPane.showMessageDialog(null, "付款成功");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		dialog.show();
	}
}
