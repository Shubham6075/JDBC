package advance_Java.JDBC.CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteQuery {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aptron", "root", "root1234");
			System.out.println("Database Connected.....");

			Scanner scanner = new Scanner(System.in);

			System.out.print("Enter employee ID to delete: ");
			int employeeIdToDelete = scanner.nextInt();

			// Construct the DELETE SQL query
			String deleteQuery = "DELETE FROM employee WHERE eid = ?";

			PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);

			deleteStatement.setInt(1, employeeIdToDelete);

			int rowsDeleted = deleteStatement.executeUpdate();

			if (rowsDeleted > 0) {
				System.out.println("Record with student ID " + employeeIdToDelete + " deleted successfully.");
			} else {
				System.out.println("No records found for student ID " + employeeIdToDelete + ". No records deleted.");
			}

			con.close(); // Close the database connection

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
