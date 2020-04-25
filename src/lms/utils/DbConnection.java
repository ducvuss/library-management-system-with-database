/**
 * 
 */
package lms.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ducba
 *
 */
public class DbConnection {

	private Properties props;

	/**
	 * 
	 */
	public DbConnection() {
		this.props = new Properties();
		try (InputStream inStream = new FileInputStream("resources/config/lms.properties")) {
			props.load(inStream);
			System.out.println("loaded property for user: " + props.get("user"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
