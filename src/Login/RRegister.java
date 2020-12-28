package Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import Database.DbUtil;

public class RRegister extends JFrame {

	private JPanel contentPane;
	
	private String raccount;
	private String rpsd;
	private String rname;
	private String rlocation;
	private String rphonenum;
	private String ricon0;
	private String ricon;

	public RRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420,150, 1080, 800);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(240,189,20));
		setContentPane(contentPane);
		setTitle("≤Õπ›◊¢≤·");
		setVisible(true);
		
		JLabel  lb_title=new JLabel("≤Õπ›◊¢≤·");
		JLabel lb_account=new JLabel("«Î ‰»Î’À∫≈:");
		JLabel lb_password=new JLabel("«Î ‰»Î√‹¬Î:");
		JLabel lb_password2=new JLabel("«Î»∑»œ√‹¬Î:");
		JLabel lb_rname=new JLabel("«Î ‰»Î≤Õπ›√˚≥∆:");
		JLabel lb_raddress=new JLabel("«Î ‰»Î≤Õπ›µÿ÷∑:");
		JLabel lb_rphonenum=new JLabel("«Î ‰»Î¡™œµ∫≈¬Î:");
		JLabel lb_ricon=new JLabel("«ÎÃÌº”≤Õπ›Õ∑œÒ:");
		
		
		JTextField tx_account=new JTextField();
		JPasswordField tx_password=new JPasswordField();
		JPasswordField tx_password2=new JPasswordField();
		JTextField tx_rname=new JTextField();
		JTextField tx_raddress=new JTextField();
		JTextField tx_rphonenum=new JTextField();
		
		Image ic=Toolkit.getDefaultToolkit().getImage("./image/add.jpg");
		ImageIcon icIc=new ImageIcon();
		icIc.setImage(ic.getScaledInstance(100,100, Image.SCALE_DEFAULT));
		JButton lb_ic=new JButton(icIc);
		lb_ic.setBackground(Color.WHITE);
		lb_ic.setBounds(550,520,100,100);
		
		lb_ic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "JPG & PNG Images", "jpg", "png");
			    File f =null;
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog( new  TextField());
			    if(returnVal == JFileChooser.APPROVE_OPTION) { 
			      f= chooser.getSelectedFile(); 
			    ricon0=f.getParent()+"\\"+f.getName();
			    Image image=Toolkit.getDefaultToolkit().getImage(ricon0);
			    icIc.setImage(image.getScaledInstance(100,100, Image.SCALE_DEFAULT));
			    }
			}
		});
		
		
		
		JButton bt_ok=new JButton("◊¢≤·");
		bt_ok.setFont(new Font("Serif",Font.BOLD,20));
		bt_ok.setBackground(Color.WHITE);
		bt_ok.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		bt_ok.setBounds(450, 650, 200, 50);
		
		lb_title.setBounds(450, 50, 200, 50);
		lb_title.setFont(new Font("Serif",Font.BOLD,30));
		
		lb_account.setBounds(250, 125, 200, 50);
		lb_account.setFont(new Font("Serif",Font.BOLD,20));
		
		lb_password.setBounds(250, 185, 200, 50);
		lb_password.setFont(new Font("Serif",Font.BOLD,20));
		
		lb_password2.setBounds(250, 245, 200, 50);
		lb_password2.setFont(new Font("Serif",Font.BOLD,20));
		
		lb_rname.setBounds(250, 305, 200, 50);
		lb_rname.setFont(new Font("Serif",Font.BOLD,20));
		
		lb_raddress.setBounds(250, 365, 200, 50);
		lb_raddress.setFont(new Font("Serif",Font.BOLD,20));
		
		lb_rphonenum.setBounds(250,425, 350, 50);
		lb_rphonenum.setFont(new Font("Serif",Font.BOLD,20));
		
		lb_ricon.setBounds(250,550, 350, 50);
		lb_ricon.setFont(new Font("Serif",Font.BOLD,20));
		
		tx_account.setBounds(450, 130, 350, 40);
		tx_account.setFont(new Font("Serif",Font.BOLD,20));
		tx_account.setBorder(BorderFactory.createEmptyBorder());
		
		tx_password.setBounds(450, 190, 350, 40);
		tx_password.setFont(new Font("Serif",Font.BOLD,20));
		tx_password.setBorder(BorderFactory.createEmptyBorder());
		
		tx_password2.setBounds(450, 250, 350, 40);
		tx_password2.setFont(new Font("Serif",Font.BOLD,20));
		tx_password2.setBorder(BorderFactory.createEmptyBorder());
		
		tx_rname.setBounds(450, 310, 350, 40);
		tx_rname.setFont(new Font("Serif",Font.BOLD,20));
		tx_rname.setBorder(BorderFactory.createEmptyBorder());
		
		tx_raddress.setBounds(450, 370, 350, 40);
		tx_raddress.setFont(new Font("Serif",Font.BOLD,20));
		tx_raddress.setBorder(BorderFactory.createEmptyBorder());
		
		tx_rphonenum.setBounds(450,430, 350, 40);
		tx_rphonenum.setFont(new Font("Serif",Font.BOLD,20));
		tx_rphonenum.setBorder(BorderFactory.createEmptyBorder());
			
		
		contentPane.add(lb_title);
		contentPane.add(lb_account);
		contentPane.add(lb_password);
		contentPane.add(lb_password2);
		contentPane.add(lb_rname);
		contentPane.add(lb_raddress);
		contentPane.add(lb_rphonenum);
		contentPane.add(tx_account);
		contentPane.add(tx_password);
		contentPane.add(tx_password2);
		contentPane.add(tx_rname);
		contentPane.add(tx_raddress);
		contentPane.add(tx_rphonenum);
		contentPane.add(bt_ok);
		contentPane.add(lb_ricon);
		contentPane.add(lb_ic);
		
	bt_ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!tx_password.getText().equals(tx_password2.getText())) {
					JOptionPane.showConfirmDialog(null, "¡Ω¥Œ ‰»Îµƒ√‹¬Î≤ª“ª÷¬£°");
					return;
				}
				raccount=tx_account.getText();
				rpsd=tx_password.getText();
				rname=tx_rname.getText();
				rlocation=tx_raddress.getText();
				rphonenum=tx_rphonenum.getText();
				ricon="E:\\JAVA\\workspace\\TakeOutSystem\\ricon\\"+rname+".jpg";
				DbUtil util=new DbUtil();
				Connection connection=util.getConnection();
				String sql="insert into restaurant(raccount,rpsd,rname,rlocation,rphonenum,ricon) values(?,?,?,?,?,?)";
				try {
					PreparedStatement statement=connection.prepareStatement(sql);
					statement.setString(1, raccount);
					statement.setString(2, rpsd);
					statement.setString(3, rname);
					statement.setString(4, rlocation);
					statement.setString(5, rphonenum);
					statement.setString(6, ricon);
					statement.executeUpdate();
					
					String sql2="insert into u values(?,?,?)";
					PreparedStatement statement2=connection.prepareStatement(sql2);
					statement2.setString(1, raccount);
					statement2.setString(2, rpsd);
					statement2.setString(3, "restaurant");
					statement2.executeUpdate();
					
					try {
						File in=new File(ricon0);
						FileInputStream inputStream=new FileInputStream(in);
						File out=new File(ricon);
						FileOutputStream outputStream=new FileOutputStream(out);
						
						int ch;
						ch = inputStream.read();
						while(ch!=-1) {
							outputStream.write(ch);
							ch=inputStream.read();
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					JDialog dialog=new JDialog();
					dialog.setTitle("◊¢≤·");
					dialog.setBounds(600, 420, 700, 250);
					dialog.setLayout(null);
					
					JLabel label=new JLabel("◊¢≤·≥…π¶");
					label.setFont(new Font("Serif", Font.BOLD, 20));
					label.setBounds(300, 50, 300, 50);

					JButton bt_next=new JButton("»∑»œ");
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
