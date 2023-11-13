package advance_Java.JDBC.CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UpdateQuery {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aptron","root","root1234");
			System.out.println("Database Connected......");

			System.out.print("Enter the employee id to update : ");
			int employeeIdToUpdate = scanner.nextInt();

			scanner.nextLine();

			System.out.print("Enter updated employee department : ");
			String updatedDepartment = scanner.nextLine();

			// sql update query
			String query = "Update employee SET department = ? Where eid = ?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, updatedDepartment);
			ps.setInt(2, employeeIdToUpdate);

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Record with student ID " + employeeIdToUpdate + " updated successfully.");
			} else {
				System.out.println("No records found for student ID " + employeeIdToUpdate + ". No records updated.");
			}

			con.close(); // Close the database connection
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
}
