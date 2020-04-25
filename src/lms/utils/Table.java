/**
 * 
 */
package lms.utils;

import java.util.function.BinaryOperator;
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
		String columns = columnNames.length > 0 ? appendColumnName(columnNames) : "*";
		return String.format("SELECT %s FROM %s", columns, tableName);
	}
	
	public String selectOrdered(String columnName) {
		return String.format("SELECT * FROM %s ORDER BY %s", tableName, columnName);
	}
	
	public String updateRowById(String... columnNames) {
		String columns = Stream.of(columnNames).map(str -> str+"=(?)").reduce("", writeColumnNames());
		return String.format("", tableName, columns);
	}

	public String insert(String... columnNames) {
		String columns = columnNames.length > 0 ? String.format("%s (%s)", tableName, appendColumnName(columnNames)) : tableName;
		return String.format("INSERT INTO %s VALUES (?)", columns);
	}

	public String delete(String columnName) {
		return String.format("DELETE FROM %s WHERE %s=?", tableName, columnName);
	}

	/**
	 * @param columnNames
	 * @return
	 */
	private String appendColumnName(String[] columnNames) {
		return Stream.of(columnNames).reduce("", writeColumnNames());
	}

	/**
	 * @return
	 */
	private BinaryOperator<String> writeColumnNames() {
		return (str1, str2) -> {
			if (str1.isEmpty()) {
				return str2;
			}
			return str1 + ", " + str2;
		};
	}

	public String selectWhere(String columnName) {
		return String.format("SELECT * FROM %s WHERE %s=(?)", tableName, columnName);
	}

}
