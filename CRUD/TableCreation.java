package advance_Java.JDBC.CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TableCreation {
	public static void main(String[] args){

		String createTable = "Create Table Employee( eid int primary key auto_increment, " +
				                                "name varchar(50), department varchar(50))";


		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aptron","root","root1234");

			Statement statement = con.createStatement();

			// Execute the SQL statement to create the "employee" table
			statement.execute(createTable);

			// Close the statement and the connection
			statement.close();
			con.close();

			System.out.println("Table 'employee' created successfully.");

		}catch(Exception ex){
			System.out.println(ex);
		}
	}
}
