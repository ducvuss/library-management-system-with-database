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
	default ResultSet read(Connection sqlConnection, String sqlQuery) throws SQLException {
		PreparedStatement sqlStatement = sqlConnection.prepareStatement(sqlQuery);
		ResultSet results = sqlStatement.executeQuery();
		return results;
	}
	
	default ResultSet read(Connection sqlConnection, String sqlQuery, Object[] objects) throws SQLException {
		PreparedStatement sqlStatement = sqlConnection.prepareStatement(sqlQuery);
		int index = 1;
		for (Object object : objects) {
			sqlStatement.setObject(index, object);
			index++;
		}
		ResultSet results = sqlStatement.executeQuery();
		return results;
	}
	
	default void save(Connection sqlConnection, String sqlQuery, Object[] objects) throws SQLException {
		PreparedStatement sqlStatement = sqlConnection.prepareStatement(sqlQuery);
		int index = 1;
		for (Object object : objects) {
			sqlStatement.setObject(index, object);
			index++;
		}
		sqlStatement.executeUpdate();
	}
}
