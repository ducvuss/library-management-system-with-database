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
import lms.dao.LibraryBranchDAO;
import lms.entity.LibraryBranch;
import lms.utils.DbConnection;

/**
 * @author ducba
 *
 */
public abstract class BaseService {
	DbConnection dbConnection = new DbConnection();

	/**
	 * 
	 */
	public BaseService() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getBranches() {
		List<String> branches = new ArrayList<String>();
		try (Connection sqlConnection = dbConnection.getConnection()) {
			LibraryBranchDAO branchDAO = new LibraryBranchDAO(sqlConnection);

			branchDAO.get().forEach(branch -> {
				branches.add(branch.toRowString());
			});

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return branches;
	}

	public LibraryBranch getBranchById(Integer branchId) {
		LibraryBranch branch = null;
		try (Connection sqlConnection = dbConnection.getConnection()) {
			LibraryBranchDAO branchDAO = new LibraryBranchDAO(sqlConnection);
			branch = branchDAO.get(branchId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return branch;
	}

	public Integer getBookCopiesByBranch(Integer bookId, Integer branchId) {
		try (Connection sqlConnection = dbConnection.getConnection()) {
			BookCopiesDAO dao = new BookCopiesDAO(sqlConnection);
			return dao.getCopies(bookId, branchId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getBooksByBranchId(Integer branchId) {
		List<String> books = new ArrayList<String>();
		try (Connection sqlConnection = dbConnection.getConnection()) {
			BookDAO bookDAO = new BookDAO(sqlConnection);
			ResultSet result = bookDAO.execute(
					"SELECT b.bookId, title, authorName FROM library.tbl_book_copies bc\r\n"
							+ "join tbl_book b on bc.bookId = b.bookId\r\n"
							+ "left join tbl_book_authors ba on ba.bookId = b.bookId\r\n"
							+ "left join tbl_author a on ba.authorId = a.authorId\r\n" + "where branchId=?;",
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
}
