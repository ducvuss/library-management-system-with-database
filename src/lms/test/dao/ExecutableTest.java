package lms.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import lms.dao.Executable;
import lms.entity.Book;
import lms.utils.DbConnection;

public class ExecutableTest implements Executable<Book>{

	@Test
	public void testReadMethod() {
		// TODO Auto-generated constructor stub
		
		DbConnection dbConnection = new DbConnection();
		try {
			ResultSet result = read(dbConnection.getConnection(), "Select * from tbl_book");
			assertNotNull(result);
		} catch (SQLException e) {
			
		}
		
		assertThrows(SQLException.class, () -> {
			read(dbConnection.getConnection(), "Select * from tbl_books");
		});
	}

}
