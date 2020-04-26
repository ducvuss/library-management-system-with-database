/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.entity.Book;
import lms.entity.BookCopies;
import lms.utils.Table;

/**
 * @author ducba
 *
 */
public class BookCopiesDAO extends BaseDAO<BookCopies> {

	private String tableName;

	/**
	 * 
	 */
	public BookCopiesDAO(Connection sqlConnection) {
		super(sqlConnection);
		this.tableName = "tbl_book_copies";
	}

	@Override
	public List<BookCopies> get() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Integer getCopies(Integer bookId, Integer branchId) throws SQLException {
		ResultSet result = read(new Table(tableName).select() + " WHERE bookId=(?) and branchId=(?)", new Object[] { bookId, branchId });
		List<BookCopies> bookCopiesList = extractData(result);
		if (bookCopiesList.size() == 0) {
			return null;
		}
		return bookCopiesList.get(0).getNoOfCopies();
	}

	@Override
	public void post(BookCopies object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put(Integer id, BookCopies object) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public void put(Integer bookId, Integer branchId, Integer numberOfCopies) throws SQLException {
		save("UPDATE tbl_book_copies SET noOfCopies=? WHERE bookId=? and branchId=?", new Object[] { numberOfCopies, bookId, branchId });
	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BookCopies> extractData(ResultSet results) throws SQLException {

		List<BookCopies> bookCopiesList = new ArrayList<>();
		
		while (results.next()) {
			BookCopies bookCopies = new BookCopies();
			bookCopies.setBranchId(results.getInt("branchId"));
			bookCopies.setBookId(results.getInt("bookId"));
			bookCopies.setNoOfCopies(results.getInt("noOfCopies"));
			bookCopiesList.add(bookCopies);
		}
		return bookCopiesList;
	}

	public List<BookCopies> getBooksByBranch(Integer branchId) throws SQLException {
		return extractData(read(new Table(tableName).select() + " WHERE branchId=(?)", new Object[] { branchId }));	
	}

}
