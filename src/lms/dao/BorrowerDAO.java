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
		return null;
	}

	public Borrower getByCardNo(Integer cardNo) throws SQLException {

		List<Borrower> borrowers = extractData(read(new Table(tableName).selectWhere("cardNo")));
		
		return borrowers.size() > 0 ? borrowers.get(0) : null;
	}

	@Override
	public void post(Borrower object) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void put(Integer id, Borrower object) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Borrower> extractData(ResultSet results) throws SQLException {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		while (results.next()) {
			Borrower borrower = new Borrower();
			borrower.setAddress(results.getString("address"));
			borrower.setName(results.getString("borrowerAddress"));
			borrower.setPhone(results.getString("phone"));
			borrower.setCardNo(results.getInt("cardNo"));
			borrowers.add(borrower);
		}
		return borrowers;
	}

}
