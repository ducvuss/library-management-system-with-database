import java.sql.SQLException;

import lms.dao.AuthorDAO;
import lms.dao.Queryable;
import lms.entity.Author;

public class App {

	public static void main(String[] args) throws SQLException {
		AuthorDAO authorDAO = new AuthorDAO();
		authorDAO.getAuthors().forEach(x -> System.out.println(x.getAuthorName()));
	}

}
