/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.entity.BookGenre;

/**
 * @author ducba
 *
 */
public class BookGenresDAO extends BaseDAO<BookGenre> {

	/**
	 * 
	 */
	public BookGenresDAO(Connection sqlConnection) {
		super(sqlConnection);
		this.tableName = "tbl_book_genres";
	}

	@Override
	public List<BookGenre> get() throws SQLException {
		return extractData(read());
	}

	@Override
	public void post(BookGenre object) throws SQLException {
		save("INSERT tbl_book_genres (bookId, genre_Id) VALUES (?, ?)",
				new Object[] { object.getBookId(), object.getGenreId() });
	}

	public void put(Integer bookId, Integer genreId, BookGenre object) throws SQLException {
		save("UPDATE tbl_book_genres SET bookId=?, genre_id=? WHERE bookId=? and genre_id=?",
				new Object[] { object.getBookId(), object.getGenreId(), bookId, genreId });
	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void remove(BookGenre bookGenre) throws SQLException {
		save("DELETE FROM tbl_book_genres WHERE bookId=? and genre_id=?",
				new Object[] { bookGenre.getBookId(), bookGenre.getGenreId() });
	}

	@Override
	public List<BookGenre> extractData(ResultSet results) throws SQLException {
		List<BookGenre> bookGenres = new ArrayList<BookGenre>();

		while (results.next()) {
			BookGenre bookGenre = new BookGenre();
			bookGenre.setBookId(results.getInt("bookId"));
			bookGenre.setGenreId(results.getInt("genre_id"));
			bookGenres.add(bookGenre);
		}
		return bookGenres;
	}

	@Override
	public void put(Integer id, BookGenre object) throws SQLException {
		// TODO Auto-generated method stub

	}

	public List<Object> get(Object[] keys) {
		// TODO Auto-generated method stub
		return null;
	}

	public void post(Object[] objects) throws SQLException {
		save("insert into tbl_book_genres (bookId, genre_id) values (?,?)", objects);
		
	}

	public void delete(String[] primaryKeys) {
		// TODO Auto-generated method stub
		
	}

	public void put(String[] objects) {
		// TODO Auto-generated method stub
		
	}

}
