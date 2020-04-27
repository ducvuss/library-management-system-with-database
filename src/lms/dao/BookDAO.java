/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.entity.Book;
import lms.utils.Table;

/**
 * @author ducba
 *
 */
public class BookDAO extends BaseDAO<Book> {

	private String tableName;

	/**
	 * @throws SQLException
	 * 
	 */
	public BookDAO() throws SQLException {
		super();
		this.tableName = "tbl_book";
	}

	public BookDAO(Connection sqlConnection) {
		super(sqlConnection);
		this.tableName = "tbl_book";
	}

	@Override
	public List<Book> get() throws SQLException {
		return extractData(read(new Table(tableName).select()));
	}

	@Override
	public void post(Book book) throws SQLException {
		save(new Table(tableName).insert("title"), new Object[] { book.getTitle() });
	}

	@Override
	public void put(Integer bookId, Book book) throws SQLException {
		save("UPDATE tbl_book SET title=? WHERE bookId=?", new Object[] { book.getTitle(), bookId });
	}

	@Override
	public void delete(Integer bookId) throws SQLException {
		save(new Table(tableName).delete("bookId"), new Object[] { bookId });
	}

	@Override
	public List<Book> extractData(ResultSet results) throws SQLException {
		List<Book> books = new ArrayList<>();
		
		while (results.next()) {
			Book book = new Book();
			book.setTitle(results.getString("title"));
			book.setBookId(results.getInt("bookId"));
			books.add(book);
		}
		return books;
	}

	public List<Book> getByBranchId(Integer branchId) throws SQLException {
		return extractData(read(new Table(tableName).select() + " WHERE pubId=(?)", new Object[] { branchId }));
	}

	public ResultSet execute(String sqlQuery, Object[] objects) throws SQLException {
		return read(sqlQuery, objects);
	}

	public List<Object> get(Object[] keys) throws SQLException {
		// TODO Auto-generated method stub
		return destructData(read("SELECT * FROM " + tableName + " WHERE bookId=?", keys));
	}
	
	public List<Object> destructData(ResultSet results) throws SQLException {
		List<Object> books = new ArrayList<>();
		
		while (results.next()) {
			books.add(results.getInt("bookId"));
			books.add(results.getString("title"));
		}
		return books;
	}
}
