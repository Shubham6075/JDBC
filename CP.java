package student_Manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CP {

	static Connection con;

	public static Connection createC() {
		try {
			String user = "root";
			String password = "root1234";
			String url = "jdbc:mysql://localhost:3306/student_manage";

			// Load the MySQL JDBC driver. You need to add the JDBC driver JAR to your project.
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (ClassNotFoundException e) {
			// Handle ClassNotFoundException - the driver class was not found
			e.printStackTrace();
		} catch (SQLException e) {
			// Handle SQLException - there was an issue with the database connection
			e.printStackTrace();
		}
		return null; // Return null to indicate that the connection failed
	}
}
