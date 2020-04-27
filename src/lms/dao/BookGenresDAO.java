/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lms.entity.BookGenres;

/**
 * @author ducba
 *
 */
public class BookGenresDAO extends BaseDAO<BookGenres> {

	/**
	 * 
	 */
	public BookGenresDAO(Connection sqlConnection) {
		super(sqlConnection);
	}

	@Override
	public List<BookGenres> get() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void post(BookGenres object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put(Integer id, BookGenres object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BookGenres> extractData(ResultSet results) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
