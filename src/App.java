import java.sql.SQLException;
import java.util.Objects;

import lms.dao.AuthorDAO;
import lms.dao.BookDAO;
import lms.dao.PublisherDAO;
import lms.entity.Author;
import lms.entity.Book;
import lms.entity.Publisher;
import lms.ui.UserInterface;
import lms.utils.DbConnection;

public class App {

	public static void main(String[] args) throws SQLException {
		UserInterface ui = new UserInterface();
		ui.run();
	}

}
