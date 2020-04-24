/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ducba
 * @param <T>
 *
 */
public interface BaseDAO<T> {
	public default ResultSet read(Connection sqlConnection, String sqlQuery) throws SQLException {
		PreparedStatement sqlStatement = sqlConnection.prepareStatement(sqlQuery);
		ResultSet results = sqlStatement.executeQuery();
		return results;
	}
}
