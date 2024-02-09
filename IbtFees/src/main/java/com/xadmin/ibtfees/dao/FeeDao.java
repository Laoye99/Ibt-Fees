package com.xadmin.ibtfees.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.ibtfees.bean.Fee;

public class FeeDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/esbuser?serverTimezone=UTC&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "oladunjoye99";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	private static final String INSERT_FEE_SQL = "INSERT INTO ibt_fees" + "  (fsn, acsn, fee_amt, fee_type_id) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_FEE_BY_FSN = "select fsn,acsn,fee_amt,fee_type_id from ibt_fees where fsn = ?";
	private static final String SELECT_ALL_FEE = "select * from ibt_fees";
	private static final String DELETE_FEE_SQL = "delete from ibt_fees where fsn = ?;";
	private static final String UPDATE_FEE_SQL = "update ibt_fees set acsn = ?,fee_amt= ?, fee_type_id =? where fsn = ?;";
	public FeeDao() {
	
	}
	
	protected Connection getConnection()  {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertFee(Fee fee)throws SQLException {
		System.out.println(INSERT_FEE_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FEE_SQL)) {
			preparedStatement.setInt(1, fee.getFsn());
			preparedStatement.setInt(2, fee.getAcsn());
			preparedStatement.setFloat(3, fee.getFee_amt());
			preparedStatement.setString(4, fee.getFee_type_id());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
}

	public Fee selectFee(int fsn){
		Fee fee = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FEE_BY_FSN);) {
			preparedStatement.setInt(1, fsn);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int acsn = rs.getInt("acsn");
				Float fee_amt = rs.getFloat("fee_amt");
				String fee_type_id = rs.getString("fee_type_id");
				fee = new Fee(fsn, acsn, fee_amt, fee_type_id);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return fee;
	}
	
public List<Fee> selectAllFees() {
		
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Fee> fees = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FEE);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int fsn = rs.getInt("fsn");
				int acsn = rs.getInt("acsn");
				Float fee_amt = rs.getFloat("fee_amt");
				String fee_type_id = rs.getString("fee_type_id");
				fees.add(new Fee(fsn, acsn, fee_amt, fee_type_id));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return fees;
	}

	public boolean updateFee(Fee fee) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_FEE_SQL);) {
			statement.setInt(1, fee.getAcsn());
			statement.setFloat(2, fee.getFee_amt());
			statement.setString(3, fee.getFee_type_id());
			statement.setInt(4, fee.getFsn());
			System.out.println("updated Fee:"+statement);
			
			rowUpdated = statement.executeUpdate() > 0;
			
		}
		return rowUpdated;
}
	
	public boolean deleteFee(int fsn) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_FEE_SQL);) {
			statement.setInt(1, fsn);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	private void printSQLException(SQLException ex) {for (Throwable e : ex) {
		if (e instanceof SQLException) {
			e.printStackTrace(System.err);
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println("Message: " + e.getMessage());
			Throwable t = ex.getCause();
			while (t != null) {
				System.out.println("Cause: " + t);
				t = t.getCause();
			}
		}
	}
}
}	