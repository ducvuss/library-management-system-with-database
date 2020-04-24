/**
 * 
 */
package lms.dao;

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
public class AuthorDAO extends BaseDAO<Author> {
	
	private String tableName;

	/**
	 * @throws SQLException 
	 * 
	 */
	public AuthorDAO() throws SQLException {
		super();
		this.tableName = "tbl_author";
	}
	


	@Override
	public List<Author> get() throws SQLException {
		return extractData(read(new Table(tableName).select()));
	}

	@Override
	public void post(Author author) throws SQLException {
		save(new Table(tableName).insert("authorName"), new Object[] { author.getAuthorName() });
	}

	@Override
	public void put(Integer authorId, Author author) throws SQLException {
		save("UPDATE tbl_author SET authorName=? WHERE authorId=?", new Object[] { author.getAuthorName(), authorId });
	}

	@Override
	public void delete(Integer authorId) throws SQLException {
		save(new Table(tableName).delete("authorId"), new Object[] { authorId });
	}

	@Override
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
