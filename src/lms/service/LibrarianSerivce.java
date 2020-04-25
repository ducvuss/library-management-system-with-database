/**
 * 
 */
package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.dao.LibraryBranchDAO;
import lms.entity.LibraryBranch;
import lms.utils.DbConnection;

/**
 * @author ducba
 *
 */
public class LibrarianSerivce {

	/**
	 * 
	 */
	public LibrarianSerivce() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getBranches() {
		List<String> branches = new ArrayList<String>();
		try (Connection sqlConnection = new DbConnection().getConnection()) {
			LibraryBranchDAO branchDAO = new LibraryBranchDAO(sqlConnection);

			branchDAO.get().forEach(branch -> {
				branches.add(String.format("%d - %s - %s", branch.getBranchId(), branch.getBranchName(),
						branch.getBranchAddress()));
			});

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return branches;
	}

	public LibraryBranch getBranchById(Integer branchId) {
		LibraryBranch branch = null;
		try (Connection sqlConnection = new DbConnection().getConnection()) {
			LibraryBranchDAO branchDAO = new LibraryBranchDAO(sqlConnection);
			branch = branchDAO.get(branchId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return branch;
	}
}
