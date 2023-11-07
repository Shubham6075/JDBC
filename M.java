import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class M {
	public static void main(String[] args) {
		System.out.println("Hello world!");

			try {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aptron", "root", "root1234");
				if (connection != null) {
					System.out.println("Connection established.");
					System.out.println("Database URL: " + connection.getMetaData().getURL());
					System.out.println("Username: " + connection.getMetaData().getUserName());
					// Use the connection for database operations
				} else {
					System.err.println("Connection failed.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
}