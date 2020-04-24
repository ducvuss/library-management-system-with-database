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
	
	/**
	 * @throws SQLException 
	 * 
	 */
	public AuthorDAO() throws SQLException {
		super();
	}
	


	@Override
	public List<Author> get() throws SQLException {
		return extractData(read(new Table("tbl_author").select()));
	}

	@Override
	public void post(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put(Integer id, Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
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
