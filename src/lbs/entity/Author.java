/**
 * 
 */
package lbs.entity;

/**
 * @author ducba
 *
 */
public class Author {
	private String authorName;

	public Author(String authorName) {
		this.setAuthorName(authorName);
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
}
