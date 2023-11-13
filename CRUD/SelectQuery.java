package advance_Java.JDBC.CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SelectQuery {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aptron", "root", "root1234");
			System.out.println("Connection Created...");

			String query = "Select * from employee where eid = ?";

			PreparedStatement ps = con.prepareStatement(query);

			System.out.print("Enter the employee id :");
			int sid = scanner.nextInt();

			ps.setInt(1,sid);

			ResultSet rst = ps.executeQuery();

			if(rst.next()){
				int v1 = rst.getInt(1);
				String v2 = rst.getString(2);
				String v3 = rst.getString(3);

				System.out.println("Employee id : " +v1);
				System.out.println("Employee name : " +v2);
				System.out.println("Employee department : " +v3);
			}else{
				System.out.println("Record not found....");
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
