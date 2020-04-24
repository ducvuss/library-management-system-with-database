import java.sql.SQLException;
import java.util.Objects;

import lms.dao.AuthorDAO;
import lms.entity.Author;

public class App {

	public static void main(String[] args) throws SQLException {
		AuthorDAO authorDAO = new AuthorDAO();
//		authorDAO.put(2, new Author("Jimmy"));
		authorDAO.delete(1);
		authorDAO.get().forEach(x -> System.out.println(x.getAuthorName()));
//		authorDAO.post(new Author("Tom Tom"));
//		authorDAO.save("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[] { "Test" });
	}

}
