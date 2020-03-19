package welcome;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class updategoods extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updategoods frame = new updategoods(args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public updategoods(Object[] as) throws Exception{
	
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商品号：");
		lblNewLabel.setBounds(39, 91, 54, 15);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(100, 88, 178, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(as[0].toString());
		
		
		JLabel lblNewLabel_1 = new JLabel("商品名：");
		lblNewLabel_1.setBounds(39, 145, 54, 15);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 142, 178, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(as[1].toString());
		
		
		
		JLabel lblNewLabel_2 = new JLabel("单价：");
		lblNewLabel_2.setBounds(39, 193, 54, 15);
		getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(100, 190, 178, 21);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		if(as[2]!=null)
		{textField_2.setText(as[2].toString());}
	
	
		
		JButton btnNewButton = new JButton("保存");
		btnNewButton.setBounds(281, 478, 93, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("售价：");
		lblNewLabel_3.setBounds(39, 224, 54, 15);
		getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(100, 221, 178, 21);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		if(as[3]!=null)
		{textField_3.setText(as[3].toString());}
		
		JLabel lblNewLabel_4 = new JLabel("销量：");
		lblNewLabel_4.setBounds(39, 265, 54, 15);
		getContentPane().add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(100, 262, 178, 21);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		if(as[4]!=null)
		{textField_4.setText(as[4].toString());}
		
	
		
		JLabel lblNewLabel_5 = new JLabel("库存：");
		lblNewLabel_5.setBounds(39, 307, 54, 15);
		getContentPane().add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setBounds(100, 304, 178, 21);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		if(as[5]!=null)
		{textField_5.setText(as[5].toString());}
		
		JLabel lblNewLabel_6 = new JLabel("摆放货柜：");
		lblNewLabel_6.setBounds(39, 352, 79, 15);
		getContentPane().add(lblNewLabel_6);
		
		textField_6 = new JTextField();
		textField_6.setBounds(100, 349, 178, 21);
		getContentPane().add(textField_6);
		textField_6.setColumns(10);
		if(as[6]!=null)
		{textField_6.setText(as[6].toString());}
		
		JLabel lblNewLabel_7 = new JLabel("备注：");
		lblNewLabel_7.setBounds(39, 402, 54, 15);
		getContentPane().add(lblNewLabel_7);
		
		textField_7 = new JTextField();
		textField_7.setBounds(100, 399, 178, 21);
		getContentPane().add(textField_7);
		textField_7.setColumns(10);
		if(as[7]!=null)
		{textField_7.setText(as[7].toString());}
		
		JLabel lblNewLabel_8 = new JLabel("商品类型：");
		lblNewLabel_8.setBounds(39, 445, 79, 15);
		getContentPane().add(lblNewLabel_8);
		
		textField_8 = new JTextField();
		textField_8.setBounds(100, 442, 178, 21);
		getContentPane().add(textField_8);
		textField_8.setColumns(10);
		if(as[8]!=null)
		{textField_8.setText(as[8].toString());}
		
		btnNewButton.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String[] upd=new String[9];
				upd[0]=textField.getText();
				upd[1]= textField_1.getText();
				upd[2]=textField_2.getText();
				upd[3]=textField_3.getText();
				upd[4]=textField_4.getText();
				upd[5]=textField_5.getText();
				upd[6]=textField_6.getText();
				upd[7]=textField_7.getText();
				upd[8]=textField_8.getText();
				try {
					Goods.updategoods(upd);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		
		

	}
}
