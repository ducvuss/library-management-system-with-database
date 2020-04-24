/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.entity.Author;
import lms.utils.Table;

/**
 * @author ducba
 *
 */
public class AuthorDAO implements Queryable<Author> {
	public static final String url = "jdbc:mysql://localhost:3306/library?useSSL=false";
	public static final String user = "root";
	public static final String password = "test";
	/**
	 * 
	 */
	public AuthorDAO() {
	}
	
	public List<Author> getAuthors() throws SQLException {
		Connection sqlConnection = DriverManager.getConnection(url, user, password);
		return extractData(read(sqlConnection, new Table("tbl_author").select()));
	}
	
	public List<Author> extractData(ResultSet results) throws SQLException {
		List<Author> authors = new ArrayList<>();
		while(results.next()) {
			Author author = new Author();
			author.setAuthorName(results.getString("authorName"));
			author.setAuthorId(results.getInt("authorId"));
			authors.add(author);
		}
		return authors;
	}
}
