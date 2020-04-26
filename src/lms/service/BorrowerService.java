/**
 * 
 */
package lms.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lms.dao.BookCopiesDAO;
import lms.dao.BookDAO;
import lms.utils.DbConnection;

/**
 * @author ducba
 *
 */
public class BorrowerService {
	DbConnection dbConnection = new DbConnection();

	/**
	 * 
	 */
	public BorrowerService() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getAvailableBooksByBranch(Integer branchId) {
		List<String> books = new ArrayList<String>();
		try (Connection sqlConnection = dbConnection.getConnection()) {
			BookDAO bookDAO = new BookDAO(sqlConnection);
			ResultSet result = bookDAO.execute("SELECT b.bookId, title, authorName FROM library.tbl_book_copies bc\r\n"
					+ "join tbl_book b on bc.bookId = b.bookId\r\n"
					+ "left join tbl_book_authors ba on ba.bookId = b.bookId\r\n"
					+ "left join tbl_author a on ba.authorId = a.authorId\r\n" + "where branchId=? and noOfCopies > 0;",
					new Object[] { branchId });
			HashMap<Integer, String> bookMap = new HashMap<>();
			while (result.next()) {
				Integer key = result.getInt("bookId");
				if (bookMap.get(key) == null) {
					bookMap.put(key, String.format("%d - %s by %s", key, result.getString("title"),
							result.getString("authorName")));
				} else {
					String value = bookMap.get(key);
					bookMap.put(key, value + ", " + result.getString("authorName"));
				}
			}

			bookMap.forEach((key, value) -> {
				books.add(value);
			});

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

	public void checkOutBook(Integer bookId, Integer branchId, Integer noOfCopies) throws SQLException  {
		Connection sqlConnection = null;
		try {
			sqlConnection = dbConnection.getConnection();
			BookCopiesDAO bookCopiesDAO = new BookCopiesDAO(sqlConnection);
			bookCopiesDAO.put(bookId, branchId, noOfCopies - 1);
			sqlConnection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlConnection.rollback();
		} finally {
			sqlConnection.close();
		}

	}

}
