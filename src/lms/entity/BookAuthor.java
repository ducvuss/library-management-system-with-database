/**
 * 
 */
package lms.entity;

/**
 * @author ducba
 *
 */
public class BookAuthor implements StringFormattable {

	private Integer bookId;
	private Integer authorId;

	/**
	 * 
	 */
	public BookAuthor() {
		// TODO Auto-generated constructor stub
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

	/**
	 * @return the authorId
	 */
	public Integer getAuthorId() {
		return authorId;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String toRowString() {
		return stringify(bookId.toString(), authorId.toString());
	}
}
