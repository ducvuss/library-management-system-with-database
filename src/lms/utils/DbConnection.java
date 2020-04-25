/**
 * 
 */
package lms.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ducba
 *
 */
public class DbConnection {

	private Properties props;
	private Connection conn;

	/**
	 * 
	 */
	public DbConnection() {
		this.props = new Properties();
		try (InputStream inStream = new FileInputStream("resources/config/lms.properties")) {
			props.load(inStream);
//			System.out.println("loaded property for user: " + props.getProperty("user"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		String url = props.getProperty("url");
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	

}
