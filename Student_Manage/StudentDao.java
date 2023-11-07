package student_Manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentDao {

	public static boolean insertStudentToDB(Student st) {
		boolean f = false;

		try {
			Connection con = CP.createC();

			if (con != null) {
				String q = "insert into students(sname, sphone, scity) values (?, ?, ?)";
				// PreparedStatement
				PreparedStatement pstmt = con.prepareStatement(q);

				// set values to parameters
				pstmt.setString(1, st.getStudentName());
				pstmt.setString(2, st.getStudentPhone());
				pstmt.setString(3, st.getStudentCity());

				// execute
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					f = true;
				} else {
					System.out.println("Insertion failed.");
				}
			} else {
				System.out.println("Database connection is null. Check your database configuration.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return f;
	}

	public static boolean deleteStudent(int userId) {
		boolean success = false;

		try (Connection con = CP.createC()) {
			if (con != null) {
				String sql = "DELETE FROM students WHERE sid = ?";
				try (PreparedStatement pstmt = con.prepareStatement(sql)) {
					pstmt.setInt(1, userId);

					int rowsAffected = pstmt.executeUpdate();
					if (rowsAffected > 0) {
						success = true;
						System.out.println("Student with ID " + userId + " has been deleted.");
					} else {
						System.out.println("No student found with ID " + userId + " or deletion failed.");
					}
				}
			} else {
				System.out.println("Database connection is null. Check your database configuration.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public static void showAll() {
		try (Connection con = CP.createC()) {
			if (con != null) {
				String sql = "SELECT * FROM students";
				try (PreparedStatement pstmt = con.prepareStatement(sql);
				     ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						int studentId = rs.getInt("sid");
						String studentName = rs.getString("sname");
						String studentPhone = rs.getString("sphone");
						String studentCity = rs.getString("scity");

						System.out.println("Student ID: " + studentId);
						System.out.println("Name: " + studentName);
						System.out.println("Phone: " + studentPhone);
						System.out.println("City: " + studentCity);
						System.out.println();
					}
				}
			} else {
				System.out.println("Database connection is null. Check your database configuration.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean updateStudent(int studentId, String newName, String newPhone, String newCity) {
		try (Connection con = CP.createC()) {
			if (con != null) {
				String sql = "UPDATE students SET sname = ?, sphone = ?, scity = ? WHERE sid = ?";
				try (PreparedStatement pstmt = con.prepareStatement(sql)) {
					pstmt.setString(1, newName);
					pstmt.setString(2, newPhone);
					pstmt.setString(3, newCity);
					pstmt.setInt(4, studentId);

					int rowsAffected = pstmt.executeUpdate();
					if (rowsAffected > 0) {
						System.out.println("Student with ID " + studentId + " has been updated.");
					} else {
						System.out.println("No student found with ID " + studentId + " or update failed.");
					}
				}
			} else {
				System.out.println("Database connection is null. Check your database configuration.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isStudentExists(int userIdToUpdate) {
		boolean exists = false;
		try (Connection con = CP.createC()) {
			if (con != null) {
				String sql = "SELECT * FROM students WHERE sid = ?";
				try (PreparedStatement pstmt = con.prepareStatement(sql)) {
					pstmt.setInt(1, userIdToUpdate);
					try (ResultSet rs = pstmt.executeQuery()) {
						exists = rs.next(); // If a row is found, the student exists
					}
				}
			} else {
				System.out.println("Database connection is null. Check your database configuration.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}
}
