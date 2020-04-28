/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lms.entity.BookAuthor;

/**
 * @author ducba
 *
 */
public class BookAuthorDAO extends BaseDAO<BookAuthor>{

	/**
	 * 
	 */
	public BookAuthorDAO(Connection sqlConnection) {
		super(sqlConnection);
		this.tableName = "tbl_book_authors";
	}

	@Override
	public List<BookAuthor> get() throws SQLException {
		return extractData(read());
	}

	@Override
	public void post(BookAuthor object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put(Integer id, BookAuthor object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BookAuthor> extractData(ResultSet results) throws SQLException {
		List<BookAuthor> items = new ArrayList<BookAuthor>();

		while (results.next()) {
			BookAuthor item = new BookAuthor();
			item.setBookId(results.getInt("bookId"));
			item.setAuthorId(results.getInt("authorId"));
			items.add(item);
		}
		return items;
	}

	public List<Object> get(Object[] keys) throws SQLException {
		// TODO Auto-generated method stub
		return destructData(read("SELECT * FROM " + tableName + " WHERE bookId=? and authorId=?", keys));
	}
	
	public List<Object> destructData(ResultSet results) throws SQLException {
		List<Object> books = new ArrayList<>();
		
		while (results.next()) {
			books.add(results.getInt("bookId"));
			books.add(results.getInt("authorId"));
		}
		return books;
	}

	public void post(Object[] objects) throws SQLException {
		save("insert into tbl_book_authors (bookId, authorId) values (?,?)", objects);
	}

	public void delete(String[] primaryKeys) throws SQLException {
		save("delete from " + tableName + " WHERE bookId=? and authorId=?", primaryKeys);
	}

	public void put(String[] objects) throws SQLException {
//		save("insert into tbl_book_authors (bookId, authorId) values (?,?)", objects);
		System.out.println("You can't update book author");
	}

}
