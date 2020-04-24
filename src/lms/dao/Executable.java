/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ducba
 * @param <T>
 *
 */
public interface Executable<T> {
	public default ResultSet read(Connection sqlConnection, String sqlQuery) throws SQLException {
		PreparedStatement sqlStatement = sqlConnection.prepareStatement(sqlQuery);
		ResultSet results = sqlStatement.executeQuery();
		return results;
	}
	
	public default ResultSet write(Connection sqlConnection, String sqlQuery) throws SQLException {
		PreparedStatement sqlStatement = sqlConnection.prepareStatement(sqlQuery);
		ResultSet results = sqlStatement.executeQuery();
		return results;
	}
}
