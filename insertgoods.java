package welcome;
/*�ظ�����������������*/
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;

public class insertgoods extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					insertgoods frame = new insertgoods();
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
	
	
	
	public insertgoods()  throws Exception{
		DefaultTableModel model = new DefaultTableModel();
		Vector columnNames = new Vector();
		columnNames.add("��Ʒ��");
		columnNames.add("��Ʒ����");
		columnNames.add("����");
		columnNames.add("�ۼ�");
		columnNames.add("����");
		columnNames.add("���");
		columnNames.add("�ڷŻ���");
		columnNames.add("��ע");
		columnNames.add("��Ʒ����");
	
		
		String a="��������Ʒ666";
		Vector data=Goods.selectname(a);//����һ����������Ʒ
		
		this.setTitle("��⴦��");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable(model);
		JScrollPane jsp=new JScrollPane(table);
		jsp.setBounds(10, 60, 629, 393);
		contentPane.add(jsp);
		
		JButton btnNewButton = new JButton("���");//��ӿ���
		btnNewButton.setBounds(533, 24, 93, 23);
		contentPane.add(btnNewButton);
		model.setDataVector(data,columnNames);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				model.addRow(new Vector());
			}
		});
		
		btnNewButton_1 = new JButton("����");
		btnNewButton_1.setBounds(477, 479, 93, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e) {
				try {
					int row=model.getRowCount();
					int column=model.getColumnCount();
					Object [][]value=new Object[row][column];
					//System.out.println(table.getCellEditor().isCellEditable(e));
					//if(table.getCellEditor().isCellEditable(e))
					// table.getCellEditor().stopCellEditing();//ǿ�ƽ����༭״̬
					for(int i=0;i<row;i++)
						{for(int j=0;j<column;j++)
							value[i][j]=table.getValueAt(i,j);}//�����������װ��value��
					Goods.insertgoods(value,row);
		            model.setRowCount(0);//�������ձ������ظ���ȡ�Ͳ���
			
				}
				catch (Exception e1) {e1.printStackTrace();}
				
			}
		});
		
		
	}

}
