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
		columnNames.add("流水号");
		columnNames.add("销售时间");
		columnNames.add("商品序号");
		columnNames.add("商品名称");
		columnNames.add("售价");
		columnNames.add("应付价");
		columnNames.add("销售数量");
		columnNames.add("总金额");
	
		
	
		
		String a="不存在商品666";
		Vector data=Goods.selectname(a);//传入一个不存在商品
		
		this.setTitle("销售");
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
		
		//监听表修改事件
		model.addTableModelListener(new TableModelListener() {
			float[] num=new float[50];
			float[]price=new float[50];
			float[]amount=new float[50];
			int aarow = 0,bbrow=0;
			@Override
			public void tableChanged(TableModelEvent e) {
				String upname = null;
				int uprow=e.getFirstRow();
				int upcol=e.getColumn();//获得修改表格位置的行列数
				//System.out.println(uprow+","+upcol);
				//通过输入商品名自动获得销售信息
				if(upcol==3)
				{ upname=table.getValueAt(uprow, upcol).toString();//获得输入商品名称
				try {
					Vector datatemp=Goods.selectname(upname);
					table.setValueAt(((Vector)datatemp.elementAt(0)).get(0), uprow, 2);//自动显示查询第一个商品序号
					table.setValueAt(((Vector)datatemp.elementAt(0)).get(3), uprow, 4);//自动显示商品售价
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				}
				//待解决，通过输入商品序号获得销售信息
				//自动显示金额
			
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
				if(uprow>=0&&price[uprow]!=0&&num[uprow]!=0&&table.getValueAt(uprow, 7)==null)//金额为空时触发计算金额
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
		
	
		JButton btnNewButton = new JButton("添加");//添加空行
		btnNewButton.setBounds(124, 479, 93, 23);
		contentPane.add(btnNewButton);
		model.setDataVector(data,columnNames);
		btnNewButton.addActionListener(new ActionListener() {
			int i=-1;//i行
			int lno=(int)(Math.random()*1000000);//产生一个随机整数为流水号
			// Calendar cal=Calendar.getInstance();   //获取系统当前时间
			java.util.Date d=new java.util.Date();    //获取当前系统的时间
			 java.text.SimpleDateFormat s= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化日期
			String dateStr = s.format(d); //转为字符串

			public void actionPerformed(ActionEvent e) {
			  i++;
				model.addRow(new Vector());
				model.setValueAt(lno, i, 0);
				model.setValueAt(dateStr, i, 1);
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("确定");
		btnNewButton_1.setBounds(477, 479, 93, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e) {
				try {
					int row=model.getRowCount();
					int column=model.getColumnCount();
					Object [][]value=new Object[row][column];
					//table.getCellEditor().stopCellEditing();//强制结束编辑状态
					//System.out.println(row+","+column);
					for(int i=0;i<row;i++)
						{for(int j=0;j<column;j++)
							value[i][j]=table.getValueAt(i,j);}//将表格中数据装入value中
					        sales(value,row);
		            model.setRowCount(0);//插入后清空表，避免重复读取和插入
			
				}
				catch (Exception e1) {e1.printStackTrace();}
				
			}
		});
		
		JButton btnNewButton_2 = new JButton("生成该笔账单");
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
					bill[i][j]=table.getValueAt(i,j);}//将表格中数据装入bill中
					 check abc= new check(bill,row,column);
					abc.setVisible(true);
			}
		});//生成该账单
		
		}	
	
	
	
	
	
	public static void sales (Object[][]aa,int i) throws Exception{
		Object[][] addr=aa;
	    int row=i;
		ConnectionFactory factory=new ConnectionFactory();
		String dburl="jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mistystore";//得到数据连接
		Connection conn=factory.getConnection(dburl);//得到数据块
		Statement stmt=conn.createStatement();
		String sqlstr;
		try {
		  for(int a=0;a<row;a++)
		  {
			sqlstr="insert into Laundry values('"+addr[a][0]+"','"+addr[a][1]+"','"+addr[a][2]+"','"+addr[a][3]+"','"+addr[a][4]+"','"+addr[a][5]+"','"+addr[a][6]+"','"+addr[a][7]+"','"+""+"')";
			
			//插入数据
			stmt.executeUpdate(sqlstr);//调用返回整数的executeUpdate来执行语句
			  }
		 JOptionPane.showMessageDialog(null,"销售成功");//提示入库成功
		 
		}
		catch(Exception ee) {
			ee.printStackTrace();
			JOptionPane.showMessageDialog(null,"销售表为空");}//当表中无数据或未输入主键商品号时提示
		
	}
	
	
		
	}

