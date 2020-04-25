/**
 * 
 */
package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.entity.LibraryBranch;
import lms.utils.Table;

/**
 * @author ducba
 *
 */
public class LibraryBranchDAO extends BaseDAO<LibraryBranch> {

	private String tableName;

	/**
	 * 
	 */
	public LibraryBranchDAO(Connection sqlConnection) {
		super(sqlConnection);
		this.tableName = "tbl_library_branch";
	}

	@Override
	public List<LibraryBranch> get() throws SQLException {
		// TODO Auto-generated method stub
		return extractData(read(new Table(tableName).select()));
	}

	@Override
	public void post(LibraryBranch object) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void put(Integer id, LibraryBranch object) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<LibraryBranch> extractData(ResultSet results) throws SQLException {
		List<LibraryBranch> branches = new ArrayList<LibraryBranch>();
		while (results.next()) {
			LibraryBranch branch = new LibraryBranch();
			branch.setBranchId(results.getInt("branchId"));
			branch.setBranchName(results.getString("branchName"));
			branch.setBranchAddress(results.getString("branchAddress"));
			branches.add(branch);
		}
		return branches;
	}

	public LibraryBranch get(Integer branchId) throws SQLException {
		// TODO Auto-generated method stub
		return extractData(read(new Table(tableName).selectWhere("branchId"), new Object[] { branchId })).get(0);
	}


}
