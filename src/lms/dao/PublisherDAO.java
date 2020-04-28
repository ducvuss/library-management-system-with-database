/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.entity.Publisher;
import lms.entity.StringFormattable;
import lms.utils.Table;

/**
 * @author ducba
 *
 */
public class PublisherDAO extends BaseDAO<Publisher> implements StringFormattable {

	private String tableName;

	/**
	 * @throws SQLException
	 * 
	 */
	public PublisherDAO() throws SQLException {
		super();
		this.tableName = "tbl_publisher";
	}

	public PublisherDAO(Connection conn) {
		super(conn);
		this.tableName = "tbl_publisher";
	}

	@Override
	public List<Publisher> get() throws SQLException {
		return extractData(read(new Table(tableName).select()));
	}

	@Override
	public void post(Publisher publisher) throws SQLException {
		save(new Table(tableName).insert("publisherName"), new Object[] { publisher.getPublisherName() });
	}

	@Override
	public void put(Integer publisherId, Publisher publisher) throws SQLException {
		save("UPDATE tbl_publisher SET publisherName=? WHERE publisherId=?",
				new Object[] { publisher.getPublisherName(), publisherId });
	}

	@Override
	public void delete(Integer publisherId) throws SQLException {
		save(new Table(tableName).delete("publisherId"), new Object[] { publisherId });
	}

	@Override
	public List<Publisher> extractData(ResultSet results) throws SQLException {
		List<Publisher> publishers = new ArrayList<>();
		while (results.next()) {
			Publisher publisher = new Publisher();
			publisher.setPublisherName(results.getString("publisherName"));
			publisher.setPublisherId(results.getInt("publisherId"));
			publishers.add(publisher);
		}
		return publishers;
	}

	public List<Object> get(Object[] keys) {
		// TODO Auto-generated method stub
		return null;
	}

	public void post(Object[] objects) throws SQLException {
		save("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?,?,?)", objects);
	}

	public void delete(String[] primaryKeys) throws SQLException {
		save("delete from " + tableName + " WHERE publisherId=?", primaryKeys);
	}

	public void put(String[] objects) throws SQLException {
		List<String> columns = new ArrayList<String>();
		List<String> strings = new ArrayList<String>();
		if (!"n/a".equals(objects[0])) {
			strings.add(objects[0]);
			columns.add("publisherName=?");
		}
		if (!"n/a".equals(objects[1])) {
			strings.add(objects[1]);
			columns.add("publisherAddress=?");
		}
		if (!"n/a".equals(objects[2])) {
			strings.add(objects[2]);
			columns.add("publisherPhone=?");
		}
		strings.add(objects[3]);
		save("update tbl_publisher set " + columns.stream().reduce("", (x, y) -> accumulateString(x, y))
				+ " where publisherId=?", strings.toArray());
	}
}
