/**
 * 
 */
package lms.utils.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lms.utils.Table;

/**
 * @author ducba
 *
 */
public class TableTest {

	@Test
	public void testSelect() {
		assertEquals("SELECT * FROM tbl_author", new Table("tbl_author").select());
		assertEquals("SELECT authorId, authorName FROM tbl_author",
				new Table("tbl_author").select("authorId, authorName"));
	}

	@Test
	public void testInsert() {
		assertEquals("INSERT INTO tbl_author VALUES (?)", new Table("tbl_author").insert());
		assertEquals("INSERT INTO tbl_author (authorName) VALUES (?)", new Table("tbl_author").insert("authorName"));
	}

}
