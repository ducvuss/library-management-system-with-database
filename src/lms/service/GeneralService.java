/**
 * 
 */
package lms.service;

import java.sql.Connection;
import java.sql.SQLException;

import lms.dao.BorrowerDAO;
import lms.entity.Borrower;
import lms.utils.DbConnection;

/**
 * @author ducba
 *
 */
public class GeneralService extends BaseService {
	DbConnection dbConnection = new DbConnection();

	/**
	 * 
	 */
	public GeneralService() {
		// TODO Auto-generated constructor stub
	}

	public Integer validateCardNo(int cardNo) {
		Borrower borrower = null;
		try (Connection sqlConnection = dbConnection.getConnection()) {
			BorrowerDAO borrowerDAO = new BorrowerDAO(sqlConnection);
			borrower = borrowerDAO.getByCardNo(cardNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return borrower == null ? null : borrower.getCardNo();
	}
}
