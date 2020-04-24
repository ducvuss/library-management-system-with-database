/**
 * 
 */
package lms.utils;

import java.util.stream.Stream;

/**
 * @author ducba
 *
 */
public class Table {

	private String tableName;

	/**
	 * 
	 */
	public Table(String tableName) {
		this.tableName = tableName;
	}

	public String select(String... columnNames) {
		return "SELECT " + Stream.of(columnNames).reduce("*", (str1, str2) -> {
			if (str1.equals("*")) {
				return str2;
			}
			return str1 + ", " + str2;
		}) + " FROM " + tableName;
	}

}
