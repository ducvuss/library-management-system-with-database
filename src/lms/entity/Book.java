/**
 * 
 */
package lms.entity;

import java.util.List;

/**
 * @author ducba
 *
 */
public class Book {
	private Integer bookId;
	private String title;
	private Integer publisherId;
	private Publisher publisher;
	private List<Author> authors;

	public Book() {
	}
	
	public Book(String title) {
		this.title = title;
	}

	public Integer getBookId() {
		return bookId;
	}
	
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public String toRowString() {
		return String.format("%d - %s", bookId, title);
	}

	public String toRowStringWithAuthors() {
		return String.format("%d - %s by %s", bookId, title, authors.stream().map(author -> author.getAuthorName()).reduce("", (str1, str2) -> {
			if (str1.isEmpty()) {
				return str2;
			}
			return str1 + ", " + str2;
		}));
	}
}
