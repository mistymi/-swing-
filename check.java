package welcome;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class check extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					check frame = new check(null, 0, 0);
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
	public check(Object[][] zd ,int r,int c) {
		Object[][]Bills=zd;
		int row=r;
		int column=c;
		this.setTitle("账单");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("流水号：");
		lblNewLabel.setBounds(10, 31, 54,15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(67, 28, 115, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(Bills[0][0].toString());
		
		JLabel lblNewLabel_1 = new JLabel("时间：");
		lblNewLabel_1.setBounds(204, 31, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(245, 28, 157, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(Bills[0][1].toString());
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 68, 399, 335);
		contentPane.add(textArea);
		String []pay=new String[row];
		StringBuffer s=new StringBuffer();
		double money=0;
		for(int i=0;i<row;i++)
		 {
		pay[i]=Bills[i][2].toString()+"\t"+Bills[i][3].toString()+"\t"+Bills[i][5].toString()+"\t"+Bills[i][6].toString()+"\t"+Bills[i][7].toString();
		 money=money+Double.parseDouble(Bills[i][7].toString());
		 }
	for(int j=0;j<row;j++) {
		s.append(pay[j]+"\r\n");
	}
	textArea.setText("商品序号\t商品名称\t价格\t数量\t总额\r\n"+s.toString()+"\r\n");
	
	JTextArea textArea_1 = new JTextArea();
	textArea_1.setBounds(10, 403, 399, 107);
	contentPane.add(textArea_1);
	textArea_1.setText("------------------------------------------------------------------\r\n应付款："+money+"元");
		
	}
}
