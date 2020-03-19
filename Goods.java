package welcome;

/*含商品入库出库查找方法*/
/*1，改成模糊查询；2，加入快捷键*/

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import java.sql.PreparedStatement;

public class Goods {
	

	/*通过商品名称查询，并将结果保存在数组中*/
	public static Vector selectname(String name) throws Exception
	{
		 Vector rowdata;
		  rowdata=new Vector();

		 ConnectionFactory factory=new ConnectionFactory();
		String dburl="jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mistystore";//得到数据连接
		Connection conn=factory.getConnection(dburl);//得到数据块
		Statement stmt=conn.createStatement();
		String sqlstr="select * from Goods where Gname like'%"+name+"%' ";//输入模糊名称进行查询
		ResultSet rs=stmt.executeQuery(sqlstr);//执行SQL语句
		int j=0;
		ResultSetMetaData rsmd = rs.getMetaData();
		while(rs.next()){
			rowdata.addElement(getNextRow(rs,rsmd));	  
         }	

		JDBCUtils.close(rs,stmt,conn);
		return rowdata;
		
	}
	
	
	private static Object getNextRow (ResultSet rs, ResultSetMetaData rsmd)throws SQLException {
		Vector currentRow = new Vector();
		for(int i = 1; i <= rsmd.getColumnCount(); i++){
			currentRow.addElement(rs.getString(i));
		}
		return currentRow;
	}

	
	/*将Value数据内容加入数据库*/
	public  static void insertgoods(Object[][]aa,int i) throws Exception
	{   Object[][] addr=aa;
	    int row=i;
		ConnectionFactory factory=new ConnectionFactory();
		String dburl="jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mistystore";//得到数据连接
		Connection conn=factory.getConnection(dburl);//得到数据块
		Statement stmt=conn.createStatement();
		String sqlstr;
		try {
		  for(int a=0;a<row;a++)
		  {
			if(addr[a][5]!=null&&addr[a][4]!=null)//解决varchar类型与float类型的转化问题
			{sqlstr="insert into Goods values('"+addr[a][0]+"','"+addr[a][1]+"','"+addr[a][2]+"','"+addr[a][3]+"','"+addr[a][4]+"','"+addr[a][5]+"','"+addr[a][6]+"','"+addr[a][7]+"','"+addr[a][8]+"')";
			}
			else
				{ sqlstr="insert into Goods values('"+addr[a][0]+"','"+addr[a][1]+"','"+addr[a][2]+"','"+addr[a][3]+"','','','"+addr[a][6]+"','"+addr[a][7]+"','"+addr[a][8]+"')";
			}//插入数据
			stmt.executeUpdate(sqlstr);//调用返回整数的executeUpdate来执行语句
			  }
		 JOptionPane.showMessageDialog(null,"入库成功");//提示入库成功
		 
		}
		catch(Exception ee) {
			ee.printStackTrace();
			JOptionPane.showMessageDialog(null,"请输入商品号");}//当表中无数据或未输入主键商品号时提示
	}
	
	/* 从数据库中删除某行数据 */
	public static  void deletegoods(Object aa) throws Exception {
		Object del=aa;
		ConnectionFactory factory=new ConnectionFactory();
		String dburl="jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mistystore";//得到数据连接
		Connection conn=factory.getConnection(dburl);//得到数据块
		Statement stmt=conn.createStatement();
		String sqlstr="delete from Goods where Gno='"+del+"'";//依据传入的商品号进行删除
		stmt.executeUpdate(sqlstr);//执行SQL语句
	
	}
	/*从数据库修改数据*/
	public static void updategoods(String[]t)   {
		String[]gx=t;
		ConnectionFactory factory=new ConnectionFactory();
		String dburl="jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mistystore";//得到数据连接
		Connection conn=factory.getConnection(dburl);//得到数据块
		Statement stmt;
			try {
				stmt = conn.createStatement();
				String sqlstr="update Goods"
						+ " set Gname='"+gx[1]+"',Pur_price='"+gx[2]+"',Sel_price='"+gx[3]+"',Tot_sales='"+gx[4]+"',Iventory='"+gx[5]+"',Counter='"+gx[6]+"',Gps='"+gx[7]+"',Type='"+gx[8]+"'"
								+ " where Gno='"+gx[0]+"'";//依据传入的商品号进行更新
				stmt.executeUpdate(sqlstr);//执行SQL语句
				JOptionPane.showMessageDialog(null,"修改成功");
				
			} 
			catch (SQLException e) {	
				JOptionPane.showMessageDialog(null,"修改失败");
				e.printStackTrace();}
			
		
	
	
		
	}
	
	
}
