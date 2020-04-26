/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ducba
 * @param <T>
 *
 */
public abstract class BaseDAO<T> implements Executable<T> {

	public static final String url = "jdbc:mysql://localhost:3306/library?useSSL=false";
	public static final String user = "root";
	public static final String password = "test";
	private Connection sqlConnection;

	/**
	 * @throws SQLException
	 * 
	 */
	public BaseDAO() throws SQLException {
		this.sqlConnection = DriverManager.getConnection(url, user, password);
	}
	
	public BaseDAO(Connection sqlConnection) {
		this.sqlConnection = sqlConnection;
	}

	protected ResultSet read(String sqlQuery) throws SQLException {
		return read(sqlConnection, sqlQuery);
	}
	
	protected void save(String sqlQuery, Object[] objects) throws SQLException {
		save(sqlConnection, sqlQuery, objects);
	}

	protected ResultSet read(String sqlQuery, Object[] objects) throws SQLException {
//		System.out.println(sqlQuery);
		return read(sqlConnection, sqlQuery, objects);
	}

	public abstract List<T> get() throws SQLException;

	public abstract void post(T object) throws SQLException;

	public abstract void put(Integer id, T object) throws SQLException;

	public abstract void delete(Integer id) throws SQLException;

	public abstract List<T> extractData(ResultSet results) throws SQLException;

}
