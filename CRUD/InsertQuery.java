package advance_Java.JDBC.CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsertQuery {
	public static void main(String[] args){

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aptron","root","root1234");

			System.out.println("Database Connected...");

			Scanner scanner = new Scanner(System.in);

			System.out.print("Enter employee id : ");
			int employeeId = scanner.nextInt();

			scanner.nextLine();   // consume next Line

			System.out.print("Enter employee name : ");
			String employeeName = scanner.nextLine();

			System.out.print("Enter employee department : ");
			String employeeDepartment = scanner.nextLine();

			String query = "insert into employee (eid , name , department) values (? , ? ,?)";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, employeeId);
			ps.setString(2, employeeName);
			ps.setString(3,employeeDepartment);

			ps.executeUpdate();

			System.out.println("Record Inserted");

			con.close(); // Close the database connection

		}catch(Exception ex){
			System.out.println(ex);
		}
	}
}
