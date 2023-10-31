package javaFromScratch.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class DB {

	private static Connection conn = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static PreparedStatement preparedStatement = null;

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String dbUrl = props.getProperty("dburl");
				System.out.println(props.getProperty("dburl"));
				conn = DriverManager.getConnection(dbUrl, props);
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
		return conn;
	}

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("files/properties")) { // rewrite this properties
																				// finding
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DBException(e.getMessage());
		}
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
//				statement.close();
//				resultSet.close();
				conn.close();
				System.out.println("DB connection closed");
			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
	}

	public static ResultSet getQueryDb(String queryString) {
		if (conn != null) {
			try {
				statement = conn.createStatement();
				resultSet = statement.executeQuery(queryString);
				while (resultSet.next()) {
					System.out.println(resultSet.getInt("Id") + " " + resultSet.getString("Name"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultSet;
	}

	public static void insertNewSeller(String name, String email, Date date, Double salary, int departmentId) {
		if (conn != null) {
			try {
				String sellerFields = "Name, Email, BirthDate, BaseSalary, DepartmentId";

				// No JBDC, utilizamos o '?' como um placeholder para depois colocar o valor
				String queryString = "INSERT INTO seller" + " (" + sellerFields + ")" + " VALUES" + "(?, ?, ?, ?, ?) ";
				// agora trocamos os valores:
				preparedStatement = conn.prepareStatement(queryString, statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, email);
				preparedStatement.setDate(3, date);
				preparedStatement.setDouble(4, salary);
				preparedStatement.setInt(5, departmentId);

				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					System.out.println("Done! " + rowsAffected + " rows were affected");
					ResultSet resultSet = preparedStatement.getGeneratedKeys();
					while (resultSet.next()) {
						int id = resultSet.getInt(1);
						System.out.println("Id generated: " + id);
					}

				} else {
					System.out.println("No rows were affected");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		return preparedStatement;
	}

	public static void insertNewDepartments(String[] names) {
		if (conn != null) {
			try {

				StringBuilder joinedNames = new StringBuilder();
				for (String name : names) {
					if (joinedNames.length() > 0) {
						joinedNames.append(", ");
					}
					joinedNames.append("('").append(name).append("')");
				}
				// Também é possível fazer diretamente
				String queryString = "INSERT INTO department (Name)" + " VALUES " + joinedNames;
				preparedStatement = conn.prepareStatement(queryString, statement.RETURN_GENERATED_KEYS);

				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					System.out.println("Done! " + rowsAffected + " rows were affected");
					ResultSet resultSet = preparedStatement.getGeneratedKeys();
					while (resultSet.next()) {
						int id = resultSet.getInt(1);
						System.out.println("Id generated: " + id);
					}

				} else {
					System.out.println("No rows were affected");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		return preparedStatement;
	}

	public static void insertQueryDb(String tableName, String[] fields, String[] values) {
		if (conn != null) {
			try {
				String valuesString = "(";
				StringJoiner fieldsString = new StringJoiner(", ");
				for (int i = 0; i < fields.length; i++) {
					fieldsString.add(fields[i]);
					if (i == fields.length - 1) {
						valuesString += "?)";
					} else {
						valuesString += "?,";
					}
				}
				String queryString = "INSERT INTO " + tableName + " (" + fieldsString.toString() + ")" + " VALUES"
						+ " ";
				preparedStatement = conn.prepareStatement(queryString);
				int rowsAffected = preparedStatement.executeUpdate();
				System.out.println("Done! " + rowsAffected + " rows were affected");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		return preparedStatement;
	}

	public static void updateSellersSalaryByDepartment(Integer departmentId, Double updatedValue) {
		if (conn != null) {

			try {
				Pattern numberPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
				String queryString = "UPDATE seller " + "SET BaseSalary = ? "
						+ " WHERE" + "( DepartmentId = ?)";
				preparedStatement = conn.prepareStatement(queryString);
				
				preparedStatement.setDouble(1, updatedValue);
				preparedStatement.setInt(2, departmentId);
				
				int rowsAffected = preparedStatement.executeUpdate();
				System.out.println("Done! " + rowsAffected + " rows were affected");

				System.err.println(queryString);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//	return preparedStatement;
	}
		
	public static void deleteById(String tableName, Integer id) {
		if (conn != null) {

			try {
				String queryString = "DELETE FROM " + tableName + " WHERE Id = " + id;
				preparedStatement = conn.prepareStatement(queryString);
				
				int rowsAffected = preparedStatement.executeUpdate();
				System.out.println("Done! " + rowsAffected + " rows were affected");

				System.err.println(queryString);

			} catch (SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
	}
	
	public static void transactionLearning() {
		if (conn != null) {

			try {
				Statement st = conn.createStatement();
				
				conn.setAutoCommit(false);
				
				int rowsAffected1 = st.executeUpdate("UPDATE seller set BaseSalary = 2000 WHERE DepartmentId = 1");
				
				// Fake error
//				if(true) {
//					throw new SQLException("Fake error");
//					// without the setAutoCommit(false) + commit(), the operation above would proceed, but the below would not;
//				}
				
				int rowsAffected2 = st.executeUpdate("UPDATE seller set BaseSalary = 3500 WHERE DepartmentId = 2");
				
//				conn.commit();

				System.out.println("Done 1st operation! " + rowsAffected1 + " rows were affected");
				System.out.println("Done 2nd operation! " + rowsAffected2 + " rows were affected");
			

			} catch (SQLException e) {
				try {
					conn.rollback();
					throw new DBException("Transaction rolled back. Caused by: " + e.getMessage());
				} catch(SQLException e1) {
					throw new DBException("Transaction rolled back FAILED. Caused by: " + e1.getMessage());
				}
			}
		}
	}
}


