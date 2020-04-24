/**
 * 
 */
package lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lms.entity.Book;

/**
 * @author ducba
 *
 */
public class BookDAO extends BaseDAO<Book>{

	private String tableName;

	/**
	 * @throws SQLException 
	 * 
	 */
	public BookDAO() throws SQLException {
		super();
		this.tableName = "tbl_author";
	}

	@Override
	public List get() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void post(Book book) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List extractData(ResultSet results) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(Integer id, Book object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
