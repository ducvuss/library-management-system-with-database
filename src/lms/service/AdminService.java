/**
 * 
 */
package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import lms.dao.AuthorDAO;
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

	public void readTable(String currentEntity) throws SQLException {
		List<String> strings = null;
		try (Connection conn = dbConn.getConnection()) {
			switch (currentEntity) {
			case "Book":
				strings = new BookDAO(conn).get().stream().map(o -> o.toRowString()).collect(Collectors.toList());
				break;
			case "Author":
				strings = new AuthorDAO(conn).get().stream().map(o -> o.toRowString()).collect(Collectors.toList());
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
				strings = new LibraryBranchDAO(conn).get().stream().map(o -> o.toRowString()).collect(Collectors.toList());
				break;
			case "Publishers":
				strings = new PublisherDAO(conn).get().stream().map(o -> o.toRowString()).collect(Collectors.toList());
				break;
			default:
				break;
			}
		}

	}

}
