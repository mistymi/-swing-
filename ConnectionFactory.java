package welcome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
		public Connection getConnection (String dburl){
			Connection con=null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			}
			catch(ClassNotFoundException cnfe) {
				System.out.println("º”‘ÿ«˝∂Ø≥Ã–Ú ß∞‹£∫"+cnfe);
			}
			try {
				String url=dburl;
				con=DriverManager.getConnection(url);
			}
			catch(SQLException ce) {
				System.out.println(ce);
			}
			return con;
		}


		
}
