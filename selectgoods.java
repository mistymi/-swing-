package welcome;

/*修改删除默认选项*/

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
	JTextField txtname=new JTextField();//名称文本框
	private final JButton ok = new JButton("确定");//确定按钮
	DefaultTableModel model = new DefaultTableModel();
	JTable txtaddr=new JTable(model);
    JScrollPane jsp=new JScrollPane(txtaddr);
	Vector columnNames = new Vector();
	private void initialize() throws Exception {
		columnNames.add("商品号");
		columnNames.add("商品名称");
		columnNames.add("单价");
		columnNames.add("售价");
		columnNames.add("销量");
		columnNames.add("库存");
		columnNames.add("摆放货柜");
		columnNames.add("备注");
		columnNames.add("商品类型");
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 20, 900,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("商品信息查询");
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("请输入商品信息：");
		lblNewLabel.setBounds(10, 24, 114, 31);
		frame.getContentPane().add(lblNewLabel);
	
		JLabel lblNewLabel_1 = new JLabel("查询结果：");
		lblNewLabel_1.setBounds(10, 140, 96, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtname.setBounds(104,63,338,25);
		frame.getContentPane().add(txtname);	
			
		JLabel lblNewLabel_2 = new JLabel("商品名称：");
		lblNewLabel_2.setBounds(10, 65, 86, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		ok.setBounds(482, 60, 96, 31);//查询商品
		frame.getContentPane().add(ok);
		ok.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e) {
				ok_actionPerformed() ;
			}
		
	  });//监听,调用
		
		jsp.setBounds(65,180,800,400);
		frame.getContentPane().add(jsp);
		
		JButton add = new JButton("添加");//增加一行
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
		
		JButton delete = new JButton("删除");//删除一行
		delete.setBounds(642, 141, 93, 23);
		frame.getContentPane().add(delete);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int confi=	JOptionPane.showConfirmDialog(null, "确定删除该商品的所有信息吗？");//默认选项怎么修改？
			//System.out.println(confi);
			if (confi==0)
				{try {
					deletevent();
				} catch (Exception e1) {
					e1.printStackTrace();
				}}
			}
		});
		
		JButton save = new JButton("修改");
		save.setBounds(745, 141, 93, 23);
		frame.getContentPane().add(save);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object[] Gnos=updateevent();
				updategoods  bb;
				 try {
					bb=new updategoods( Gnos);//将商品号传递到修改界面
					 bb.setVisible(true);
				}
				 catch (Exception e1) {e1.printStackTrace();}
				
					
			}
		});
		
		
		
		
		
		
	}
	
	//当用户单击【确定】按钮时，执行如下操作
			public  Vector ok_actionPerformed() {
				String name=txtname.getText();//得到要查询的商品名称
				Goods goods=new Goods();
				boolean flag=false;
				Vector rowdata;
				//int i=0,j=0;
				Vector datatemp=null;
				//String[] datatemp=null;
				try {
					//调用业务方法，返回数据
					
					datatemp=Goods.selectname(name);
					flag=true;
				
				}
				catch(Exception ex) {ex.printStackTrace();}
				if (flag)
				{
			 
		            model.setDataVector(datatemp, columnNames);
		         
				}//将返回的数据填充文本域*/
			    return datatemp;
			}
			
			public void  deletevent() throws Exception{
				int rowcount = txtaddr.getSelectedRow();
			Object as=txtaddr.getValueAt(rowcount, 0);//获得删除行的商品号
				if(rowcount >= 0){
					Goods.deletegoods(as);
					model.removeRow(rowcount);
					
				}
				JOptionPane.showMessageDialog(null, "删除成功！");//删除后提示
				

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
			
				return zc;//获得所选行的商品号并返回
				
			
}
}
	