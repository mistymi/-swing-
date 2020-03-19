package welcome;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JPanel;

public class register {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register window = new register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("系统用户登录");
		frame.setBounds(400, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		JPanel panel = new JPanel();
		panel.setBounds(0, 251, 434, -226);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("您好，欢迎使用商品管理系统，请先登录");
		lblNewLabel.setBounds(109, 10, 273, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("用户名不能为空");
		lblNewLabel_1.setBounds(328, 75, 96, 15);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		JLabel lblNewLabel_2 = new JLabel("密码不能为空");
		lblNewLabel_2.setBounds(328, 122, 84, 15);
		frame.getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		
		JLabel label = new JLabel("用户名：");
		label.setBounds(53, 75, 54, 15);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(117, 72, 195, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
	
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setBounds(53, 122, 54, 15);
		frame.getContentPane().add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(117, 119, 195, 21);
		frame.getContentPane().add(passwordField);
		
		
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.setBounds(85, 186, 93, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					String user=textField.getText();
					String password=passwordField.getText();
					if(user.equals(""))
						lblNewLabel_1.setVisible(true);//用户名为空时提示“用户名不能为空”
					if(password.equals(""))
						lblNewLabel_2.setVisible(true);//密码为空时提示“密码不能为空”
					if(!user.equals("")&&!password.equals(""))//用户名和密码非空时，检验是否正确
					valid(user,password);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.setBounds(243, 186, 93, 23);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
				
				
			}
		});
		
		
	}
	
	public void valid(String a,String b) throws SQLException {
		String a_user=a,b_password=b,pa = null;
	
		
		 ConnectionFactory factory=new ConnectionFactory();
			String dburl="jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mistystore";//得到数据连接
			Connection conn=factory.getConnection(dburl);//得到数据块
			Statement stmt=conn.createStatement();
			String sqlstr="select Password from Tab_user where Username='"+a_user+"'";//输入模糊名称进行查询
			ResultSet rs=stmt.executeQuery(sqlstr);//执行SQL语句
			ResultSetMetaData rsmd = rs.getMetaData();
			if(rs.next())
				pa=rs.getString("Password");
			if(b_password.equals(pa))
			{ JOptionPane.showMessageDialog(null, "登录成功！");
			mainfest window = new mainfest();
			window.frame.setVisible(true);
			frame.setVisible(false);
			
			}
			
			else 
				JOptionPane.showMessageDialog(null, "密码错误，请重新输入");
			
	
	
			
			
			
		
		
	}
}
