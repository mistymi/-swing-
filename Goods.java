package welcome;

/*����Ʒ��������ҷ���*/
/*1���ĳ�ģ����ѯ��2�������ݼ�*/

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import java.sql.PreparedStatement;

public class Goods {
	

	/*ͨ����Ʒ���Ʋ�ѯ���������������������*/
	public static Vector selectname(String name) throws Exception
	{
		 Vector rowdata;
		  rowdata=new Vector();

		 ConnectionFactory factory=new ConnectionFactory();
		String dburl="jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mistystore";//�õ���������
		Connection conn=factory.getConnection(dburl);//�õ����ݿ�
		Statement stmt=conn.createStatement();
		String sqlstr="select * from Goods where Gname like'%"+name+"%' ";//����ģ�����ƽ��в�ѯ
		ResultSet rs=stmt.executeQuery(sqlstr);//ִ��SQL���
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

	
	/*��Value�������ݼ������ݿ�*/
	public  static void insertgoods(Object[][]aa,int i) throws Exception
	{   Object[][] addr=aa;
	    int row=i;
		ConnectionFactory factory=new ConnectionFactory();
		String dburl="jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mistystore";//�õ���������
		Connection conn=factory.getConnection(dburl);//�õ����ݿ�
		Statement stmt=conn.createStatement();
		String sqlstr;
		try {
		  for(int a=0;a<row;a++)
		  {
			if(addr[a][5]!=null&&addr[a][4]!=null)//���varchar������float���͵�ת������
			{sqlstr="insert into Goods values('"+addr[a][0]+"','"+addr[a][1]+"','"+addr[a][2]+"','"+addr[a][3]+"','"+addr[a][4]+"','"+addr[a][5]+"','"+addr[a][6]+"','"+addr[a][7]+"','"+addr[a][8]+"')";
			}
			else
				{ sqlstr="insert into Goods values('"+addr[a][0]+"','"+addr[a][1]+"','"+addr[a][2]+"','"+addr[a][3]+"','','','"+addr[a][6]+"','"+addr[a][7]+"','"+addr[a][8]+"')";
			}//��������
			stmt.executeUpdate(sqlstr);//���÷���������executeUpdate��ִ�����
			  }
		 JOptionPane.showMessageDialog(null,"���ɹ�");//��ʾ���ɹ�
		 
		}
		catch(Exception ee) {
			ee.printStackTrace();
			JOptionPane.showMessageDialog(null,"��������Ʒ��");}//�����������ݻ�δ����������Ʒ��ʱ��ʾ
	}
	
	/* �����ݿ���ɾ��ĳ������ */
	public static  void deletegoods(Object aa) throws Exception {
		Object del=aa;
		ConnectionFactory factory=new ConnectionFactory();
		String dburl="jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mistystore";//�õ���������
		Connection conn=factory.getConnection(dburl);//�õ����ݿ�
		Statement stmt=conn.createStatement();
		String sqlstr="delete from Goods where Gno='"+del+"'";//���ݴ������Ʒ�Ž���ɾ��
		stmt.executeUpdate(sqlstr);//ִ��SQL���
	
	}
	/*�����ݿ��޸�����*/
	public static void updategoods(String[]t)   {
		String[]gx=t;
		ConnectionFactory factory=new ConnectionFactory();
		String dburl="jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mistystore";//�õ���������
		Connection conn=factory.getConnection(dburl);//�õ����ݿ�
		Statement stmt;
			try {
				stmt = conn.createStatement();
				String sqlstr="update Goods"
						+ " set Gname='"+gx[1]+"',Pur_price='"+gx[2]+"',Sel_price='"+gx[3]+"',Tot_sales='"+gx[4]+"',Iventory='"+gx[5]+"',Counter='"+gx[6]+"',Gps='"+gx[7]+"',Type='"+gx[8]+"'"
								+ " where Gno='"+gx[0]+"'";//���ݴ������Ʒ�Ž��и���
				stmt.executeUpdate(sqlstr);//ִ��SQL���
				JOptionPane.showMessageDialog(null,"�޸ĳɹ�");
				
			} 
			catch (SQLException e) {	
				JOptionPane.showMessageDialog(null,"�޸�ʧ��");
				e.printStackTrace();}
			
		
	
	
		
	}
	
	
}
