import java.sql.SQLException;
import java.util.Objects;

import lms.dao.AuthorDAO;
import lms.dao.BookDAO;
import lms.entity.Author;
import lms.entity.Book;

public class App {

	public static void main(String[] args) throws SQLException {
		AuthorDAO authorDAO = new AuthorDAO();
//		authorDAO.put(2, new Author("Jimmy"));
//		authorDAO.delete(1);
//		authorDAO.get().forEach(x -> System.out.println(x.getAuthorName()));
//		authorDAO.post(new Author("Tom Tom"));
//		authorDAO.save("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[] { "Test" });

		BookDAO bookDAO = new BookDAO();
		bookDAO.delete(31);
		bookDAO.put(33, new Book("The Best Book Ever II"));
//		bookDAO.post(new Book("Pronto"));
		bookDAO.get().forEach(x -> System.out.println(x.getBookId() + " " + x.getTitle()));
	}

}
