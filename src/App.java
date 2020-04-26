import java.sql.SQLException;
import lms.ui.UserInterface;

public class App {

	public static void main(String[] args) throws SQLException {
		UserInterface ui = new UserInterface();
		ui.run();
	}
}
