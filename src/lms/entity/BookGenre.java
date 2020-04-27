/**
 * 
 */
package lms.entity;

/**
 * @author ducba
 *
 */
public class BookGenre implements StringFormattable {
	private Integer genreId;
	private Integer bookId;
	/**
	 * 
	 */
	public BookGenre() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the genreId
	 */
	public Integer getGenreId() {
		return genreId;
	}
	/**
	 * @param genreId the genreId to set
	 */
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}
	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String toRowString() {
		return stringify(bookId.toString(), genreId.toString());
	}

}
