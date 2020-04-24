/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lms.utils.Table;

/**
 * @author ducba
 * @param <T>
 *
 */
public abstract class BaseDAO<T> implements Queryable<T> {

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

	public ResultSet read(String sqlQuery) throws SQLException {
		return read(sqlConnection, new Table("tbl_author").select());
	}

	public abstract List<T> get() throws SQLException;

	public abstract void post(Object object);

	public abstract void put(Integer id, Object object);

	public abstract void delete(Integer id);

	public abstract List<T> extractData(ResultSet results) throws SQLException;

}
