/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.entity.Borrower;
import lms.utils.Table;

/**
 * @author ducba
 *
 */
public class BorrowerDAO extends BaseDAO<Borrower> {

	private String tableName;

	/**
	 * 
	 */
	public BorrowerDAO(Connection sqlConnection) {
		// TODO Auto-generated constructor stub
		super(sqlConnection);
		this.tableName = "tbl_borrower";
	}

	@Override
	public List<Borrower> get() throws SQLException {
		return extractData(read("SELECT * FROM tbl_borrower"));
	}
	
	

	public Borrower getByCardNo(Integer cardNo) throws SQLException {

		List<Borrower> borrowers = extractData(
				read(new Table(tableName).selectWhere("cardNo"), new Object[] { cardNo }));

		return borrowers.size() > 0 ? borrowers.get(0) : null;
	}

	@Override
	public void post(Borrower object) throws SQLException {
		save("INSERT INTO tbl_borrower VALUES()",
				new Object[] { object.getCardNo(), object.getName(), object.getAddress(), object.getPhone() });
	}

	@Override
	public void put(Integer id, Borrower object) throws SQLException {
		save("UPDATE tbl_borrower SET cardNo=?, name=?, address=?, phone=? WHERE cardNo=?",
				new Object[] { object.getCardNo(), object.getName(), object.getAddress(), object.getPhone(), id });
	}

	@Override
	public void delete(Integer id) throws SQLException {
		save("DELETE FROM tbl_borrower where cardNo=?", new Object[] { id });

	}

	@Override
	public List<Borrower> extractData(ResultSet results) throws SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		while (results.next()) {
			Borrower borrower = new Borrower();
			borrower.setAddress(results.getString("address"));
			borrower.setName(results.getString("name"));
			borrower.setPhone(results.getString("phone"));
			borrower.setCardNo(results.getInt("cardNo"));
			borrowers.add(borrower);
		}
		return borrowers;
	}

	public void post(Object[] objects) throws SQLException {
		save("insert into tbl_borrower (cardNo, name, address, phone) values (?,?,?,?)", objects);
	}

	public void delete(String[] primaryKeys) throws SQLException {
		save("delete from " + tableName + " WHERE cardNo=?", primaryKeys);
	}

	public void put(String[] objects) throws SQLException {
		save("insert into tbl_borrower (name, address, phone) values (?,?,?) where cardNo=?", objects);
		
	}
}
