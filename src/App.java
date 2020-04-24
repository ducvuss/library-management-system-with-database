import java.sql.SQLException;
import java.util.Objects;

import lms.dao.AuthorDAO;

public class App {

	public static void main(String[] args) throws SQLException {
		AuthorDAO authorDAO = new AuthorDAO();
		authorDAO.get().forEach(x -> System.out.println(x.getAuthorName()));
//		authorDAO.save("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[] { "Test" });
	}

}
