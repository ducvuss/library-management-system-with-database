/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import lms.entity.BookLoan;

/**
 * @author ducba
 *
 */
public class BookLoansDAO extends BaseDAO<BookLoan> {
	/**
	 * 
	 */
	public BookLoansDAO(Connection sqlConnection) {
		super(sqlConnection);
		this.setTableName("tbl_book_loans");
	}

	@Override
	public List<BookLoan> get() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void post(BookLoan object) throws SQLException {
		// TODO Auto-generated method stub
		LocalDateTime now = LocalDateTime.now();
		save("INSERT INTO tbl_book_loans VALUES (?,?,?,?,?,?)", new Object[] { object.getBookId(), object.getBranchId(), object.getCardNo(),
				now, now.plusDays(7), null });

	}

	@Override
	public void put(Integer id, BookLoan object) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BookLoan> extractData(ResultSet results) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
