package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDAO  extends BaseDAO<String> {

	public AdminDAO(Connection sqlConnection, String entityName){
		super(sqlConnection);
		this.tableName = entityName;
	}
	
	public AdminDAO(Connection sqlConnection) {
		super(sqlConnection);
	}

	@Override
	public List<String> get() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<String> getAll() throws SQLException {
//		ResultSet results = read("select * from " + tableName);
		return null;
	}

	@Override
	public void post(String object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put(Integer id, String object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> extractData(ResultSet results) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void execute(String sqlQuery) throws SQLException {
		save(sqlQuery, new Object[] {});
	}

	public void execute(String commands, Object[] objects) throws SQLException {
		save(commands, objects);
	}

}
