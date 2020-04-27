/**
 * 
 */
package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lms.dao.AdminDAO;
import lms.dao.AuthorDAO;
import lms.dao.BookAuthorDAO;
import lms.dao.BookDAO;
import lms.dao.BookGenresDAO;
import lms.dao.BookLoansDAO;
import lms.dao.LibraryBranchDAO;
import lms.dao.PublisherDAO;
import lms.utils.DbConnection;

/**
 * @author ducba
 *
 */
public class AdminService {

	DbConnection dbConn = new DbConnection();

	/**
	 * 
	 */
	public AdminService() {
		// TODO Auto-generated constructor stub
	}

	public List<String> readTable(String currentEntity) throws SQLException {
		List<String> strings = null;
		try (Connection conn = dbConn.getConnection()) {
			switch (currentEntity) {
			case "Book":
				strings = new BookDAO(conn).get().stream().map(o -> o.toRowString()).collect(Collectors.toList());
				break;
			case "Author":
				strings = new AuthorDAO(conn).get().stream().map(o -> o.toRowString()).collect(Collectors.toList());
				break;
			case "Book Authors":
				strings = new BookAuthorDAO(conn).get().stream().map(o -> o.toRowString()).collect(Collectors.toList());
				break;
			case "Book Loan":
				strings = new BookLoansDAO(conn).get().stream().map(o -> o.toRowString()).collect(Collectors.toList());
				break;
			case "Book Genres":
				strings = new BookGenresDAO(conn).get().stream().map(o -> o.toRowString()).collect(Collectors.toList());
				break;
			case "Borrowers":
				strings = new BookGenresDAO(conn).get().stream().map(o -> o.toRowString()).collect(Collectors.toList());
				break;
			case "Library Branches":
				strings = new LibraryBranchDAO(conn).get().stream().map(o -> o.toRowString())
						.collect(Collectors.toList());
				break;
			case "Publishers":
				strings = new PublisherDAO(conn).get().stream().map(o -> o.toRowString()).collect(Collectors.toList());
				break;
			default:
				break;
			}

			return strings;
		} catch (Exception e) {
			return new ArrayList<String>();
		}
	}

	public List<Object> readTable(String currentEntity, Object[] keys) {
		List<Object> objects = null;
		try (Connection conn = dbConn.getConnection()) {
			switch (currentEntity) {
			case "Book":
				objects = new BookDAO(conn).get(keys);
				break;
			case "Author":
				objects = new AuthorDAO(conn).get(keys);
				break;
			case "Book Authors":
				objects = new BookAuthorDAO(conn).get(keys);
				break;
			case "Book Loan":
				objects = new BookLoansDAO(conn).get(keys);
				break;
			case "Book Genres":
				objects = new BookGenresDAO(conn).get(keys);
				break;
			case "Borrowers":
				objects = new BookGenresDAO(conn).get(keys);
				break;
			case "Library Branches":
				objects = new LibraryBranchDAO(conn).get(keys);
				break;
			case "Publishers":
				objects = new PublisherDAO(conn).get(keys);
				break;
			default:
				break;
			}

			return objects;
		} catch (Exception e) {
			return new ArrayList<Object>();
		}
	}

	public boolean execute(String commands) throws SQLException {
		Connection sqlConnection = null;

		try {
			sqlConnection = dbConn.getConnection();
			AdminDAO adminDAO = new AdminDAO(sqlConnection);
			adminDAO.execute(commands);
			sqlConnection.commit();
			return true;
		} catch (Exception e) {
			sqlConnection.rollback();
//			e.printStackTrace();
			return false;
		} finally {
			sqlConnection.close();
		}
	}

	public boolean execute(String commands, Object[] objects) throws SQLException {
		Connection sqlConnection = null;

		try {
			sqlConnection = dbConn.getConnection();
			AdminDAO adminDAO = new AdminDAO(sqlConnection);
			adminDAO.execute(commands, objects);
			sqlConnection.commit();
			return true;
		} catch (Exception e) {
			sqlConnection.rollback();
			e.printStackTrace();
			return false;
		} finally {
			sqlConnection.close();
		}
	}

	public boolean updateBookLoanDueDate(String[] objects) throws SQLException {
		return execute(
				"update tbl_book_loans set dueDate=? where bookId=? and branchId=? and cardNo=? and dateIn is null;",
				new String[] { objects[0] + " " + objects[1], objects[2], objects[3], objects[4] });
	}

}
