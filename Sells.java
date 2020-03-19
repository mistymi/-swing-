package welcome;

import java.awt.BorderLayout;
import java.util.StringTokenizer;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;

public class Sells extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sells frame = new Sells();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public String cells(String a,int i)
	{
		StringTokenizer aa=new StringTokenizer(a,", []");
		int j=0;String bb=null;
		while(j<=i&&aa.hasMoreTokens()) {
			bb=aa.nextToken();++j;
		}
		if(j>i) 
			return bb;
		else return null;
		
	}
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Sells() throws Exception {
		DefaultTableModel model = new DefaultTableModel();
		Vector columnNames = new Vector();
		columnNames.add("��ˮ��");
		columnNames.add("����ʱ��");
		columnNames.add("��Ʒ���");
		columnNames.add("��Ʒ����");
		columnNames.add("�ۼ�");
		columnNames.add("Ӧ����");
		columnNames.add("��������");
		columnNames.add("�ܽ��");
	
		
	
		
		String a="��������Ʒ666";
		Vector data=Goods.selectname(a);//����һ����������Ʒ
		
		this.setTitle("����");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable(model);
		JScrollPane jsp=new JScrollPane(table);
		jsp.setBounds(10, 36, 629, 416);
		contentPane.add(jsp);
		
		//�������޸��¼�
		model.addTableModelListener(new TableModelListener() {
			float[] num=new float[50];
			float[]price=new float[50];
			float[]amount=new float[50];
			int aarow = 0,bbrow=0;
			@Override
			public void tableChanged(TableModelEvent e) {
				String upname = null;
				int uprow=e.getFirstRow();
				int upcol=e.getColumn();//����޸ı��λ�õ�������
				//System.out.println(uprow+","+upcol);
				//ͨ��������Ʒ���Զ����������Ϣ
				if(upcol==3)
				{ upname=table.getValueAt(uprow, upcol).toString();//���������Ʒ����
				try {
					Vector datatemp=Goods.selectname(upname);
					table.setValueAt(((Vector)datatemp.elementAt(0)).get(0), uprow, 2);//�Զ���ʾ��ѯ��һ����Ʒ���
					table.setValueAt(((Vector)datatemp.elementAt(0)).get(3), uprow, 4);//�Զ���ʾ��Ʒ�ۼ�
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				}
				//�������ͨ��������Ʒ��Ż��������Ϣ
				//�Զ���ʾ���
			
				 if(upcol==5&&table.getValueAt(uprow, 5)!=null)
				  {
				  price[uprow]=Float.parseFloat((String) table.getValueAt(uprow, 5) );
				  aarow=uprow;
				 
				  }
				 if(upcol==6&&table.getValueAt(uprow, 6)!=null)
				  {
				  num[uprow]=Float.parseFloat((String) table.getValueAt(uprow, 6) );
				
			      bbrow=uprow;
				  }
				if(uprow>=0&&price[uprow]!=0&&num[uprow]!=0&&table.getValueAt(uprow, 7)==null)//���Ϊ��ʱ����������
				{ amount[uprow]=price[uprow]*num[uprow];
				//System.out.println(price[uprow]+","+num[uprow]+","+amount[uprow]);
				DecimalFormat df = new DecimalFormat("#########.#");
				String s = df.format(amount[uprow]);
				//System.out.println(s);
				table.setValueAt(s, uprow,7);
				
				}
			
				 /*if(amount[uprow]!=0)
				 {
					 table.setValueAt(amount[uprow], uprow, 7);
				 }*/
				 
				/* if(aarow==bbrow)
				 {  amount[1]=price[1]*num[1];
				  if(amount[1]!=0)
				    {System.out.println(amount);
				    System.out.println(aarow);
				    //table.setValueAt(amount, aarow,7);
				    }
				
			    }*/
		 }
			 
		});
		
	
		
		/*table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			if(e.getButton()==1)
				table.rowAtPoint(e.getPoint());
		        table.columnAtPoint(e.getPoint());
		System.out.println(table.rowAtPoint(e.getPoint())+","+table.columnAtPoint(e.getPoint()));
		Object aaa=table.getValueAt(table.rowAtPoint(e.getPoint()), table.columnAtPoint(e.getPoint()));
		System.out.println(aaa);
			}
		});*/
		
	
		JButton btnNewButton = new JButton("���");//��ӿ���
		btnNewButton.setBounds(124, 479, 93, 23);
		contentPane.add(btnNewButton);
		model.setDataVector(data,columnNames);
		btnNewButton.addActionListener(new ActionListener() {
			int i=-1;//i��
			int lno=(int)(Math.random()*1000000);//����һ���������Ϊ��ˮ��
			// Calendar cal=Calendar.getInstance();   //��ȡϵͳ��ǰʱ��
			java.util.Date d=new java.util.Date();    //��ȡ��ǰϵͳ��ʱ��
			 java.text.SimpleDateFormat s= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//��ʽ������
			String dateStr = s.format(d); //תΪ�ַ���

			public void actionPerformed(ActionEvent e) {
			  i++;
				model.addRow(new Vector());
				model.setValueAt(lno, i, 0);
				model.setValueAt(dateStr, i, 1);
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("ȷ��");
		btnNewButton_1.setBounds(477, 479, 93, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e) {
				try {
					int row=model.getRowCount();
					int column=model.getColumnCount();
					Object [][]value=new Object[row][column];
					//table.getCellEditor().stopCellEditing();//ǿ�ƽ����༭״̬
					//System.out.println(row+","+column);
					for(int i=0;i<row;i++)
						{for(int j=0;j<column;j++)
							value[i][j]=table.getValueAt(i,j);}//�����������װ��value��
					        sales(value,row);
		            model.setRowCount(0);//�������ձ������ظ���ȡ�Ͳ���
			
				}
				catch (Exception e1) {e1.printStackTrace();}
				
			}
		});
		
		JButton btnNewButton_2 = new JButton("���ɸñ��˵�");
		btnNewButton_2.setBounds(290, 479, 127, 23);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=model.getRowCount();
				int column=model.getColumnCount();
				Object [][]bill=new Object[row][column];
				//System.out.println(row+","+column);
				for(int i=0;i<row;i++)
				{for(int j=0;j<column;j++)
					bill[i][j]=table.getValueAt(i,j);}//�����������װ��bill��
					 check abc= new check(bill,row,column);
					abc.setVisible(true);
			}
		});//���ɸ��˵�
		
		}	
	
	
	
	
	
	public static void sales (Object[][]aa,int i) throws Exception{
		Object[][] addr=aa;
	    int row=i;
		ConnectionFactory factory=new ConnectionFactory();
		String dburl="jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mistystore";//�õ���������
		Connection conn=factory.getConnection(dburl);//�õ����ݿ�
		Statement stmt=conn.createStatement();
		String sqlstr;
		try {
		  for(int a=0;a<row;a++)
		  {
			sqlstr="insert into Laundry values('"+addr[a][0]+"','"+addr[a][1]+"','"+addr[a][2]+"','"+addr[a][3]+"','"+addr[a][4]+"','"+addr[a][5]+"','"+addr[a][6]+"','"+addr[a][7]+"','"+""+"')";
			
			//��������
			stmt.executeUpdate(sqlstr);//���÷���������executeUpdate��ִ�����
			  }
		 JOptionPane.showMessageDialog(null,"���۳ɹ�");//��ʾ���ɹ�
		 
		}
		catch(Exception ee) {
			ee.printStackTrace();
			JOptionPane.showMessageDialog(null,"���۱�Ϊ��");}//�����������ݻ�δ����������Ʒ��ʱ��ʾ
		
	}
	
	
		
	}

