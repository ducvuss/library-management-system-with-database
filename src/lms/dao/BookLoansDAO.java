/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import lms.entity.BookLoan;
import lms.utils.Table;

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

	public BookLoan get(Integer bookId, Integer branchId, Integer cardNo) throws SQLException {
		List<BookLoan> bookLoans = extractData(
				read(new Table(tableName).select() + " WHERE bookId=? and branchId=? and cardNo=? and dateIn is null;",
						new Object[] { bookId, branchId, cardNo }));

		if (bookLoans.size() > 0) {
			return bookLoans.get(0);
		}
		return null;
	}

	@Override
	public void post(BookLoan object) throws SQLException {
		LocalDateTime now = LocalDateTime.now();
		save("INSERT INTO tbl_book_loans VALUES (?,?,?,?,?,?)", new Object[] { object.getBookId(), object.getBranchId(),
				object.getCardNo(), now, now.plusDays(7), null });

	}

	@Override
	public void put(Integer id, BookLoan object) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void update(BookLoan loan) throws SQLException {
		save("UPDATE tbl_book_loans set dueDate=?, dateIn=? WHERE bookId=? and branchId=? and cardNo=? and dateOut=?",
				new Object[] { loan.getDateIn(), loan.getDueDate(), loan.getBookId(), loan.getBranchId(),
						loan.getCardNo(), loan.getDateOut() });
	}

	@Override
	public void delete(Integer id) throws SQLException {
	}

	public void remove(BookLoan loan) throws SQLException {
		save("DELETE FROM tbl_book_loans WHERE bookId=? and branchId=? and cardNo=? and dateOut=?",
				new Object[] { loan.getBookId(), loan.getBranchId(), loan.getCardNo(), loan.getDateOut() });
	}

	@Override
	public List<BookLoan> extractData(ResultSet results) throws SQLException {

		List<BookLoan> bookLoans = new ArrayList<>();

		while (results.next()) {

			BookLoan bookLoan = new BookLoan();
			bookLoan.setBranchId(results.getInt("branchId"));
			bookLoan.setBookId(results.getInt("bookId"));
			bookLoan.setCardNo(results.getInt("cardNo"));
			if (results.getTimestamp("dateIn") != null) {
				bookLoan.setDateIn(
						LocalDateTime.ofInstant(results.getTimestamp("dateIn").toInstant(), ZoneId.systemDefault()));
			}
			if (results.getTimestamp("dateOut") != null) {
				bookLoan.setDateOut(
						LocalDateTime.ofInstant(results.getTimestamp("dateOut").toInstant(), ZoneId.systemDefault()));
			}
			if (results.getTimestamp("dueDate") != null) {
				bookLoan.setDueDate(
						LocalDateTime.ofInstant(results.getTimestamp("dueDate").toInstant(), ZoneId.systemDefault()));
			}

			bookLoans.add(bookLoan);
		}

		return bookLoans;
	}

	public void put(BookLoan loan) throws SQLException {
		LocalDateTime now = LocalDateTime.now();
		save("UPDATE tbl_book_loans SET dateIn=? WHERE bookId=? and branchId=? and cardNo=? and dateIn is null",
				new Object[] { now, loan.getBookId(), loan.getBranchId(), loan.getCardNo() });
	}

}
