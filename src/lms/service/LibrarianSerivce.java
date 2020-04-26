/**
 * 
 */
package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.dao.AuthorDAO;
import lms.dao.BookDAO;
import lms.dao.LibraryBranchDAO;
import lms.entity.Author;
import lms.entity.Book;
import lms.entity.LibraryBranch;
import lms.utils.DbConnection;

/**
 * @author ducba
 *
 */
public class LibrarianSerivce {
	DbConnection dbConnection = new DbConnection();
	/**
	 * 
	 */
	public LibrarianSerivce() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getBooksByBranchId(Integer branchId) {
		List<String> books = new ArrayList<String>();
		try (Connection sqlConnection = dbConnection.getConnection()){
			BookDAO bookDAO = new BookDAO(sqlConnection);
			
			List<Book> bookList = bookDAO.getByBranchId(branchId);
			bookList.forEach(book -> {
				AuthorDAO authorDAO = new AuthorDAO(sqlConnection);
				try {
					List<Author> authors = authorDAO.getByBookId(book.getBookId());
					book.setAuthors(authors);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				books.add(book.toRowStringWithAuthors());
			});

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
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

	public void update(LibraryBranch branch) throws SQLException {
		Connection sqlConnection = null;
		try {
			sqlConnection = dbConnection.getConnection();
			LibraryBranchDAO branchDAO = new LibraryBranchDAO(sqlConnection);
			branchDAO.put(branch.getBranchId(), branch);
			sqlConnection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlConnection.rollback();
		} finally {
			sqlConnection.close();
		}
	}

}
