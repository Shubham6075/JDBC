package student_Manage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class start {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Welcome to the Student Management App");

		while (true) {
			System.out.println("\nSelect an option:");
			System.out.println("1. Add a student");
			System.out.println("2. Delete a student");
			System.out.println("3. Display all students");
			System.out.println("4. Update a student record");
			System.out.println("5. Exit");

			try {
				int choice = Integer.parseInt(br.readLine());

				switch (choice) {
					case 1:
						addStudent(br);
						break;
					case 2:
						deleteStudent(br);
						break;
					case 3:
						showAllStudents();
						break;
					case 4:
						updateStudent(br);
						break;
					case 5:
						System.out.println("Exiting the application.");
						System.exit(0);
					default:
						System.out.println("Invalid option. Please try again.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid option (1-5).");
			}
		}
	}

	private static void addStudent(BufferedReader br) throws IOException {
		System.out.println("Enter the student's name:");
		String name = br.readLine();

		System.out.println("Enter the student's phone number:");
		String phone = br.readLine();

		System.out.println("Enter the student's city:");
		String city = br.readLine();

		// Create Student object to store values
		Student st = new Student(name, phone, city);
		boolean answer = StudentDao.insertStudentToDB(st);

		if (answer) {
			System.out.println("Student added successfully.");
		} else {
			System.out.println("Something went wrong. Please try again.");
		}
	}

	private static void deleteStudent(BufferedReader br) throws IOException {
		System.out.println("Enter the student ID to delete:");
		try {
			int userId = Integer.parseInt(br.readLine());
			boolean deleteResult = StudentDao.deleteStudent(userId);
			if (deleteResult) {
				System.out.println("Student deleted successfully.");
			} else {
				System.out.println("No student found with the provided ID or deletion failed.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid student ID.");
		}
	}

	private static void showAllStudents() {
		System.out.println("Showing all students:");
		StudentDao.showAll();
	}

	private static void updateStudent(BufferedReader br) throws IOException {
		System.out.println("Enter the student ID to update:");
		try {
			int userIdToUpdate = Integer.parseInt(br.readLine());

			// Check if the student exists in the database
			if (!StudentDao.isStudentExists(userIdToUpdate)) {
				System.out.println("No student found with the provided ID.");
			} else {
				System.out.println("Enter new name:");
				String newName = br.readLine();
				System.out.println("Enter new phone:");
				String newPhone = br.readLine();
				System.out.println("Enter new city:");
				String newCity = br.readLine();
				boolean updateResult = StudentDao.updateStudent
						(userIdToUpdate, newName, newPhone, newCity);

				if (updateResult) {
					System.out.println("Student record updated successfully.");
				} else {
					System.out.println("Failed to update student record.");
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid student ID.");
		}
	}
}
