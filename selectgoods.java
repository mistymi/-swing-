package welcome;

/*�޸�ɾ��Ĭ��ѡ��*/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTable;

public   class  selectgoods {

	public  JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selectgoods window = new selectgoods();
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
	public selectgoods() {
		try{initialize();}
		catch (Exception ex) {
			ex.printStackTrace();
			}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	JTextField txtname=new JTextField();//�����ı���
	private final JButton ok = new JButton("ȷ��");//ȷ����ť
	DefaultTableModel model = new DefaultTableModel();
	JTable txtaddr=new JTable(model);
    JScrollPane jsp=new JScrollPane(txtaddr);
	Vector columnNames = new Vector();
	private void initialize() throws Exception {
		columnNames.add("��Ʒ��");
		columnNames.add("��Ʒ����");
		columnNames.add("����");
		columnNames.add("�ۼ�");
		columnNames.add("����");
		columnNames.add("���");
		columnNames.add("�ڷŻ���");
		columnNames.add("��ע");
		columnNames.add("��Ʒ����");
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 20, 900,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("��Ʒ��Ϣ��ѯ");
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("��������Ʒ��Ϣ��");
		lblNewLabel.setBounds(10, 24, 114, 31);
		frame.getContentPane().add(lblNewLabel);
	
		JLabel lblNewLabel_1 = new JLabel("��ѯ�����");
		lblNewLabel_1.setBounds(10, 140, 96, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtname.setBounds(104,63,338,25);
		frame.getContentPane().add(txtname);	
			
		JLabel lblNewLabel_2 = new JLabel("��Ʒ���ƣ�");
		lblNewLabel_2.setBounds(10, 65, 86, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		ok.setBounds(482, 60, 96, 31);//��ѯ��Ʒ
		frame.getContentPane().add(ok);
		ok.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				ok_actionPerformed() ;
			}
		
	  });//����,����
		
		jsp.setBounds(65,180,800,400);
		frame.getContentPane().add(jsp);
		
		JButton add = new JButton("���");//����һ��
		add.setBounds(536, 141, 93, 23);
		frame.getContentPane().add(add);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			insertgoods add;
			try {
				add = new insertgoods();
				add.setVisible(true);
			} 
			catch (Exception e1) {e1.printStackTrace();}
		
		
			}
		});
		
		JButton delete = new JButton("ɾ��");//ɾ��һ��
		delete.setBounds(642, 141, 93, 23);
		frame.getContentPane().add(delete);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int confi=	JOptionPane.showConfirmDialog(null, "ȷ��ɾ������Ʒ��������Ϣ��");//Ĭ��ѡ����ô�޸ģ�
			//System.out.println(confi);
			if (confi==0)
				{try {
					deletevent();
				} catch (Exception e1) {
					e1.printStackTrace();
				}}
			}
		});
		
		JButton save = new JButton("�޸�");
		save.setBounds(745, 141, 93, 23);
		frame.getContentPane().add(save);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object[] Gnos=updateevent();
				updategoods  bb;
				 try {
					bb=new updategoods( Gnos);//����Ʒ�Ŵ��ݵ��޸Ľ���
					 bb.setVisible(true);
				}
				 catch (Exception e1) {e1.printStackTrace();}
				
					
			}
		});
		
		
		
		
		
		
	}
	
	//���û�������ȷ������ťʱ��ִ�����²���
			public  Vector ok_actionPerformed() {
				String name=txtname.getText();//�õ�Ҫ��ѯ����Ʒ����
				Goods goods=new Goods();
				boolean flag=false;
				Vector rowdata;
				//int i=0,j=0;
				Vector datatemp=null;
				//String[] datatemp=null;
				try {
					//����ҵ�񷽷�����������
					
					datatemp=Goods.selectname(name);
					flag=true;
				
				}
				catch(Exception ex) {ex.printStackTrace();}
				if (flag)
				{
			 
		            model.setDataVector(datatemp, columnNames);
		         
				}//�����ص���������ı���*/
			    return datatemp;
			}
			
			public void  deletevent() throws Exception{
				int rowcount = txtaddr.getSelectedRow();
			Object as=txtaddr.getValueAt(rowcount, 0);//���ɾ���е���Ʒ��
				if(rowcount >= 0){
					Goods.deletegoods(as);
					model.removeRow(rowcount);
					
				}
				JOptionPane.showMessageDialog(null, "ɾ���ɹ���");//ɾ������ʾ
				

			}
			
			
			public Object[] updateevent() {
				
				
				 int aa=txtaddr.getSelectedRow();
				 Object []zc=new Object[9];
				zc[0]= model.getValueAt(aa, 0);
				zc[1]=model.getValueAt(aa, 1);
				zc[2]=model.getValueAt(aa, 2);
				zc[3]=model.getValueAt(aa, 3);
				zc[4]=model.getValueAt(aa, 4);
				zc[5]=model.getValueAt(aa, 5);
				zc[6]=model.getValueAt(aa, 6);
				zc[7]=model.getValueAt(aa, 7);
				zc[8]=model.getValueAt(aa, 8);
			
				return zc;//�����ѡ�е���Ʒ�Ų�����
				
			
}
}
	