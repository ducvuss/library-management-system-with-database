/**
 * 
 */
package lms.entity;

/**
 * @author ducba
 *
 */
public class Author implements StringFormattable {
	private Integer authorId;
	private String authorName;

	public Author() {
	}

	public Author(String authorName) {
		this.authorName = authorName;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String toRowString() {
		return stringify(authorId.toString(), authorName);
	}
}
