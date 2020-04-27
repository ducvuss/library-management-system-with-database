/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lms.entity.BookGenre;

/**
 * @author ducba
 *
 */
public class BookGenresDAO extends BaseDAO<BookGenre> {

	/**
	 * 
	 */
	public BookGenresDAO(Connection sqlConnection) {
		super(sqlConnection);
	}

	@Override
	public List<BookGenre> get() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void post(BookGenre object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put(Integer id, BookGenre object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BookGenre> extractData(ResultSet results) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
